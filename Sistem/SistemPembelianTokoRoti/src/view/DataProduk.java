package view;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataProduk extends javax.swing.JFrame {
    
    public DataProduk() {
        initComponents();
        table();
    }
         void cari(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Id Produk");
        tbl.addColumn("Nama Produk");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Harga");          
        try{
            String sql = "SELECT * FROM produk WHERE id_produk like '%" + TCari.getText()+"%'";
                    Connection con = (Connection) Koneksi.getKoneksi();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                while(rs.next()){
                tbl.addRow(new Object[]{
                    rs.getString("id_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("jumlah_produk"),
                    rs.getString("harga_produk")
                });
                 TbProduk.setModel(tbl);
            }
            TbProduk.setModel(tbl);
        }catch (Exception e){
        }
    }
            
        void hapus(){
        TIdProduk.setText("");
        TNama.setText("");
        TJumlahProduk.setText("");
        THargaProduk.setText("");
    }
    public void table(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Id Produk");
        tbl.addColumn("Nama Produk");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Harga");
        
        try{
            Statement st = Koneksi.getKoneksi().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM produk");
            
            while(rs.next()){
                tbl.addRow(new Object[]{
                    rs.getString("id_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("jumlah_produk"),
                    rs.getString("harga_produk")
                });
            }
            TbProduk.setModel(tbl);
          
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Eror"+e.getMessage());
        }
    
    }
        

  
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        TbProduk = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        TCari = new javax.swing.JTextField();
        BCari = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TIdProduk = new javax.swing.JTextField();
        TNama = new javax.swing.JTextField();
        TJumlahProduk = new javax.swing.JTextField();
        BEdit = new javax.swing.JButton();
        BHapus = new javax.swing.JButton();
        BTambah = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        THargaProduk = new javax.swing.JTextField();
        BTampil = new javax.swing.JButton();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        TbProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {"", null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "null", "null", "Title 3", "Title 4"
            }
        ));
        TbProduk.setGridColor(new java.awt.Color(0, 0, 0));
        TbProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbProdukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TbProduk);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Jenis Produk ");

        TCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCariActionPerformed(evt);
            }
        });

        BCari.setText("Cari");
        BCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BCariMouseClicked(evt);
            }
        });
        BCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCariActionPerformed(evt);
            }
        });
        BCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BCariKeyReleased(evt);
            }
        });

        jLabel2.setText("Id Produk");

        jLabel3.setText("Nama");

        jLabel4.setText("Jumlah");

        TIdProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIdProdukActionPerformed(evt);
            }
        });

        BEdit.setText("Edit");
        BEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BEditMouseClicked(evt);
            }
        });
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BHapus.setText("Hapus");
        BHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BHapusMouseClicked(evt);
            }
        });
        BHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHapusActionPerformed(evt);
            }
        });

        BTambah.setText("Tambah");
        BTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTambahMouseClicked(evt);
            }
        });
        BTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTambahActionPerformed(evt);
            }
        });

        jLabel5.setText("Harga");

        THargaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                THargaProdukActionPerformed(evt);
            }
        });

        BTampil.setText("Tampil");
        BTampil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTampilMouseClicked(evt);
            }
        });
        BTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTampilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BEdit)
                        .addGap(29, 29, 29)
                        .addComponent(BHapus))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TCari, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BCari, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTambah)
                                .addGap(18, 18, 18)
                                .addComponent(BTampil))
                            .addComponent(TIdProduk)
                            .addComponent(TNama)
                            .addComponent(TJumlahProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(THargaProduk))))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TIdProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TJumlahProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(THargaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTambah)
                    .addComponent(BTampil))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TCari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BCari, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BEdit)
                    .addComponent(BHapus))
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCariActionPerformed

    }//GEN-LAST:event_TCariActionPerformed

    private void BCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCariActionPerformed
 
    }//GEN-LAST:event_BCariActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed

    }//GEN-LAST:event_BEditActionPerformed

    private void BTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTambahActionPerformed

    }//GEN-LAST:event_BTambahActionPerformed

    private void BHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHapusActionPerformed
   
    }//GEN-LAST:event_BHapusActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
  
    }//GEN-LAST:event_formComponentShown

    private void TIdProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIdProdukActionPerformed

    }//GEN-LAST:event_TIdProdukActionPerformed

    private void BCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BCariMouseClicked
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_BCariMouseClicked

    private void BCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BCariKeyReleased

    }//GEN-LAST:event_BCariKeyReleased

    private void BEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BEditMouseClicked
try {
    String sql = "UPDATE produk SET id_produk=?, nama_produk=?, jumlah_produk=?, harga_produk=? WHERE id_produk=?";
    Connection con = Koneksi.getKoneksi();
    PreparedStatement pst = con.prepareStatement(sql);
    pst.setString(1, TIdProduk.getText());
    pst.setString(2, TNama.getText());
    pst.setString(3, TJumlahProduk.getText());
    pst.setString(4, THargaProduk.getText());
    pst.setString(5, TIdProduk.getText());
    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Berhasil edit");

    pst.close();

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Gagal edit: " + e.getMessage());
    e.printStackTrace();
}

    }//GEN-LAST:event_BEditMouseClicked

    private void BTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTambahMouseClicked
            int jumlah, harga;
            jumlah = Integer.parseInt(TJumlahProduk.getText());
            harga = Integer.parseInt(THargaProduk.getText());

            try {
                Connection con = Koneksi.getKoneksi();

                String sql = "INSERT INTO produk (id_produk, nama_produk, jumlah_produk, harga_produk) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
            
                pst.setString(1, TIdProduk.getText());
                pst.setString(2, TNama.getText());
                pst.setInt(3, jumlah);
                pst.setInt(4, harga);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil menambah");
                pst.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal menambah: " + e.getMessage());
                e.printStackTrace();
            }
    }//GEN-LAST:event_BTambahMouseClicked

    private void BTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTampilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTampilActionPerformed

    private void BTampilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTampilMouseClicked
    try{
        String sql ="SELECT*FROM produk WHERE id_produk='" +TIdProduk.getText()+"'";
        Connection con = (Connection) Koneksi.getKoneksi();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.execute();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    table();
    hapus();
    }//GEN-LAST:event_BTampilMouseClicked

    private void TbProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbProdukMouseClicked
    int baris = TbProduk.rowAtPoint(evt.getPoint());
    String IdProduk = TbProduk.getValueAt(baris, 0).toString();
    TIdProduk.setText(IdProduk);
    String Nama = TbProduk.getValueAt(baris, 1).toString();
    TNama.setText(Nama);
    String Jumlah = TbProduk.getValueAt(baris, 2).toString();
    TJumlahProduk.setText(Jumlah);
    String Harga = TbProduk.getValueAt(baris, 3).toString();
    THargaProduk.setText(Harga);
    }//GEN-LAST:event_TbProdukMouseClicked

    private void BHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BHapusMouseClicked
        try{
         String sql = "DELETE FROM produk WHERE id_produk='" +TIdProduk.getText()+"'";
         Connection con = (Connection) Koneksi.getKoneksi();
         PreparedStatement pst = con.prepareStatement(sql);
         pst.execute();
         JOptionPane.showMessageDialog(null, "Berhasil menghapus");
         
     }catch(Exception e){
         JOptionPane.showMessageDialog(null, "Gagal menghapus"+e.getMessage());
     }  
    }//GEN-LAST:event_BHapusMouseClicked

    private void THargaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_THargaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_THargaProdukActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataProduk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCari;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BHapus;
    private javax.swing.JButton BTambah;
    private javax.swing.JButton BTampil;
    private javax.swing.JTextField TCari;
    private javax.swing.JTextField THargaProduk;
    private javax.swing.JTextField TIdProduk;
    private javax.swing.JTextField TJumlahProduk;
    private javax.swing.JTextField TNama;
    private javax.swing.JTable TbProduk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
