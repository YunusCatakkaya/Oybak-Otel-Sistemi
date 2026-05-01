/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Yunus                       //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak
 */
public class Yonetim extends Personel implements VeriTabani{
    public Yonetim(String name, String lastName, long tcNo, double maas, String isTipi,String uzmanlikAlani) {
        super(name,lastName,tcNo,maas,"Yonetim",uzmanlikAlani);
    }
    
    
    /**
     * Sadece Yönetici sınıfına özel personel ekleme fonksiyonu.
     * Bu metod VeriTabanı interface'inde tanımlı değildir, 
     * bu yüzden sadece Yonetim nesneleri üzerinden erişilebilir.
     */
    
  
    
  public void personelEkle(Personel p) {
    // Sorguya uzmanlik_alanı eklendi (Toplam 6 adet '?' oldu)
    String sql = "INSERT INTO calisanlar(ad_soyad, tc_no, maas, is_tipi, Parola, uzmanlik_alanı) VALUES(?,?,?,?,?,?)";
    
    try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, p.getName() + " " + p.getLastName());
        pstmt.setLong(2, p.getTcNo());
        pstmt.setDouble(3, p.getMaas());
        pstmt.setString(4, p.getIsTipi());
        pstmt.setString(5, "123"); // Varsayılan şifre
        pstmt.setString(6, p.getUzmanlikAlani()); // Personel nesnesinden gelen uzmanlık bilgisi

        pstmt.executeUpdate();
    } catch (java.sql.SQLException e) {
        System.out.println("Kayıt Hatası: " + e.getMessage());
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


