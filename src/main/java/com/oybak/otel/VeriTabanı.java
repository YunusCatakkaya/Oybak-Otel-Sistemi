/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.UserRole;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ahmet
 */
public interface VeriTabanı {
    
    static final String URL = "jdbc:sqlite:veritabani_dosyan.db";
    
    public default Oda odaBilgileri(int oda_no){
        Oda bulunanOda = new Oda(); // Verileri dolduracağımız boş bir kova
        String odaNo = null;
        String sql = "SELECT * FROM odalar WHERE oda_no = '" + odaNo + "'";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(URL);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                // Veritabanından gelenleri nesneye doldur
                bulunanOda.setOdaNumarası(rs.getInt("oda_no"));
                bulunanOda.setOdaDurumu(com.oybak.otel.enums.OdaDurumu.valueOf(rs.getString("durum")));
                // Diğer set işlemlerini buraya ekle (tipi, ek özellikler vb.)
                System.out.println(odaNo + " verileri veritabanından çekildi.");
            }
        } catch (Exception e) {
            System.out.println("Sorgu hatası: " + e.getMessage());
        }
        
        return bulunanOda; // Dolu nesneyi geri gönder
    }
    
    public default UserRole calısanBilgileri(String tc, String parola){
        String sql = "SELECT is_tipi FROM calisanlar WHERE tc_no = ? AND Parola = ?";
    
        try (Connection baglanti = DriverManager.getConnection(URL);
            PreparedStatement sorgu = baglanti.prepareStatement(sql)) {

            // 2. Soru işaretlerini dolduruyoruz
            sorgu.setString(1, tc);
            sorgu.setString(2, parola);

            // 3. Sorguyu çalıştır ve sonucu al
            ResultSet sonuc = sorgu.executeQuery();

            if (sonuc.next()) { // Eğer böyle bir kullanıcı bulunduysa
                String veri = sonuc.getString("is_tipi"); // Veritabanından gelen yazı (örn: "YONETICI")
                // 4. Yazıyı Enum tipine çevir ve geri gönder
                return UserRole.valueOf(veri); 
            }

        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
        return null; // Kullanıcı bulunamadıysa boş dön
    }
}
