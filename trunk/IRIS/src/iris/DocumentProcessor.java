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
 * @author ZAKIY FIRDAUS ALFIKR
 */
public class DocumentProcessor {

    public DocumentProcessor(){

    }
   
    public int GetTextProperty(String text){
        int hasil=0;
        /*
         * 1 = index
         * 2 = title
         * 3 = content (isi)
         */

        char[] ch = text.toCharArray();
        if (ch[0]=='.'){
            if(ch[1]=='I'){
                hasil = 1;
            } else if(ch[1]=='T'){
                hasil = 2;
            } else if(ch[1]=='W'){
                hasil = 3;
            } else if(ch[1]=='A'){
                hasil = 4;
            } else if(ch[1]=='B'){
                hasil = 5;
            }
        }

        return hasil;
    }

    public int GetTextIdx(String text){
        int hasil=0;
        String idx="";

        char[] ch = text.toCharArray();
        int length = ch.length;
        for(int i=3;i<length;i++){
            idx = idx + ch[i];
        }

        hasil = Integer.parseInt(idx);
        return hasil;
    }

    public Document[] GetDocCollection(String lokasi) throws IOException{
        Document[] docs = new Document[1500];
        for(int i=0;i<1500;i++){
            docs[i] = new Document("","");
        }
        int idx = 0;
        String dtitle="";
        String dcontent="";
        int jenistext = 0;
        FileInputStream fin;
        BufferedInputStream bis;
        DataInputStream dis;
        String text="";

        try {
            fin = new FileInputStream(lokasi);
            bis = new BufferedInputStream(fin);
            dis = new DataInputStream(bis);

            while (dis.available()!=0){
                text = new DataInputStream(fin).readLine();
                jenistext = this.GetTextProperty(text);


                if (jenistext==1){
                    idx = this.GetTextIdx(text);
                }
                if (jenistext==2){
                    dtitle = new DataInputStream(fin).readLine();
                    text = new DataInputStream(fin).readLine();
                    jenistext = this.GetTextProperty(text);
                    while (jenistext < 1){
                        dtitle = dtitle + " " + text;
                        text = new DataInputStream(fin).readLine();
                        jenistext = this.GetTextProperty(text);
                    }
                }
                if (jenistext==3){
                    dcontent = new DataInputStream(fin).readLine();
                    if (dis.available()!=0){
                        text = new DataInputStream(fin).readLine();
                        jenistext = this.GetTextProperty(text);
                    }
                    while (jenistext < 1 && dis.available()!=0){
                        dcontent = dcontent + " " + text;
                        text = new DataInputStream(fin).readLine();
                        if (dis.available()!=0)
                            jenistext = this.GetTextProperty(text);
                    }
//                    System.out.println("[IDX] "+idx);
//                    System.out.println("[TITLE] "+dtitle);
//                    System.out.println("[CONTENT] "+dcontent);
                    dcontent = dcontent.toLowerCase();
                    dtitle = dtitle.toLowerCase();
                    docs[idx].SetContent(dcontent);

                    docs[idx].SetTitle(dtitle);

                    if (jenistext==1){
                        idx = this.GetTextIdx(text);
                    }

                }
            }
            Document[] dochasil = new Document[idx+1];
            for(int i=0;i<idx+1;i++){
                dochasil[i] = new Document(docs[i].GetTitle(), docs[i].GetContent());
            }

            fin.close();
            return dochasil;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }

        return docs;

    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        DocumentProcessor tes = new DocumentProcessor();
        Document[] docs = new Document[1500];
        docs = tes.GetDocCollection("D:\\kuliah\\STBI\\Tugas 3 STBI\\cran.all\\crandummy.all");
        System.out.println("Jumlah: " + docs.length);
        for (int i=1; i<docs.length; i++){
            System.out.println("["+i+"]");
            System.out.println("title: " +docs[i].title);
            System.out.println("content: " +docs[i].content);
            System.out.println("-----------------------");
        }
    }
}
