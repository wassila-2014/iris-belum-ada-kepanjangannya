/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iris;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    ArrayList<String> Q_test; // queryID, isi query
    HashMap<Point, Boolean> RJ; // daftar Relevant Judgement
    HashMap<Integer, Integer> RelSize = new HashMap<Integer, Integer>();
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
            GlobalVariable.Doc = Doc;
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
//        System.out.println("Mode weighting "+WeightBy);
//        System.out.println("**************Hasil*********************");
//        for (InvertedFile tempInF : InF) {
//            System.out.println("Term : "+tempInF.term);
//            System.out.println("Dokumen : "+tempInF.docID);
//            System.out.println("Jumlah : "+tempInF.TF);
//            System.out.println("Bobot : "+tempInF.TFWeight);
//            System.out.println("");
//        }
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
//        System.out.println("Query Processing took " + time + " milliseconds");
//        System.out.println("Mode weighting "+WeightBy);
//        System.out.println("**************Hasil*********************");
//        for (InvertedFile tempInF : InFQuery) {
//            System.out.println("Term : "+tempInF.term);
//            System.out.println("Dokumen : "+tempInF.docID);
//            System.out.println("Jumlah : "+tempInF.TF);
//            System.out.println("Bobot : "+tempInF.TFWeight);
//            System.out.println("");
//        }
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
    void Retrieve(){
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
        GlobalVariable.searchResultDocument = resultDocument;
        GlobalVariable.searchResultSimilarity = resultSimilarity;
    }

    /*******************************************************************/
    void BacaQuery(String filename) {
        try {
            Q_test = new ArrayList<String>();
            DocumentProcessor dp = new DocumentProcessor();
            Query[] q = dp.GetQueryCollection(filename);
            Integer i = 0;
            for (Query query : q) {
                if (i==0) {}
                else {
                    Q_test.add(query.content);
                }
                i++;
            }
            GlobalVariable.Que = Q_test;
        } catch (IOException ex) {
            Logger.getLogger(IRSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void BacaRJ(String filename) {
        try {
            RJ = new HashMap<Point, Boolean>();

            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String[] temp;
            int queryID = 0;
            int docID = 0;
            int currQID = 0;
            int relQID = 0;
            while ((strLine = br.readLine()) != null) {
                temp = strLine.split(" ");
                queryID = Integer.parseInt(temp[0]);
                docID = Integer.parseInt(temp[1]);
                RJ.put(new Point(queryID,docID),true);
                if(queryID != currQID)
                {
                    relQID++;
                    RelSize.put(currQID,relQID);
                    relQID = 0;
                    currQID = queryID;
                }
                else
                {
                    relQID++;
                }
            }
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    /************************** EXPERIMENT *****************************/
    //lakukan Retrieve untuk semua Query di Q_test, lalu hitung NIAP masing2
    void experiment(String queryAddress, String RJAddress,Boolean isStemming, Boolean isStopWordRemoval, Boolean isUseIDF, int WeightBy, Boolean isNormalisasi)
    {
        BacaQuery(queryAddress);
	//- Baca file Relevance Judgement, simpen ke variabel RJ
        BacaRJ(RJAddress);
	//- Lakukan Retrieval Process (yang b.) untuk masing2 Query di Q_test
        float niap = 0;
        float sum = 0;
        GlobalVariable.NIAP = new ArrayList<Float>();
        int n=0;
        for(int i = 0; i < Q_test.size(); ++i)
        {
            IndexQuery(Q_test.get(i), isStemming, isStopWordRemoval, isUseIDF, WeightBy, isNormalisasi);
            Retrieve();
            niap = countNIAP(GlobalVariable.searchResultDocument,i+1);
            GlobalVariable.NIAP.add(niap);
            if(niap>-1) {
                sum += niap;
                n++;
            }
            
        }
        GlobalVariable.xNIAP = sum/n;
        System.out.println(GlobalVariable.NIAP.get(111));
        System.out.println("Rata2");
        System.out.println(GlobalVariable.xNIAP);
    }

    float countNIAP(List<Integer> SearchResult,int queryID){
        Integer relDoc = 0;
        Integer retrieved = SearchResult.size();

        float sum = 0;
        for (int i = 0; i < retrieved; ++i) {
            if (RJ.get(new Point(queryID, SearchResult.get(retrieved-i-1))) != null) {
                relDoc++;
                sum += relDoc / (float) (i+1);
            }
        }
        try
        {
        if (relDoc > 0) {
            return sum / (float) RelSize.get(queryID);
        } else {
            return -1;
        }
        } catch(Exception e)
        {

            System.out.println(queryID);
            return 0;
        }


    }

    /*******************************************************************/
    public static void main(String[] args) {
        System.out.println("ok");
    }
    
}
