/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iris;

import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author User
 */
public class IRSystem {

    /************ KOMPONEN-KOMPONEN UTAMA DALAM IR SYSTEM *************/
    HashMap<Integer, Document> Doc;// <docID, dokumen yang berasosiasi>
    HashMap<Integer, String> Q_test; // queryID, isi query
    Vector<RelJud> RJ;

    Vector<String> Stopwords; // daftar stopwords

    static Vector<InvertedFile> InF;
    static HashMap<String, Integer> DF; //Document Frequency, banyaknya dokumen yang mengandung kata ini <kata, banyak dokumen>

    Vector<InvertedFile> InFQuery;

    /*******************************************************************/

    //fungsi-fungsi yang ada di sini baru contoh, silakan tambahkan masing-masing sesuai kebutuhan


    /************************** INDEXING *******************************/

    void IndexDocument(){
        //generate InF dari Doc, saat pembobotan perhitungkan pilihan pengguna
    }

    /*******************************************************************/


    /********************** RETRIEVAL PROCESS **************************/
    //Retrieve dari query InFQuery
    Vector<Integer> Retrieve(){
        Vector<Integer> result = new Vector<Integer>(); //Vector<Integer>, Int = docID
        Vector<Float> similarityVector = new Vector<Float>();
        Vector<Integer> docIDList = new Vector<Integer>();

        //Vektor yang udah kefilter :
        Filters filter = new Filters();
        Vector<InvertedFile> filteredInFDoc = filter.filterInFByInfQuery(InF, InFQuery);

        //HItung SC semua dokumen :
        int filteredInFDocSize = filteredInFDoc.size();
        

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
        }

       
        //Menghasilkan hasil retrieve dari query yang diinput, hasil diranking ke dalam vektor

        return result;

    }

    /*******************************************************************/

    /************************** EXPERIMENT *****************************/
    //lakukan Retrieve untuk semua Query di Q_test, lalu hitung NIAP masing2

    float countNIAP(Vector<Integer> SearchResult){
        float NIAP = 0;

        //membandingkan search result dengan RJ, lalu hitung NIAPnya

        return NIAP;
    }

    /*******************************************************************/
}
