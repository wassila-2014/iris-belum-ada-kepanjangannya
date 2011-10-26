/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iris;

/**
 *
 * @author ZAKIY FIRDAUS ALFIKR
 */
public class Query {
//Menyatakan sebuah dokumen, terdiri dari judul dan isi
    public String content;

    //konstruktor
    public Query(String ctn){
        content = ctn;
    }

    public void SetContent (String content){
        this.content = content;
    }

    public String GetContent (){
        return this.content;
    }

}
