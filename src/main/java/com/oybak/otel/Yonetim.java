/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 *
 * @author Yunus                       //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak
 */
public class Yonetim extends Personel implements VeriTabani{

    public Yonetim(String name, String lastName, long tcNo, double maas, String isTipi) {
        super(name,lastName,tcNo,maas,"Yonetim");
    }
    
    
    /**
     * Sadece Yönetici sınıfına özel personel ekleme fonksiyonu.
     * Bu metod VeriTabanı interface'inde tanımlı değildir, 
     * bu yüzden sadece Yonetim nesneleri üzerinden erişilebilir.
     */
    
   public boolean tcVarMi(long kontrolTc) {
    boolean sonuc = false;
    // Veritabanı yolun (image_4bac41.jpg'deki yolun aynısı)
    String url = "jdbc:sqlite:C:/Users/onuro/OneDrive/Belgeler/NetBeansProjects/Oybak-Otel-Sistemi/veritabani_dosyan.db";
    String sql = "SELECT tc_no FROM calisanlar WHERE tc_no = ?"; 
    
    try (Connection conn = java.sql.DriverManager.getConnection(url);
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setLong(1, kontrolTc);
        java.sql.ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            sonuc = true; 
        }
    } catch (java.sql.SQLException e) {
        System.out.println("TC Sorgulama Hatası: " + e.getMessage());
    }
    return sonuc;
}
    
    public void personelEkle(Personel yeniPersonel) {
        // SQL sorgusunu hazırlıyoruz
        String sql = "INSERT INTO calisanlar (ad, soyad, tc_no, maas, is_tipi) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // VeriTabanı interface'indeki URL'yi kullanıyoruz: image_693573.png
        try (Connection conn = DriverManager.getConnection(VeriTabani.URL); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Personel nesnesinden verileri alıp sorguya yerleştiriyoruz
            pstmt.setString(1, yeniPersonel.getName());
            pstmt.setString(2, yeniPersonel.getLastName());
            pstmt.setLong(3, yeniPersonel.getTcNo());
            pstmt.setDouble(6, yeniPersonel.getMaas());
            pstmt.setString(7, yeniPersonel.getIsTipi());

            pstmt.executeUpdate();
            System.out.println("Sistem Mesajı: " + yeniPersonel.getName() + " isimli yeni personel yönetici tarafından eklendi.");
            
        } catch (Exception e) {
            System.out.println("Personel eklenirken yetki/bağlantı hatası oluştu: " + e.getMessage());
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


