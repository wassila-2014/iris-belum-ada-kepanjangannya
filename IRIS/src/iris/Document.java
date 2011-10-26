/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iris;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hendra
 */
public class Document {
    //Menyatakan sebuah dokumen, terdiri dari judul dan isi
    public String title;
    public String content;

    //konstruktor
    public Document(String ttl, String ctn){
        title = ttl;
        content = ctn;
    }

    public void SetTitle (String text){
        this.title = text;
    }

    public void SetContent (String content){
        this.content = content;
    }

    public String GetContent (){
        return this.content;
    }

    public String GetTitle(){
        return this.title;
    }



}
