/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;
import com.oybak.otel.enums.UserRole;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//jjjj
/**
 *
 * @author ahmet
 */
public interface VeriTabani {
    
    static final String URL = "jdbc:sqlite:veritabani_dosyan.db";
    
    public default Oda odaBilgileri(int oda_no) {
        Oda bulunanOda = null; // Başlangıçta boş bırakıyoruz
        String sql = "SELECT * FROM odalar WHERE oda_no = ?";

        try (Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        
            pstmt.setInt(1, oda_no);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bulunanOda = new Oda(); 
                                    
                    bulunanOda.setOdaNumarasi(rs.getInt("oda_no"));
                    bulunanOda.setTekKisilikYatak(rs.getInt("tek_kisilik_yatak"));
                    bulunanOda.setCiftKisilikYatak(rs.getInt("cift_kisilik_yatak"));                    
                    bulunanOda.setDenizManzarasi(Boolean.parseBoolean(rs.getString("deniz_manzarasi")));
                    bulunanOda.setBalkon(Boolean.parseBoolean(rs.getString("balkon")));
                    bulunanOda.setJakuzi(Boolean.parseBoolean(rs.getString("jakuzi")));
                    bulunanOda.setFiyat(rs.getInt("fiyat"));
                    bulunanOda.setOdenmeDurumu(Boolean.parseBoolean(rs.getString("odenme_durumu")));
                    bulunanOda.setBakimSebebi(rs.getString("bakim_sebebi"));                                   
                                                      
                    String durumString = rs.getString("durum");
                    if (durumString != null) {
                        bulunanOda.setOdaDurumu(OdaDurumu.valueOf(durumString.toUpperCase().trim()));
                    }
                
                    System.out.println(oda_no + " numaralı oda verileri çekildi.");
                }
            }
        } catch (Exception e) {
            System.out.println("Oda sorgulama hatası: " + e.getMessage());
        }
        return bulunanOda; // Oda bulunamazsa null, bulunursa dolu nesne döner
    }   
 
    public default Personel calısanBilgileri(String tc, String parola){
        Personel calisan = null;
        String sql = "SELECT * FROM calisanlar WHERE tc_no = ? AND Parola = ?";
    
        try (Connection baglanti = DriverManager.getConnection(URL);
            PreparedStatement sorgu = baglanti.prepareStatement(sql)) {
            
            sorgu.setString(1, tc);
            sorgu.setString(2, parola);
            
            ResultSet sonuc = sorgu.executeQuery();

            if (sonuc.next()) { // Eğer böyle bir kullanıcı bulunduysa
                UserRole rol = UserRole.valueOf(sonuc.getString("is_tipi"));
                calisan = new Personel(sonuc.getString("ad_soyad"), sonuc.getLong("tc_no"), sonuc.getInt("maas"),rol,sonuc.getString("parola"));
            }

        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        } // Kullanıcı bulunamadıysa boş dön
        return calisan;
    } 
    
    public default void logKayit(String kullanici,String islem){
        String sql = "INSERT INTO log_kayitlar (tarih_saat, aciklama) VALUES (?, ?)";
    
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
            LocalDateTime simdi = LocalDateTime.now();
            DateTimeFormatter formatci = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatliSimdi = simdi.format(formatci);
        
            pstmt.setString(1, formatliSimdi);
            pstmt.setString(2, kullanici + islem);

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Log Kayıt Hatası: " + e.getMessage());
        }
    }
}   