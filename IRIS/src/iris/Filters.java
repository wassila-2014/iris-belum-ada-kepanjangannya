/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iris;

import java.util.Vector;

/**
 *
 * @author User
 */
public class Filters {
    //Sebagai pengganti query SELECT di database

    //contoh
    int getWeightByTermDoc (String term, String docID){
        int result = 0;

        //menghasilkan bobot kata term di dokumen docID, kalo gak ada nilainya nol
        //menelusuri semua isi InF, cari yang sesuai

        return result;
    }

    Vector<InvertedFile> filterInFByInfQuery(Vector<InvertedFile> InFDoc, Vector<InvertedFile> InFQuery) {
        Vector<InvertedFile> result = new Vector<InvertedFile>();

        int InFQuerySize = InFQuery.size();
        int InFDocSize = InFDoc.size();
        int i,j;
        
        for (i=0; i<InFQuerySize; ++i) {
            String queryTermNow = InFQuery.get(i).term;
            for (j=0; i<InFDocSize; ++j) {
                String docTermNow = InFDoc.get(j).term;
                //Kalo term queryTermNow = docTermNow, tambahin ke result;
                if (queryTermNow.equals(docTermNow)) {
                    result.add(InFDoc.get(j));
                }
            }
        }
        
        return result;
    }

    
}
