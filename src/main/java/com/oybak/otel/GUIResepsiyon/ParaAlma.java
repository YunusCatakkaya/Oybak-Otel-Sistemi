/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.oybak.otel.GUIResepsiyon;
import com.oybak.otel.Personel;
import com.oybak.otel.VeriTabani;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author onuro
 */
public class ParaAlma extends javax.swing.JFrame implements VeriTabani { //sd
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ParaAlma.class.getName());

    //private com.oybak.otel.enums.UserRole aktifRol; // Mevcut rolü saklamak için
    
    /**
     * Creates new form ParaAlma
     */
    private Personel p;
    
    public ParaAlma(Personel p) {
        initComponents();
        doluOdalariYukle();
        odaComboBoxActionPerformed(null);
        this.p = p;
    }
    
    // Sınıfınızın içinde uygun bir yere bu metodu ekleyin:
private void doluOdalariYukle() {
    odaComboBox.removeAllItems(); // Önce eski verileri temizle
    
    // Sadece durumu DOLU olan ve ödemesi henüz alınmamış odaları getir
        String sql = "SELECT oda_no FROM odalar WHERE durum = 'DOLU' AND (odenme_durumu = 'false' OR odenme_durumu IS NULL)";
        
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(com.oybak.otel.VeriTabani.URL);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                odaComboBox.addItem(String.valueOf(rs.getInt("oda_no")));
            }
        } catch (Exception e) {
            System.out.println("Oda yükleme hatası: " + e.getMessage());
        }
}


// 2. Yeni bir metot ekliyoruz: Gün sayısını hesaplayıp toplam fiyatı bulur
    private double hesaplaToplamFiyat(int odaNo) {
        double tabanFiyat = 0;
        long gunSayisi = 1; // Varsayılan en az 1 gün
        
        // Odalar tablosundan taban fiyatı al
        String sqlOda = "SELECT fiyat FROM odalar WHERE oda_no = ?";
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(com.oybak.otel.VeriTabani.URL);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sqlOda)) {
            pstmt.setInt(1, odaNo);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tabanFiyat = rs.getDouble("fiyat");
                }
            }
        } catch (Exception e) {
            System.err.println("Fiyat çekme hatası: " + e.getMessage());
            return -1;
        }

        // Guncel musteriler tablosundan kalma süresini al (1 müşteri üzerinden)
        String sqlMusteri = "SELECT giris_tarihi, cikis_tarihi FROM guncel_musteriler WHERE oda_no = ? LIMIT 1";
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(com.oybak.otel.VeriTabani.URL);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sqlMusteri)) {
            pstmt.setInt(1, odaNo);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String girisStr = rs.getString("giris_tarihi");
                    String cikisStr = rs.getString("cikis_tarihi");
                    
                    if (girisStr != null && cikisStr != null && !girisStr.isEmpty() && !cikisStr.isEmpty()) {
                        // TARİH FORMATINI SENİN SİSTEMİNE (dd.MM.yyyy) UYGUN HALE GETİRDİK
                        DateTimeFormatter formatlayici = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        
                        LocalDate giris = LocalDate.parse(girisStr, formatlayici);
                        LocalDate cikis = LocalDate.parse(cikisStr, formatlayici);
                        
                        gunSayisi = ChronoUnit.DAYS.between(giris, cikis);
                        if (gunSayisi <= 0) gunSayisi = 1; // Aynı gün çıkış yaparsa 1 günlük ücret al
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Tarih çekme hatası: " + e.getMessage());
        }

        return tabanFiyat * gunSayisi;
    }       

private double odenenMiktariGetir(int odaNo) {
    double odenen = 0.0;
    // guncel_musteriler tablosundan bu odaya ait kasa_katki değerini çekiyoruz
    String sql = "SELECT kasa_katki FROM guncel_musteriler WHERE oda_no = ?";
    
    try (java.sql.Connection conn = java.sql.DriverManager.getConnection(com.oybak.otel.VeriTabani.URL);
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        // Veritabanında oda_no TEXT olarak tutulduğu için String'e çeviriyoruz[cite: 1]
        pstmt.setString(1, String.valueOf(odaNo)); 
        java.sql.ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            odenen = rs.getDouble("kasa_katki");
        }
    } catch (Exception e) {
        System.out.println("Ödeme Sorgu Hatası: " + e.getMessage());
    }
    return odenen;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        odaComboBox = new javax.swing.JComboBox<>();
        fiyatText = new javax.swing.JLabel();
        odemeAl = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        odaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        odaComboBox.addActionListener(this::odaComboBoxActionPerformed);

        fiyatText.setText("     Fiyat: 0.0 TL");

        odemeAl.setText("Ödeme Al");
        odemeAl.addActionListener(this::odemeAlActionPerformed);

        jLabel1.setText("Ödeme Alacağınız Odayı Seçiniz");

        jButton1.setText("Geri");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(odemeAl)
                    .addComponent(odaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(fiyatText)
                .addGap(210, 210, 210))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(odaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(fiyatText, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(odemeAl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void odaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odaComboBoxActionPerformed
    Object secilen = odaComboBox.getSelectedItem();
        if (secilen == null) {
            fiyatText.setText("Fiyat: Seçili Oda Yok");
            return;
        }

        try {
            int odaNo = Integer.parseInt(secilen.toString());
            double toplamFiyat = hesaplaToplamFiyat(odaNo);
            
            if (toplamFiyat >= 0) {
                fiyatText.setText("Fiyat: " + toplamFiyat + " TL");
            } else {
                fiyatText.setText("Fiyat: Hata/Bulunamadı");
            }
        } catch (Exception e) {
            fiyatText.setText("Fiyat: Hata");
        }
    }//GEN-LAST:event_odaComboBoxActionPerformed

    private void odemeAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odemeAlActionPerformed
    Object secilen = odaComboBox.getSelectedItem();
        if (secilen == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Lütfen bir oda seçiniz!");
            return;
        }

        int odaNo = Integer.parseInt(secilen.toString());
        double odaFiyati = hesaplaToplamFiyat(odaNo);

        if (odaFiyati > 0) {
            // 1. Odanın ödenme durumunu true yapacak sorgu
            String sqlOdaGuncelle = "UPDATE odalar SET odenme_durumu = 'true' WHERE oda_no = ?";
            
            // 2. Odaya giren İLK müşteriyi (kime ödeme yazılacağını) bulma sorgusu
            // rowid sıralaması bize o odaya ilk eklenen kişiyi verir
            String sqlIlkMusteriBul = "SELECT tc_no FROM guncel_musteriler WHERE oda_no = ? ORDER BY rowid ASC LIMIT 1";
            
            // 3. Sadece bulduğumuz o kişinin kasasına parayı ekleyecek sorgu
            String sqlMusteriGuncelle = "UPDATE guncel_musteriler SET kasa_katki = COALESCE(kasa_katki, 0) + ? WHERE tc_no = ?";
            
            try (java.sql.Connection conn = java.sql.DriverManager.getConnection(com.oybak.otel.VeriTabani.URL)) {
                
                // İki işlemi aynı anda ve hatasız yapmak için otomatik kaydetmeyi kapatıyoruz (Transaction)
                conn.setAutoCommit(false); 
                
                try (java.sql.PreparedStatement pstmtOda = conn.prepareStatement(sqlOdaGuncelle);
                     java.sql.PreparedStatement pstmtMusteriBul = conn.prepareStatement(sqlIlkMusteriBul);
                     java.sql.PreparedStatement pstmtMusteri = conn.prepareStatement(sqlMusteriGuncelle)) {
                    
                    // 1. Adım: Oda ödenme durumunu güncelle
                    pstmtOda.setInt(1, odaNo);
                    pstmtOda.executeUpdate();
                    
                    // 2. Adım: İlk müşteriyi bul
                    pstmtMusteriBul.setInt(1, odaNo);
                    String ilkMusteriTc = null;
                    try (java.sql.ResultSet rs = pstmtMusteriBul.executeQuery()) {
                        if (rs.next()) {
                            ilkMusteriTc = rs.getString("tc_no"); // İlk eklenen kişinin tc_no'sunu aldık
                        }
                    }
                    
                    // 3. Adım: Eğer ilk müşteri bulunduysa sadece onun hesabını güncelle
                    if (ilkMusteriTc != null) {
                        pstmtMusteri.setDouble(1, odaFiyati);
                        pstmtMusteri.setString(2, ilkMusteriTc);
                        pstmtMusteri.executeUpdate();
                    }
                    
                    // Tüm işlemler başarılıysa veritabanına onayla/kaydet (Commit)
                    conn.commit();
                    
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        odaNo + " nolu odanın " + odaFiyati + " TL tutarındaki ödemesi alınmış ve ana müşteri (TC: " + ilkMusteriTc + ") hesabına işlenmiştir.", 
                        "Ödeme Başarılı", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    logKayit(p.bilgileriYazdir() ," " +odaNo +" numaralı odanın ücreti"+ilkMusteriTc + " TC'li müşteriden alındı");
                    
                    // Listeden seçili odayı sil
                    odaComboBox.removeItem(secilen);
                    
                    // Varsa sıradaki odayı seç, yoksa uyarı ver
                    if (odaComboBox.getItemCount() > 0) {
                        odaComboBox.setSelectedIndex(0);
                    } else {
                        fiyatText.setText("Fiyat: Ödenecek Oda Kalmadı");
                    }
                    
                } catch (Exception ex) {
                    // Herhangi bir hata olursa işlemleri geri al (Rollback)
                    conn.rollback();
                    javax.swing.JOptionPane.showMessageDialog(this, "Güncelleme sırasında hata oluştu: " + ex.getMessage(), "Hata", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Veritabanı bağlantı hatası: " + e.getMessage(), "Hata", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Hata: Veritabanında " + odaNo + " nolu odanın fiyatı 0.0 veya boş görünüyor!", 
                "Fiyat Hatası", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_odemeAlActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Personel p = new Personel("Test", 12345678916L, 0, null, "123");
            // Test amaçlı varsayılan bir rol (örneğin YONETIM) gönderiyoruz
            new MusteriArama(p).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fiyatText;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> odaComboBox;
    private javax.swing.JButton odemeAl;
    // End of variables declaration//GEN-END:variables
}
