package view;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MenuTransaksi extends javax.swing.JFrame {
        public void table() {
            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("ID Transaksi");
            tbl.addColumn("ID Produk");
            tbl.addColumn("Nama Barang");
            tbl.addColumn("Jumlah");
            tbl.addColumn("Harga");
            tbl.addColumn("Total");

            try {
                Connection con = Koneksi.getKoneksi();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM transaksi");

                while (rs.next()) {
                    tbl.addRow(new Object[] {
                        rs.getString("id_transaksi"),
                            rs.getString("id_produk"),
                            rs.getString("nama_produk"),
                            rs.getString("jumlah_produk"),
                            rs.getString("harga_produk"),
                            rs.getString("total_transaksi")
                    });
                }
                TbTransaksi.setModel(tbl);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                e.printStackTrace();
            }
            hitung();
        }
        public void kurangiProduk(String idProduk, int jumlah) {
            try {
                Connection con = Koneksi.getKoneksi();

                // Perbarui jumlah barang di tabel produk
                String sql = "UPDATE produk SET jumlah_produk = jumlah_produk - ? WHERE id_produk = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, jumlah);
                pst.setString(2, idProduk);
                pst.executeUpdate();

                pst.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal mengurangi jumlah barang: " + e.getMessage());
                e.printStackTrace();
            }
        }

public void tambahTransaksi() {
    int harga, jumlah, total;
    jumlah = Integer.parseInt(TJumlahProduk.getText());
    harga = Integer.parseInt(THarga.getText());
    total = jumlah * harga;
  
    
    TTotalBayar.setText("Rp." + total + ",00");

    try {
        Connection con = Koneksi.getKoneksi();

        String sql = "INSERT INTO transaksi (id_transaksi, id_produk, nama_produk, jumlah_produk, harga_produk, total_transaksi) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, TIdTransaksi.getText());
        pst.setString(2, TIdProduk.getText());
        pst.setString(3, TNamaProduk.getText());
        pst.setInt(4, jumlah);
        pst.setInt(5, harga);
        pst.setInt(6, total);

        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Berhasil menambah");
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal menambah: " + e.getMessage());
        e.printStackTrace();
    }

            kurangiProduk(TIdProduk.getText(), jumlah);
            hitung();
            data();
            totalTransaksi();
            clear2();
            TIdTransaksi.requestFocus();
        }

        public void totalTransaksi() {
            try {
                Connection con = Koneksi.getKoneksi();
                Statement st = con.createStatement();
                String sql = "SELECT jumlah_produk, harga_produk FROM transaksi";
                ResultSet rs = st.executeQuery(sql);
                int totalTransaksi = 0;

                while (rs.next()) {
                    int jumlahProduk = rs.getInt("jumlah_produk");
                    int hargaProduk = rs.getInt("harga_produk");
                    totalTransaksi += jumlahProduk * hargaProduk;
                }

                // Menampilkan total transaksi
                TTotalBayar.setText("Rp" + totalTransaksi + ",00");

                // Menutup koneksi
                rs.close();
                st.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal menambah: " + e.getMessage());
                e.printStackTrace();
            }
        }

        private void hitung() {
            try {
                Connection con = Koneksi.getKoneksi();
                Statement st = con.createStatement();
                String sql = "SELECT * FROM transaksi ORDER BY id_transaksi DESC";
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    String id_transaksi = rs.getString("id_transaksi").substring(2);
                    String TR = "" + (Integer.parseInt(id_transaksi) + 1);
                    String Nol = "";

                    if (TR.length() == 1) {
                        Nol = "000";
                    } else if (TR.length() == 2) {
                        Nol = "00";
                    } else if (TR.length() == 3) {
                        Nol = "0";
                    } else if (TR.length() == 4) {
                        Nol = "";
                    }
                    TIdTransaksi.setText("TR" + Nol + TR);
                } else {
                    TIdTransaksi.setText("TR0001");
                }
                rs.close();
                st.close();
            } catch (Exception e) {
                System.out.println("hitung error: " + e.getMessage());
            }
            totalTransaksi();
        }

        public void data() {
            DefaultTableModel model = (DefaultTableModel) TbTransaksi.getModel();
            model.addRow(new Object[] {
                TIdTransaksi.getText(),
                    TIdProduk.getText(),
                    TNamaProduk.getText(),
                    TJumlahProduk.getText(),
                    THarga.getText(),
                    TTotalBayar.getText()
            });
        }

        public void kosong() {
            DefaultTableModel tbl = (DefaultTableModel) TbTransaksi.getModel();
            while (tbl.getRowCount() > 0) {
                tbl.removeRow(0);
            }
        }

        public void utama() {
            TIdTransaksi.setText("");
            TIdProduk.setText("");
            TNamaProduk.setText("");
            TJumlahProduk.setText("");
            THarga.setText("");
            hitung();
        }

        public void clear() {
            TTotalBayar.setText("0");
        }

        public void clear2() {
            TIdProduk.setText("");
            TNamaProduk.setText("");
            TJumlahProduk.setText("");
            THarga.setText("");
        }
        public MenuTransaksi() {
            initComponents();
            table();
        }
        void hapus() {
            TIdTransaksi.setText("");
            TIdProduk.setText("");
            TNamaProduk.setText("");
            TJumlahProduk.setText("");
            THarga.setText("");
            TTotalBayar.setText("0");
        }
        
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton4 = new javax.swing.JToggleButton();
        jMenu1 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TIdTransaksi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TbTransaksi = new javax.swing.JTable();
        BHapus = new javax.swing.JToggleButton();
        BTambah = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        LabelJumlah = new javax.swing.JLabel();
        TIdProduk = new javax.swing.JTextField();
        THarga = new javax.swing.JTextField();
        TJumlahProduk = new javax.swing.JTextField();
        TNamaProduk = new javax.swing.JTextField();
        TTotalBayar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        BTampil = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        jToggleButton4.setText("Total ");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("KASIR ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("ID Produk");

        TIdTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIdTransaksiActionPerformed(evt);
            }
        });

        TbTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Produk", "Nama Produk", "Jumlah", "Harga"
            }
        ));
        TbTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TbTransaksi);

        BHapus.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 12)); // NOI18N
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

        BTambah.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 12)); // NOI18N
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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("ID Transaksi");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Nama Produk");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Harga");

        LabelJumlah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LabelJumlah.setText("Jumlah");

        TIdProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIdProdukActionPerformed(evt);
            }
        });

        THarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                THargaActionPerformed(evt);
            }
        });

        TJumlahProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TJumlahProdukActionPerformed(evt);
            }
        });

        TNamaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNamaProdukActionPerformed(evt);
            }
        });

        TTotalBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TTotalBayarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Total Bayar");

        BTampil.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
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

        BEdit.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        BEdit.setText("Edit");
        BEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BEditMouseClicked(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(427, 427, 427)
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(TTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TIdProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addGap(58, 58, 58)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(TNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(BEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(BTampil)
                                .addGap(39, 39, 39)
                                .addComponent(BTambah))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelJumlah)
                                    .addComponent(TJumlahProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(BHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(THarga, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(245, 245, 245))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelJumlah)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TIdProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(THarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TJumlahProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BEdit)
                    .addComponent(BTampil)
                    .addComponent(BTambah)
                    .addComponent(BHapus))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TIdTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIdTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TIdTransaksiActionPerformed

    private void BHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHapusActionPerformed

    }//GEN-LAST:event_BHapusActionPerformed

    private void BTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTambahActionPerformed

    }//GEN-LAST:event_BTambahActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void TIdProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIdProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TIdProdukActionPerformed

    private void THargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_THargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_THargaActionPerformed

    private void TJumlahProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TJumlahProdukActionPerformed
tambahTransaksi();
    }//GEN-LAST:event_TJumlahProdukActionPerformed

    private void TNamaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNamaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNamaProdukActionPerformed

    private void TTotalBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TTotalBayarActionPerformed
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_TTotalBayarActionPerformed

    private void TbTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbTransaksiMouseClicked
    int row = TbTransaksi.getSelectedRow();
    if (row != -1) {
        String IdTransaksi = TbTransaksi.getValueAt(row, 0).toString();
        String IdProduk = TbTransaksi.getValueAt(row, 1).toString();
        String Nama = TbTransaksi.getValueAt(row, 2).toString();
        String Jumlah = TbTransaksi.getValueAt(row, 3).toString();
        String Harga = TbTransaksi.getValueAt(row, 4).toString();

        TIdTransaksi.setText(IdTransaksi);
        TIdProduk.setText(IdProduk);
        TNamaProduk.setText(Nama);
        TJumlahProduk.setText(Jumlah);
        THarga.setText(Harga);
    }
    }//GEN-LAST:event_TbTransaksiMouseClicked

    private void BTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTambahMouseClicked
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_BTambahMouseClicked

    private void BHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BHapusMouseClicked
        try {
            String idTransaksi = TIdTransaksi.getText();
            Connection con = Koneksi.getKoneksi();
            String sql = "DELETE FROM transaksi WHERE id_transaksi=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idTransaksi);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Berhasil menghapus");
                kosong();
                table();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus");
            }

            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_BHapusMouseClicked

    private void BTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTampilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTampilActionPerformed

    private void BEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BEditMouseClicked
try {
    String sql = "UPDATE transaksi SET id_produk=?, nama_produk=?, jumlah_produk=?, harga_produk=? WHERE id_produk=?";
    Connection con = Koneksi.getKoneksi();
    PreparedStatement pst = con.prepareStatement(sql);
    pst.setString(1, TIdProduk.getText());
    pst.setString(2, TNamaProduk.getText());
    pst.setString(3, TJumlahProduk.getText());
    pst.setString(4, THarga.getText());
    pst.setString(5, TIdProduk.getText());
    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "Berhasil edit");

    pst.close();
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Gagal edit: " + e.getMessage());
    e.printStackTrace();
}
table();
    }//GEN-LAST:event_BEditMouseClicked

    private void BTampilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTampilMouseClicked
    try {
        String sql = "SELECT*FROM transaksi WHERE id_transaksi='" + TIdTransaksi.getText() + "'";
        Connection con = (Connection) Koneksi.getKoneksi();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.execute();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    table();
    hapus();
    }//GEN-LAST:event_BTampilMouseClicked

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
            java.util.logging.Logger.getLogger(MenuTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BEdit;
    private javax.swing.JToggleButton BHapus;
    private javax.swing.JToggleButton BTambah;
    private javax.swing.JButton BTampil;
    private javax.swing.JLabel LabelJumlah;
    private javax.swing.JTextField THarga;
    private javax.swing.JTextField TIdProduk;
    private javax.swing.JTextField TIdTransaksi;
    private javax.swing.JTextField TJumlahProduk;
    private javax.swing.JTextField TNamaProduk;
    private javax.swing.JTextField TTotalBayar;
    private javax.swing.JTable TbTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton4;
    // End of variables declaration//GEN-END:variables
}
