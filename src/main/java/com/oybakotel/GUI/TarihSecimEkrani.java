/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.oybakotel.GUI;
import com.oybak.otel.Resepsiyon;
import com.oybak.otel.Personel;
import com.oybak.otel.VeriTabani;


/**
 *
 * @author Yunus
 */
public class TarihSecimEkrani extends javax.swing.JFrame implements VeriTabani{
    
    private com.toedter.calendar.JDateChooser girisTakvim;
    private com.toedter.calendar.JDateChooser cikisTakvim;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TarihSecimEkrani.class.getName());

    private Personel p;
    private int odaNo = -1; // -1 ise normal giriş, değilse müşteri ekleme işlemidir
    private com.oybak.otel.Musteri gelenMusteri; 
    
    public TarihSecimEkrani(Personel p) {
        this.p = p;
        initComponents();
        
        
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); //tam ekran yapmak için
        
        // JFrame'in ana taşıyıcısının layout'unu GridBagLayout yapıyoruz
        this.getContentPane().setLayout(new java.awt.GridBagLayout());
    
        
        
        this.add(AnaPanel, new java.awt.GridBagConstraints());
        
        // Takvim nesnelerini oluşturuyoruz
    girisTakvim = new com.toedter.calendar.JDateChooser();
    cikisTakvim = new com.toedter.calendar.JDateChooser();

    // Giriş paneline (pnlGiris) takvimi tam sığacak şekilde 
    pnlGiris.setLayout(new java.awt.BorderLayout());
    pnlGiris.add(girisTakvim, java.awt.BorderLayout.CENTER);

    // Çıkış paneline (pnlCikis) takvimi tam sığacak şekilde 
    pnlCikis.setLayout(new java.awt.BorderLayout());
    pnlCikis.add(cikisTakvim, java.awt.BorderLayout.CENTER);

    // Görselin anında ekrana yansıması için panelleri yenile
    pnlGiris.revalidate();
    pnlGiris.repaint();
    pnlCikis.revalidate();
    pnlCikis.repaint();
    
    // --- TAKVİM KODLARI BİTİŞİ ---
    }
    
    
   
    public TarihSecimEkrani(Personel p, int odaNo, com.oybak.otel.Musteri m) {
        this.p = p;
        this.odaNo = odaNo;
        this.gelenMusteri = m; // Verileri paket olarak alınır
        
        initComponents();
        
        
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); // Tam ekran
        this.getContentPane().setLayout(new java.awt.GridBagLayout());
        this.add(AnaPanel, new java.awt.GridBagConstraints()); // Ekranı ortaya hizala

        // Takvim nesnelerini oluştur ve panellere yerleştir
        girisTakvim = new com.toedter.calendar.JDateChooser();
        cikisTakvim = new com.toedter.calendar.JDateChooser();
        
        pnlGiris.setLayout(new java.awt.BorderLayout());
        pnlGiris.add(girisTakvim, java.awt.BorderLayout.CENTER);
        
        pnlCikis.setLayout(new java.awt.BorderLayout());
        pnlCikis.add(cikisTakvim, java.awt.BorderLayout.CENTER);
        
        pnlGiris.revalidate();
        pnlGiris.repaint();
        pnlCikis.revalidate();
        pnlCikis.repaint();
    }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        AnaPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlGiris = new javax.swing.JPanel();
        pnlCikis = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        devamEt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AnaPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Konaklama Tarihleri ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 129;
        gridBagConstraints.ipady = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 94, 0, 6);
        AnaPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Giriş Tarihi :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 33, 0, 0);
        AnaPanel.add(jLabel2, gridBagConstraints);

        pnlGiris.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout pnlGirisLayout = new javax.swing.GroupLayout(pnlGiris);
        pnlGiris.setLayout(pnlGirisLayout);
        pnlGirisLayout.setHorizontalGroup(
            pnlGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );
        pnlGirisLayout.setVerticalGroup(
            pnlGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 184;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 0);
        AnaPanel.add(pnlGiris, gridBagConstraints);

        pnlCikis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout pnlCikisLayout = new javax.swing.GroupLayout(pnlCikis);
        pnlCikis.setLayout(pnlCikisLayout);
        pnlCikisLayout.setHorizontalGroup(
            pnlCikisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );
        pnlCikisLayout.setVerticalGroup(
            pnlCikisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 184;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(68, 4, 0, 0);
        AnaPanel.add(pnlCikis, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Çıkış Tarihi :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(78, 23, 0, 0);
        AnaPanel.add(jLabel3, gridBagConstraints);

        devamEt.setText("Devam Et");
        devamEt.addActionListener(this::devamEtActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 109, 48, 0);
        AnaPanel.add(devamEt, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AnaPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AnaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Veritabanı kayıt ve yönlendirme işlemlerini arka planda da yapabilmek için ayırdık
  public void veritabaninaIsleVeKontrolEt(String girisStr, String cikisStr) {
        
        
        int sonuc = com.oybak.otel.Resepsiyon.musteriEkle(odaNo, gelenMusteri, girisStr, cikisStr);
        logKayit(p.bilgileriYazdir(), " " + odaNo + " nolu odaya"+ gelenMusteri.bilgileriYazdir() +" müşterisini ekledi.");

        if (sonuc == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "HATA: Bu oda tam kapasite dolu!");
            return;
        } 
        else if (sonuc == -2) {
            javax.swing.JOptionPane.showMessageDialog(this, "Veritabanı Hatası oluştu!");
            return;
        } 
        else if (sonuc == 1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Müşteri eklendi! Oda kapasitesi tam dolduğu için oda 'DOLU' durumuna getirildi.");
            
            com.oybakotel.GUI.OdaSecimEkrani odaSecim = new com.oybakotel.GUI.OdaSecimEkrani(p);
            odaSecim.setLocationRelativeTo(null);
            odaSecim.setVisible(true);
            this.dispose();
        } 
        else if (sonuc == 0) {
            int cevap = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Müşteri eklendi.\nBu odaya başka bir müşteri daha ekleyecek misiniz?", 
                "Kayıt Başarılı",                 
                javax.swing.JOptionPane.YES_NO_OPTION);
                            
            if (cevap == javax.swing.JOptionPane.YES_OPTION) {
                com.oybak.otel.GUIResepsiyon.MusteriEkleme yeniEkleme = new com.oybak.otel.GUIResepsiyon.MusteriEkleme(p, odaNo, girisStr, cikisStr);
                yeniEkleme.setLocationRelativeTo(null);
                yeniEkleme.setVisible(true);
                this.dispose();
            } else {
                Resepsiyon.odayiDoluYap(odaNo);
                
                com.oybakotel.GUI.OdaSecimEkrani odaSecim = new com.oybakotel.GUI.OdaSecimEkrani(p);
                odaSecim.setLocationRelativeTo(null);
                odaSecim.setVisible(true);
                this.dispose();
            }
        }
    }
    
    private void devamEtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_devamEtActionPerformed
    java.util.Date giris = girisTakvim.getDate();
        java.util.Date cikis = cikisTakvim.getDate();
        java.util.Date bugun = new java.util.Date(); //bugunun tarıhı alınır
        
        // KONTROL 1: Boşluk Kontrolü
        if (giris == null || cikis == null) {
            javax.swing.JOptionPane.showMessageDialog(this, 
            "Lütfen konaklama yapacağınız giriş ve çıkış tarihlerini seçiniz!", 
            "Eksik Bilgi", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // KONTROL 2: Geçmiş Tarih Kontrolü 
        if (giris.before(new java.util.Date(bugun.getTime() - (1000 * 60 * 60 * 24)))) {
            javax.swing.JOptionPane.showMessageDialog(this, 
            "Giriş tarihi geçmiş bir tarih olamaz!", 
            "Geçersiz Tarih", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // KONTROL 3: Çıkış Tarihi Kontrolü
        if (cikis.before(giris) || cikis.equals(giris)) {
            javax.swing.JOptionPane.showMessageDialog(this, 
            "Çıkış tarihi, giriş tarihinden en az 1 gün sonra olmalıdır!", 
            "Mantık Hatası", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        System.out.println("Başarılı! Giriş: " + giris + " | Çıkış: " + cikis);
        
        
        if (odaNo != -1) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy");
            String girisStr = sdf.format(giris);
            String cikisStr = sdf.format(cikis);
            
            veritabaninaIsleVeKontrolEt(girisStr, cikisStr);
            return; 
            
        } else {
            // Eğer oda numarası -1 ise (yani yeni rezervasyonsa) Oda Seçim Ekranını aç
            com.oybakotel.GUI.OdaSecimEkrani odaEkrani = new com.oybakotel.GUI.OdaSecimEkrani(p);
            odaEkrani.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_devamEtActionPerformed

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
            // Test edebilmek için sahte (dummy) bir personel oluşturuyoruz
            Personel p = new Personel("Test", 12345678916L, 0, null, "123"); 
            new TarihSecimEkrani(p).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AnaPanel;
    private javax.swing.JButton devamEt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel pnlCikis;
    private javax.swing.JPanel pnlGiris;
    // End of variables declaration//GEN-END:variables
}
