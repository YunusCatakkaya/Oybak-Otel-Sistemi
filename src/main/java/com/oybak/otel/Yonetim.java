/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;
/**
 *
 * @author Yunus                       //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak
 */
public class Yonetim extends Personel implements VeriTabani, Hatalar{
    public Yonetim(String name, String lastName, long tcNo, double maas, String isTipi,String uzmanlikAlani,String parola) {
        super(name,lastName,tcNo,maas,"YONETIM",uzmanlikAlani,parola);
    }
    //aaaa
    
    /**
     * Sadece Yönetici sınıfına özel personel ekleme fonksiyonu.
     * Bu metod VeriTabanı interface'inde tanımlı değildir, 
     * bu yüzden sadece Yonetim nesneleri üzerinden erişilebilir.
     */
    
  
    
  public void personelEkle(Personel p) {
    // Sorguya uzmanlik_alanı eklendi (Toplam 6 adet '?' oldu)
    String sql = "INSERT INTO calisanlar(ad_soyad, tc_no, maas, is_tipi, Parola, uzmanlik_alani) VALUES(?,?,?,?,?,?)";
    
    try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, p.getName() + " " + p.getLastName());
        pstmt.setLong(2, p.getTcNo());
        pstmt.setDouble(3, p.getMaas());
        pstmt.setString(4, p.getIsTipi());
        pstmt.setString(5, p.getParola()); 
        pstmt.setString(6, p.getUzmanlikAlani()); // Personel nesnesinden gelen uzmanlık bilgisi

        pstmt.executeUpdate();
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Veritabanı Hatası: " + e.getMessage());
        e.printStackTrace();
        
    }
}
  
  public void personelSil(String adSoyad, String tcNoStr) {
    long tcNo = Long.parseLong(tcNoStr);
    String sql = "DELETE FROM calisanlar WHERE ad_soyad = ? AND tc_no = ?";

    try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, adSoyad);
        pstmt.setLong(2, tcNo);

        int sonuc = pstmt.executeUpdate();

        if (sonuc > 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "BAŞARILI: " + adSoyad + " sistemden silindi.");
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "UYARI: Girilen bilgilerle eşleşen kimse bulunamadı!");
        }
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null, "VERİTABANI HATASI: " + e.getMessage());
    }
}
  
  public void maasGuncelle(String adSoyad, String tcNoStr, String yeniMaasStr) {
   try {
        long tcNo = Long.parseLong(tcNoStr);
        // Veritabanı integer olduğu için tam sayıya çeviriyoruz
        int yeniMaas = Integer.parseInt(yeniMaasStr);

        String sql = "UPDATE calisanlar SET maas = ? WHERE ad_soyad = ? AND tc_no = ?";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, yeniMaas); // Integer olarak gönderiyoruz
            pstmt.setString(2, adSoyad);
            pstmt.setLong(3, tcNo);

            int etkilenenSatir = pstmt.executeUpdate();

            if (etkilenenSatir > 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "BAŞARILI: " + adSoyad + " isimli çalışanın maaşı " + yeniMaas + " TL olarak güncellendi.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "HATA: Girilen isim ve TC numarasıyla eşleşen bir kayıt bulunamadı!\nLütfen bilgileri kontrol ediniz.");
            }
        }
    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "HATA: Maaş alanına lütfen sadece tam sayı giriniz (Örn: 35000).");
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "VERİTABANI HATASI: " + e.getMessage());
    }
}
  
}
    
    
    
    
    
    
    
    
    
    
    
    
    
                        //ÇALIŞAN İŞLEMLERİ METOTLARI 
        // public void calisanEkle
        //public void calisanCikar
        //public void maasBelirle
        //a
    
    
                        //ÖZEL BİLGİ KASA İŞLEMLERİ
        //public void genelKasayıGoruntule
        //public void musteriTcSorgulama
        
    
    
                        //ODA İŞLEMLERİ
        //public void odaDuzenle  d


