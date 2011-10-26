/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iris;
/**
 *
 * @author Hendra
 */
public class InvertedFile {

    //hasil indexing
    public String term;//suatu kata
    public Integer docID;//di dokumen mana, berasosiasi ke variabel Doc
    public int TF; //banyak kata term yang muncul di dokumen docID
    public int TFWeight; //bobot kata term di dokumen docID


   //konstruktor
    public InvertedFile(String trm, Integer id, int tfr, int tfw){
        term = trm;
        docID = id;
        TF = tfr;
        TFWeight = tfw;
    }
}
