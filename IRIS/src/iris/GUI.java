/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GUI.java
 *
 * Created on 09 Okt 11, 16:21:16
 */

package iris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author Hendra
 */
public class GUI extends javax.swing.JFrame {

    /** Creates new form GUI */
    public GUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        document_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dok_tf = new javax.swing.JComboBox();
        dok_idf = new javax.swing.JCheckBox();
        dok_normal = new javax.swing.JCheckBox();
        dok_stemming = new javax.swing.JCheckBox();
        dok_stopword = new javax.swing.JCheckBox();
        query_panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        query_tf = new javax.swing.JComboBox();
        query_idf = new javax.swing.JCheckBox();
        query_normal = new javax.swing.JCheckBox();
        query_stemming = new javax.swing.JCheckBox();
        query_stopword = new javax.swing.JCheckBox();
        file_addr_panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dok_addr = new javax.swing.JTextField();
        dok_browse = new javax.swing.JButton();
        query_browse = new javax.swing.JButton();
        query_addr = new javax.swing.JTextField();
        rel_browse = new javax.swing.JButton();
        rel_addr = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        stopword_browse = new javax.swing.JButton();
        stopword_addr = new javax.swing.JTextField();
        action_panel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        indexing_btn = new javax.swing.JButton();
        user_query = new javax.swing.JTextField();
        search_btn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        experiment_btn = new javax.swing.JButton();
        show_if_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title_panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36));
        jLabel1.setText("IRIS (belum ada kepanjangannya)");

        javax.swing.GroupLayout title_panelLayout = new javax.swing.GroupLayout(title_panel);
        title_panel.setLayout(title_panelLayout);
        title_panelLayout.setHorizontalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(4, Short.MAX_VALUE))
        );
        title_panelLayout.setVerticalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(title_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 563, -1));

        document_panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setText("Collection Document Indexing");

        jLabel3.setText("Dokumen Weighting:");

        dok_tf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Raw TF", "Binary TF", "Logaritmic TF", "Augmented TF" }));

        dok_idf.setText("IDF");

        dok_normal.setText("Normalization");

        dok_stemming.setText("Perform Stemming");

        dok_stopword.setText("Perform Stopword Removal");

        javax.swing.GroupLayout document_panelLayout = new javax.swing.GroupLayout(document_panel);
        document_panel.setLayout(document_panelLayout);
        document_panelLayout.setHorizontalGroup(
            document_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(document_panelLayout.createSequentialGroup()
                .addGroup(document_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(document_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(document_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(document_panelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(document_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dok_normal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dok_idf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dok_stopword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dok_stemming, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dok_tf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        document_panelLayout.setVerticalGroup(
            document_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(document_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dok_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dok_idf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dok_normal)
                .addGap(18, 18, 18)
                .addComponent(dok_stemming)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dok_stopword)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        getContentPane().add(document_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 119, -1, -1));

        query_panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel4.setText("Query Formulation");

        jLabel5.setText("Query Weighting:");

        query_tf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Raw TF", "Binary TF", "Logaritmic TF", "Augmented TF" }));

        query_idf.setText("IDF");
        query_idf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                query_idfActionPerformed(evt);
            }
        });

        query_normal.setText("Normalization");

        query_stemming.setText("Perform Stemming");

        query_stopword.setText("Perform Stopword Removal");

        javax.swing.GroupLayout query_panelLayout = new javax.swing.GroupLayout(query_panel);
        query_panel.setLayout(query_panelLayout);
        query_panelLayout.setHorizontalGroup(
            query_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(query_panelLayout.createSequentialGroup()
                .addGroup(query_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(query_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(query_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)))
                    .addGroup(query_panelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(query_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(query_normal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(query_idf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(query_stopword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(query_stemming, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(query_tf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        query_panelLayout.setVerticalGroup(
            query_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(query_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(query_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(query_idf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(query_normal)
                .addGap(18, 18, 18)
                .addComponent(query_stemming)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(query_stopword)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        getContentPane().add(query_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 119, -1, -1));

        file_addr_panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel6.setText("Files Address");

        jLabel7.setText("Document Collection");

        jLabel8.setText("Queries");

        jLabel9.setText("Relevance-Judgement");

        dok_browse.setText("Browse");
        dok_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dok_browseActionPerformed(evt);
            }
        });

        query_browse.setText("Browse");
        query_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                query_browseActionPerformed(evt);
            }
        });

        rel_browse.setText("Browse");
        rel_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rel_browseActionPerformed(evt);
            }
        });

        jLabel12.setText("Stopwords");

        stopword_browse.setText("Browse");
        stopword_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopword_browseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout file_addr_panelLayout = new javax.swing.GroupLayout(file_addr_panel);
        file_addr_panel.setLayout(file_addr_panelLayout);
        file_addr_panelLayout.setHorizontalGroup(
            file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(file_addr_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(file_addr_panelLayout.createSequentialGroup()
                        .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(file_addr_panelLayout.createSequentialGroup()
                                .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                                .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(file_addr_panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(query_addr, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dok_addr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, file_addr_panelLayout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(rel_addr, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dok_browse)
                            .addComponent(query_browse)
                            .addComponent(rel_browse))
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, file_addr_panelLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                        .addGap(9, 9, 9)
                        .addComponent(stopword_addr, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(stopword_browse)
                        .addGap(50, 50, 50))))
        );
        file_addr_panelLayout.setVerticalGroup(
            file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(file_addr_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(file_addr_panelLayout.createSequentialGroup()
                        .addComponent(dok_browse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(query_browse)
                            .addComponent(query_addr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(file_addr_panelLayout.createSequentialGroup()
                        .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(dok_addr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rel_browse)
                    .addComponent(rel_addr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(file_addr_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stopword_browse)
                    .addComponent(stopword_addr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(file_addr_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 560, 170));

        action_panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel10.setText("Actions");

        indexing_btn.setText("Doc. Indexing");
        indexing_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexing_btnActionPerformed(evt);
            }
        });

        search_btn.setText("Search");
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        jLabel11.setText("user query:");

        experiment_btn.setText("Experiment");

        show_if_btn.setText("Show Inverted File");
        show_if_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_if_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout action_panelLayout = new javax.swing.GroupLayout(action_panel);
        action_panel.setLayout(action_panelLayout);
        action_panelLayout.setHorizontalGroup(
            action_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(action_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(action_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(action_panelLayout.createSequentialGroup()
                        .addGroup(action_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(action_panelLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(user_query, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(search_btn))
                            .addComponent(jLabel10))
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(action_panelLayout.createSequentialGroup()
                        .addComponent(indexing_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(show_if_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(experiment_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );
        action_panelLayout.setVerticalGroup(
            action_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(action_panelLayout.createSequentialGroup()
                .addGroup(action_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(action_panelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(action_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(indexing_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(experiment_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                    .addGroup(action_panelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(show_if_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(action_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_query, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(search_btn))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(action_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 560, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dok_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dok_browseActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int retval = fc.showOpenDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            dok_addr.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_dok_browseActionPerformed

    private void query_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_query_browseActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int retval = fc.showOpenDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            query_addr.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_query_browseActionPerformed

    private void rel_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rel_browseActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int retval = fc.showOpenDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            rel_addr.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_rel_browseActionPerformed

    private void stopword_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopword_browseActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int retval = fc.showOpenDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            stopword_addr.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_stopword_browseActionPerformed

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        if (isIndexingDoc) {
            irs.IndexQuery(user_query.getText(), query_stemming.isSelected(), query_stopword.isSelected(), query_idf.isSelected(), query_tf.getSelectedIndex(), query_normal.isSelected());
            new SearchResult().setVisible(true);
        } else {
            
        }
    }//GEN-LAST:event_search_btnActionPerformed

    private void show_if_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_if_btnActionPerformed
        // TODO add your handling code here:
        ShowInvertedFile sif = new ShowInvertedFile(this);
        sif.show();
        this.hide();
    }//GEN-LAST:event_show_if_btnActionPerformed

    private void indexing_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexing_btnActionPerformed
        isIndexingDoc = true;
        irs = new IRSystem();
        irs.IndexDocument(dok_addr.getText(), dok_stemming.isSelected(), dok_stopword.isSelected(), stopword_addr.getText(), dok_idf.isSelected(), dok_tf.getSelectedIndex(), dok_normal.isSelected());
    }//GEN-LAST:event_indexing_btnActionPerformed

    private void query_idfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_query_idfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_query_idfActionPerformed


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    
    public Boolean isIndexingDoc = false;
    public IRSystem irs;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel action_panel;
    private javax.swing.JPanel document_panel;
    private javax.swing.JTextField dok_addr;
    private javax.swing.JButton dok_browse;
    private javax.swing.JCheckBox dok_idf;
    private javax.swing.JCheckBox dok_normal;
    private javax.swing.JCheckBox dok_stemming;
    private javax.swing.JCheckBox dok_stopword;
    private javax.swing.JComboBox dok_tf;
    private javax.swing.JButton experiment_btn;
    private javax.swing.JPanel file_addr_panel;
    private javax.swing.JButton indexing_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField query_addr;
    private javax.swing.JButton query_browse;
    private javax.swing.JCheckBox query_idf;
    private javax.swing.JCheckBox query_normal;
    private javax.swing.JPanel query_panel;
    private javax.swing.JCheckBox query_stemming;
    private javax.swing.JCheckBox query_stopword;
    private javax.swing.JComboBox query_tf;
    private javax.swing.JTextField rel_addr;
    private javax.swing.JButton rel_browse;
    private javax.swing.JButton search_btn;
    private javax.swing.JButton show_if_btn;
    private javax.swing.JTextField stopword_addr;
    private javax.swing.JButton stopword_browse;
    private javax.swing.JPanel title_panel;
    private javax.swing.JTextField user_query;
    // End of variables declaration//GEN-END:variables

}
