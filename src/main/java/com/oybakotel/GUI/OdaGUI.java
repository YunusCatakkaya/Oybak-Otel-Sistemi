/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.oybakotel.GUI;


import com.oybak.otel.GUITeknikEkip.BakımSebebiPopupGUI;
import com.oybak.otel.Oda;
import com.oybak.otel.Personel;
import com.oybak.otel.TeknikEkip;
import javax.swing.JOptionPane;
import com.oybak.otel.VeriTabani;
import static com.oybak.otel.enums.OdaDurumu.BAKIMDA;
import static com.oybak.otel.enums.OdaDurumu.DOLU;
import static com.oybak.otel.enums.OdaDurumu.MUSAIT;
import static com.oybak.otel.enums.UserRole.BAKIM;
import static com.oybak.otel.enums.UserRole.RESEPSIYON;

/**
 *
 * @author yunus
 */
public class OdaGUI extends javax.swing.JFrame implements VeriTabani{
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OdaGUI.class.getName());

    /**
     * Creates new form OdaGUI
     */
    private int secilenOda;
    private Personel p;

   // Parametreli Constructor: Bu ekran açılırken bir Oda nesnesi bekler
    public OdaGUI(int oda, Personel p) {

        this.secilenOda = oda; // Gelen oda bilgisini kaydet
        this.p = p;
        initComponents();    // Verileri arayüze bas

        // 1. Oda Numarasını Yazdır
        OdaNo.setText("Oda No: " + this.secilenOda);

        // 2. VERİTABANINDAN ÖZELLİKLERİ ÇEKİP YAZDIRMA 
        Oda aktifOda = odaBilgileri(this.secilenOda);
        
        if (aktifOda != null) {
            // İŞTE BÜTÜN SİHİR BURADA:
            // HTML oluşturma işini Oda nesnesine devrettik, biz sadece sonucu alıp yazdırıyoruz.
            Ozellikler.setText(aktifOda.getOzelliklerHTML());
        } else {
            Ozellikler.setText("Oda bilgileri veritabanından çekilemedi.");
        }

        setLocationRelativeTo(null);
        
        rolleriAyarla();
        musteriBilgileriniYukle();
    }
    
    
    
    private void rolleriAyarla() {
    // 1. Önce tüm özel panelleri gizle (Varsayılan durum)
    teknikPersonelPaneli.setVisible(false);
    bakimSebebiPaneli.setVisible(false);
    musteriEklemePaneli.setVisible(false);
    musteriBilgileriPaneli.setVisible(false);

    // 2. Aktif role göre panelleri görünür yap
    switch (p.getIsTipi()) {
        case TEKNIKPERSONEL ->{
            bakimSebebiPaneli.setVisible(true); // Bakım sebebi yazısını da görmeleri gerekir
        }
        case BAKIM ->{
            teknikPersonelPaneli.setVisible(true);
            bakimSebebiPaneli.setVisible(true);
        }
        case RESEPSIYON -> {
            musteriBilgileriPaneli.setVisible(true);
        }
        case MUSTERIEKLEME -> {
            musteriEklemePaneli.setVisible(true);
            musteriBilgileriPaneli.setVisible(true);
        }
        // Diğer roller (YÖNETİM, MÜŞTERİ vb.) için paneller kapalı kalmaya devam eder
        default -> {
            // İsteğe bağlı: Varsayılan bir işlem buraya eklenebilir
        }
    }
    }
    
    private void musteriBilgileriniYukle() {
        Oda geciciOda = odaBilgileri(this.secilenOda);

        // Eğer oda DOLU ise müşteri bilgilerini veritabanından çekip yazdır
        if (geciciOda != null && geciciOda.getOdaDurumu() == com.oybak.otel.enums.OdaDurumu.DOLU) {
            
            // Kullanıcının rolü bu paneli görmeye yetiyorsa (rolleriAyarla metodundan geçebildiyse)
            if (musteriBilgileriPaneli.isVisible()) {
                // TC no'yu SQL sorgusundan ve ekrandan çıkardık
                String sql = "SELECT ad_soyad, giris_tarihi, cikis_tarihi FROM guncel_musteriler WHERE oda_no = ?";
                StringBuilder sb = new StringBuilder();
                sb.append("<html>");
                
                try (java.sql.Connection conn = java.sql.DriverManager.getConnection(com.oybak.otel.VeriTabani.URL);
                     java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
                     
                    pstmt.setString(1, String.valueOf(this.secilenOda));
                    
                    try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                        int kisiSayisi = 1;
                        while (rs.next()) {
                            // Sadece Müşteri adı ve tarihleri ekrana basıyoruz
                            sb.append("<b>").append(kisiSayisi).append(". Müşteri:</b> ").append(rs.getString("ad_soyad")).append("<br>");
                            sb.append("<b>Giriş:</b> ").append(rs.getString("giris_tarihi"))
                              .append(" &nbsp;|&nbsp; <b>Çıkış:</b> ").append(rs.getString("cikis_tarihi")).append("<br><br>");
                            kisiSayisi++;
                        }
                        
                        if (kisiSayisi == 1) { 
                            sb.append("Oda dolu görünüyor ancak müşteri kaydı bulunamadı.");
                        }
                    }
                } catch (Exception e) {
                    sb.append("Veri tabanından bilgiler çekilirken hata oluştu: ").append(e.getMessage());
                }
                
                sb.append("</html>");
                Bilgiler.setText(sb.toString()); // Çekilen veriyi Label'a bas
            }
        } else {
            // Oda DOLU değilse (Müsait, Bakımda vb.) müşteri paneli tamamen SAKLANIR
            musteriBilgileriPaneli.setVisible(false);
        }
    }

    private OdaGUI() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        SabitMetin = new javax.swing.JLabel();
        OdaNo = new javax.swing.JLabel();
        Ozellikler = new javax.swing.JLabel();
        teknikPersonelPaneli = new javax.swing.JPanel();
        BakimAl = new javax.swing.JButton();
        BakımdanCikar = new javax.swing.JButton();
        bakimSebebiPaneli = new javax.swing.JPanel();
        BakımSebebi = new javax.swing.JLabel();
        musteriBilgileriPaneli = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Bilgiler = new javax.swing.JLabel();
        musteriEklemePaneli = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        SabitMetin.setText("Özellikler:");

        OdaNo.setText("Oda No");

        Ozellikler.setText("sırayla özellikler");

        teknikPersonelPaneli.setBackground(new java.awt.Color(255, 255, 255));

        BakimAl.setText("Bakıma Al");
        BakimAl.addActionListener(this::BakimAlActionPerformed);

        BakımdanCikar.setText("Bakımdan Çıkar");
        BakımdanCikar.addActionListener(this::BakımdanCikarActionPerformed);

        javax.swing.GroupLayout teknikPersonelPaneliLayout = new javax.swing.GroupLayout(teknikPersonelPaneli);
        teknikPersonelPaneli.setLayout(teknikPersonelPaneliLayout);
        teknikPersonelPaneliLayout.setHorizontalGroup(
            teknikPersonelPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teknikPersonelPaneliLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(teknikPersonelPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BakımdanCikar)
                    .addComponent(BakimAl, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        teknikPersonelPaneliLayout.setVerticalGroup(
            teknikPersonelPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teknikPersonelPaneliLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(BakimAl, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BakımdanCikar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        bakimSebebiPaneli.setBackground(new java.awt.Color(255, 255, 255));

        BakımSebebi.setText("Bakım sebebi");

        javax.swing.GroupLayout bakimSebebiPaneliLayout = new javax.swing.GroupLayout(bakimSebebiPaneli);
        bakimSebebiPaneli.setLayout(bakimSebebiPaneliLayout);
        bakimSebebiPaneliLayout.setHorizontalGroup(
            bakimSebebiPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bakimSebebiPaneliLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BakımSebebi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        bakimSebebiPaneliLayout.setVerticalGroup(
            bakimSebebiPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bakimSebebiPaneliLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BakımSebebi)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        BakımSebebi.getAccessibleContext().setAccessibleName("Bakım Sebebi");

        musteriBilgileriPaneli.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Müşteri Bilgileri:");

        Bilgiler.setText("Bilgiler");

        javax.swing.GroupLayout musteriBilgileriPaneliLayout = new javax.swing.GroupLayout(musteriBilgileriPaneli);
        musteriBilgileriPaneli.setLayout(musteriBilgileriPaneliLayout);
        musteriBilgileriPaneliLayout.setHorizontalGroup(
            musteriBilgileriPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, musteriBilgileriPaneliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(musteriBilgileriPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Bilgiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addContainerGap())
        );
        musteriBilgileriPaneliLayout.setVerticalGroup(
            musteriBilgileriPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(musteriBilgileriPaneliLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bilgiler, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        musteriEklemePaneli.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setText("Müşteri ekle");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout musteriEklemePaneliLayout = new javax.swing.GroupLayout(musteriEklemePaneli);
        musteriEklemePaneli.setLayout(musteriEklemePaneliLayout);
        musteriEklemePaneliLayout.setHorizontalGroup(
            musteriEklemePaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(musteriEklemePaneliLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        musteriEklemePaneliLayout.setVerticalGroup(
            musteriEklemePaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(musteriEklemePaneliLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(SabitMetin)
                        .addGap(102, 102, 102))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(OdaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ozellikler))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(musteriEklemePaneli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(musteriBilgileriPaneli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bakimSebebiPaneli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(teknikPersonelPaneli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(musteriBilgileriPaneli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(teknikPersonelPaneli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bakimSebebiPaneli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addComponent(musteriEklemePaneli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(OdaNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SabitMetin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ozellikler)))
                .addGap(55, 55, 55))
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

    private void BakimAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BakimAlActionPerformed
        Oda geciciOda = odaBilgileri(this.secilenOda);
        switch(geciciOda.getOdaDurumu()){
            case BAKIMDA -> JOptionPane.showMessageDialog(this, "Oda zaten bakımda!");
            case DOLU -> JOptionPane.showMessageDialog(this, "Odada müşteri var. Bakım yapılamıyor!");
            case MUSAIT -> {
                BakımSebebiPopupGUI popup = new BakımSebebiPopupGUI(this, true, secilenOda, p); 
                popup.setLocationRelativeTo(this); // Pop-up'ın ana pencerenin tam ortasında fırlamasını sağlar
                popup.setVisible(true);
                this.dispose();
            }
        }   // TODO add your handling code here:
    }//GEN-LAST:event_BakimAlActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        com.oybak.otel.GUIResepsiyon.MusteriEkleme musteriEkleme = new com.oybak.otel.GUIResepsiyon.MusteriEkleme(p, secilenOda);
        musteriEkleme.setLocationRelativeTo(null); // Ekranı tam ortaya hizala
        musteriEkleme.setVisible(true); // Ekranı görünür yap
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BakımdanCikarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BakımdanCikarActionPerformed
        Oda geciciOda = odaBilgileri(this.secilenOda);
        switch(geciciOda.getOdaDurumu()){
            case BAKIMDA ->{ 
                TeknikEkip.odaBakimdanCikar(this.secilenOda);
                JOptionPane.showMessageDialog(this, "Oda bakımdan çıkarıldı.");
                logKayit(p.bilgileriYazdir() ," " +secilenOda +" numaralı odayı bakımdan çıkardı.");
                this.dispose();
            }
            case DOLU -> JOptionPane.showMessageDialog(this, "Odada müşteri var. Bakım yapılamıyor!");
            case MUSAIT -> JOptionPane.showMessageDialog(this, "Odada bakımda değil.!");
        }// TODO add your handling code here:
    }//GEN-LAST:event_BakımdanCikarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new OdaGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BakimAl;
    private javax.swing.JLabel BakımSebebi;
    private javax.swing.JButton BakımdanCikar;
    private javax.swing.JLabel Bilgiler;
    private javax.swing.JLabel OdaNo;
    private javax.swing.JLabel Ozellikler;
    private javax.swing.JLabel SabitMetin;
    private javax.swing.JPanel bakimSebebiPaneli;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel musteriBilgileriPaneli;
    private javax.swing.JPanel musteriEklemePaneli;
    private javax.swing.JPanel teknikPersonelPaneli;
    // End of variables declaration//GEN-END:variables
}
