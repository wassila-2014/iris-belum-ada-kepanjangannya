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

    Vector<InvertedFile> InF;
    HashMap<String, Integer> DF; //Document Frequency, banyaknya dokumen yang mengandung kata ini <kata, banyak dokumen>


    /*******************************************************************/

    //fungsi-fungsi yang ada di sini baru contoh, silakan tambahkan masing-masing sesuai kebutuhan
    /************************** INDEXING *******************************/

    void IndexDocument(){
        //generate InF dari Doc, saat pembobotan perhitungkan pilihan pengguna
    }

    /*******************************************************************/


    /********************** RETRIEVAL PROCESS **************************/

    Vector<Integer> Retrieve(String query){
        Vector<Integer> Result = new Vector<Integer>();

        //Menghasilkan hasil retrieve dari query yang diinput, hasil diranking ke dalam vektor

        return Result;

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
