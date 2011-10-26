/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iris;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class IRSystem {

    /************ KOMPONEN-KOMPONEN UTAMA DALAM IR SYSTEM *************/
    HashMap<Integer, Document> Doc;// <docID, dokumen yang berasosiasi>
    HashMap<Integer, String> Q_test; // queryID, isi query
    Vector<RelJud> RJ;

    HashMap<String, Boolean> Stopwords;

    static Vector<InvertedFile> InF;
    static HashMap<String, Integer> DF; //Document Frequency, banyaknya dokumen yang mengandung kata ini <kata, banyak dokumen>

    Vector<InvertedFile> InFQuery;

    /*******************************************************************/

    //fungsi-fungsi yang ada di sini baru contoh, silakan tambahkan masing-masing sesuai kebutuhan


    /************************** INDEXING *******************************/
    void BacaDocument(String filename) {
        try {
            Doc = new HashMap<Integer, Document>();
            DocumentProcessor dp = new DocumentProcessor();
            Document[] d = dp.GetDocCollection(filename);
            Integer i = 0;
            for (Document document : d) {
                if (i==0) {}
                else {
                    Doc.put(i, document);
                }
                i++;
                //System.out.println("aa");
            }
        } catch (IOException ex) {
            Logger.getLogger(IRSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void IndexDocument(String filenameDocument, Boolean isStemming, Boolean isStopWordRemoval, String filenameStopWord, Boolean isUseIDF, int WeightBy, Boolean isNormalisasi){
        //generate InF dari Doc, saat pembobotan perhitungkan pilihan pengguna
        long time=System.currentTimeMillis();
        BacaDocument(filenameDocument);
        if (isStopWordRemoval) {
            BacaStopWord(filenameStopWord);
        }
        Iterator it = Doc.keySet().iterator();
        InF = new Vector<InvertedFile>();
        DF = new HashMap<String, Integer>();
        while (it.hasNext()) {
            Integer key = (Integer) it.next();      // document index
            Document val = (Document) Doc.get(key); // document variable
            String ncontent;
            if (isStemming) {                         // check for stemming
                ncontent = Stemmer.stem(val.content);
            } else {
                ncontent = val.content;
            }
            
            String[] tempContent = ncontent.split("[ .,?!]+");      // remove tanda baca dan spasi
            HashMap<String, Integer> tempInf = new HashMap<String, Integer>();
            for (String c : tempContent) {                          // iterate for count frequency all terms in each document
                if (isStopWordRemoval && Stopwords.containsKey(c)) {     // check for stopwordremoval
                } else {
                    Integer t = tempInf.put(c, 1);
                    if (t!=null) {
                        tempInf.put(c, t+1);
                    }
                }
            }
            
            Integer maxTF = 0;
            Iterator tempk = tempInf.keySet().iterator();
            while(tempk.hasNext()) {                                // insert to vector inverted file
                String tempKey = (String) tempk.next();
                Integer tempValue = (Integer)tempInf.get(tempKey);
                if (tempValue>maxTF)        // max tf untuk perhitungan augmented TF
                    maxTF = tempValue;
                float tempWeight = tempValue;                               // default weight raw TF, pembobotan dilakukan setelah semua dihitung
                if (WeightBy==1)    // default weight jika yg dipilih binary TF
                    tempWeight = 1;
                else if (WeightBy==2) // log tf
                    tempWeight = (float) (1 + Math.log10(tempWeight));
                InvertedFile ivf = new InvertedFile(tempKey, key, tempValue, tempWeight);
                InF.add(ivf);
                // tambahkan perhitungan DF
                Integer valDF = DF.put(tempKey, 1);
                if (valDF!=null)
                    DF.put(tempKey, valDF+1);
            }
            // perhitungan augmented TF
            if (WeightBy==3) {
                for (InvertedFile tempInF : InF) {
                    if (tempInF.docID==key) {
                        tempInF.TFWeight = (float) (0.5 + (0.5*tempInF.TFWeight)/maxTF);
                    }
                }
            }
        }
        
        // perhitungan IDF
        if (isUseIDF) {
            for (InvertedFile tempInF : InF) {
                tempInF.TFWeight = (float) (tempInF.TFWeight * Math.log10((float)Doc.size()/DF.get(tempInF.term)));
            }
        }
        // perhitungan Normalisasi
        if (isNormalisasi) {
            HashMap<Integer, Float> vectorDocumentValue = new HashMap<Integer, Float>();
            for (InvertedFile tempInF : InF) {
                Float tempSquareVal = tempInF.TFWeight;
                Float tempValueSquare = vectorDocumentValue.put(tempInF.docID, tempSquareVal*tempSquareVal);
                if (tempValueSquare!=null) {
                    vectorDocumentValue.put(tempInF.docID, tempSquareVal*tempSquareVal+tempValueSquare);
                }
            }
            for (InvertedFile tempInF : InF) {
                tempInF.TFWeight = (float) (tempInF.TFWeight/(Math.sqrt(vectorDocumentValue.get(tempInF.docID))));
            }
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Document Processing took " + time + " milliseconds");
        System.out.println("Mode weighting "+WeightBy);
        System.out.println("**************Hasil*********************");
        for (InvertedFile tempInF : InF) {
            System.out.println("Term : "+tempInF.term);
            System.out.println("Dokumen : "+tempInF.docID);
            System.out.println("Jumlah : "+tempInF.TF);
            System.out.println("Bobot : "+tempInF.TFWeight);
            System.out.println("");
        }
    }
    
    void IndexQuery(String Query, Boolean isStemming, Boolean isStopWordRemoval, Boolean isUseIDF, int WeightBy, Boolean isNormalisasi){
        //generate InF dari Doc, saat pembobotan perhitungkan pilihan pengguna
        long time=System.currentTimeMillis();
        Iterator it = Doc.keySet().iterator();
        InFQuery = new Vector<InvertedFile>();
        
        Document val = new Document("Query", Query); // document variable
        String ncontent;
        if (isStemming) {                         // check for stemming
            ncontent = Stemmer.stem(val.content);
        } else {
            ncontent = val.content;
        }

        String[] tempContent = ncontent.split("[ .,?!]+");      // remove tanda baca dan spasi
        HashMap<String, Integer> tempInf = new HashMap<String, Integer>();
        for (String c : tempContent) {                          // iterate for count frequency all terms in each document
            if (isStopWordRemoval && Stopwords.containsKey(c)) {     // check for stopwordremoval
            } else {
                Integer t = tempInf.put(c, 1);
                if (t!=null) {
                    tempInf.put(c, t+1);
                }
            }
        }

        Integer maxTF = 0;
        Iterator tempk = tempInf.keySet().iterator();
        while(tempk.hasNext()) {                                // insert to vector inverted file
            String tempKey = (String) tempk.next();
            Integer tempValue = (Integer)tempInf.get(tempKey);
            if (tempValue>maxTF)        // max tf untuk perhitungan augmented TF
                maxTF = tempValue;
            float tempWeight = tempValue;                               // default weight raw TF, pembobotan dilakukan setelah semua dihitung
            if (WeightBy==1)    // default weight jika yg dipilih binary TF
                tempWeight = 1;
            else if (WeightBy==2) // log tf
                tempWeight = (float) (1 + Math.log10(tempWeight));
            InvertedFile ivf = new InvertedFile(tempKey, -1, tempValue, tempWeight);
            InFQuery.add(ivf);
        }
        // perhitungan augmented TF
        if (WeightBy==3) {
            for (InvertedFile tempInF : InFQuery) {
                if (tempInF.docID==-1) {
                    tempInF.TFWeight = (float) (0.5 + (0.5*tempInF.TFWeight)/maxTF);
                }
            }
        }
        
        // perhitungan IDF
        if (isUseIDF) {
            for (InvertedFile tempInF : InFQuery) {
                if (DF.containsKey(tempInF.term))
                    tempInF.TFWeight = (float) (tempInF.TFWeight * Math.log10((float)Doc.size()/DF.get(tempInF.term)));
                else
                    tempInF.TFWeight = 0;
            }
        }
        // perhitungan Normalisasi
        if (isNormalisasi) {
            HashMap<Integer, Float> vectorDocumentValue = new HashMap<Integer, Float>();
            for (InvertedFile tempInF : InFQuery) {
                Float tempSquareVal = tempInF.TFWeight;
                Float tempValueSquare = vectorDocumentValue.put(tempInF.docID, tempSquareVal*tempSquareVal);
                if (tempValueSquare!=null) {
                    vectorDocumentValue.put(tempInF.docID, tempSquareVal*tempSquareVal+tempValueSquare);
                }
            }
            for (InvertedFile tempInF : InFQuery) {
                tempInF.TFWeight = (float) (tempInF.TFWeight/(Math.sqrt(vectorDocumentValue.get(tempInF.docID))));
            }
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Query Processing took " + time + " milliseconds");
        System.out.println("Mode weighting "+WeightBy);
        System.out.println("**************Hasil*********************");
        for (InvertedFile tempInF : InFQuery) {
            System.out.println("Term : "+tempInF.term);
            System.out.println("Dokumen : "+tempInF.docID);
            System.out.println("Jumlah : "+tempInF.TF);
            System.out.println("Bobot : "+tempInF.TFWeight);
            System.out.println("");
        }
    }
    
    void BacaStopWord(String filename) {
        try {
            //Stopwords = new Vector<String>();
            Stopwords = new HashMap<String, Boolean> ();
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(filename);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                //Stopwords.add(strLine.replaceAll("[^a-zA-Z]", ""));
                Stopwords.put(strLine.toLowerCase().replaceAll("[^a-z]", ""), Boolean.TRUE);
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    /*******************************************************************/


    /********************** RETRIEVAL PROCESS **************************/
    //Retrieve dari query InFQuery
    List<Object> Retrieve(){
        List<Object> result = null;
        List<Integer> resultDocument;
        List<Float> resultSimilarity;

        HashMap<Integer, Float> similarityHashmap = new HashMap<Integer, Float>();

        //Vektor yang udah kefilter :
        Filters filter = new Filters();
        Vector<InvertedFile> filteredInFDoc = filter.filterInFByInfQuery(InF, InFQuery);

        /* HItung SC semua dokumen : */
        int filteredInFDocSize = filteredInFDoc.size();
        float init = 0.0f;
        int i;

        //Iterasi filteredInFDocSize ini :
        for (i=0; i<filteredInFDocSize; ++i) {
            Integer idDoc = filteredInFDoc.get(i).docID;
            String termThisDoc = filteredInFDoc.get(i).term;
            float bobotQueryThisTerm = filter.getTFWeightByTerm(termThisDoc, InFQuery);
            Float SCThisTerm = filteredInFDoc.get(i).TFWeight * bobotQueryThisTerm;

            Float simTemp = similarityHashmap.get(idDoc);

            if (simTemp== null) {
                Float newSC = SCThisTerm + init;
                similarityHashmap.put(idDoc, newSC);
            } else {
                Float newSC = SCThisTerm + simTemp;
                similarityHashmap.put(idDoc, newSC);
            }
        }

        //MAsukkin ke result (udah di rank) :
        resultDocument = Utility.sortByValueGetKey(similarityHashmap);
        resultSimilarity = Utility.sortByValueGetValue(similarityHashmap);


        /* Vector<Integer> docIDList = new Vector<Integer>();
        //List semua docID masukkin ke vector ListDocID;
        float init = 0.0f;
        for (Integer key : Doc.keySet()) {
             docIDList.add(key);
        }

        //Iterasi semua docIDLIst :
        int i;
        int docIDListSize = docIDList.size();
        for (i=0; i < docIDListSize; ++i) {
            int docID = docIDList.get(i);
            //Hitung SC untuk docID ini
            //Cari semua term yang ada di filteredInFDocSize yang punya docID = docID ini


        } */


        //Menghasilkan hasil retrieve dari query yang diinput, hasil diranking ke dalam vektor

        result.add(resultDocument);
        result.add(resultSimilarity);

        return result;

    }

    /*******************************************************************/
    void BacaQuery(String filename) {
        try {
            Q_test = new HashMap<Integer, String> ();
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int i = 0;
            while ((strLine = br.readLine()) != null) {
                Q_test.put(i,strLine.replaceAll("[^a-zA-Z]", ""));
                ++i;
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    void BacaRJ(String filename) {
        try {
            RJ = new Vector<RelJud>();
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int i = 0;
            String[] temp;
            while ((strLine = br.readLine()) != null) {
                temp = strLine.split(" ", 2);
                RJ.add(new RelJud(Integer.parseInt(temp[0]),Integer.parseInt(temp[1])));
            }
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    /************************** EXPERIMENT *****************************/
    //lakukan Retrieve untuk semua Query di Q_test, lalu hitung NIAP masing2

    float countNIAP(Vector<Integer> SearchResult){
        float NIAP = 0;

        //membandingkan search result dengan RJ, lalu hitung NIAPnya

        return NIAP;
    }

    /*******************************************************************/
    
}
