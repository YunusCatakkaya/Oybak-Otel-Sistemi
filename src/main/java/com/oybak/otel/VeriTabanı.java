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
import java.util.ArrayList;
import java.util.List;
//jjjj
/**
 *
 * @author ahmet
 */
public interface VeriTabanı {
    
    static final String URL = "jdbc:sqlite:veritabani_dosyan.db";
    
    public default Oda odaBilgileri(int oda_no) {
        Oda bulunanOda = null; // Başlangıçta boş bırakıyoruz
        String sql = "SELECT * FROM odalar WHERE oda_no = ?";

        try (Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // 1. Parametre olarak gelen oda_no'yu sorguya yerleştiriyoruz
            pstmt.setInt(1, oda_no);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bulunanOda = new Oda(); // Oda bulundu, nesneyi oluştur
                
                    // 2. Veritabanındaki sütun isimlerine göre verileri çekip nesneye doldur
                    bulunanOda.setOdaNumarası(rs.getInt("oda_no"));
                    bulunanOda.setTekKisilikYatak(rs.getInt("tek_kisilik_yatak"));
                    bulunanOda.setCiftKisilikYatak(rs.getInt("cift_kisilik_yatak"));
                    bulunanOda.setDenizManzarasi(rs.getBoolean("deniz_manzarasi"));
                    bulunanOda.setMinibar(rs.getBoolean("minibar"));
                    bulunanOda.setJakuzi(rs.getBoolean("jakuzi"));
                
                    // 3. Durum bilgisini Enum'a çevirirken hata payını azaltmak için büyük harf yapıyoruz
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
    
    public default List<Oda> doluOdaListesi() {
    List<Oda> doluOdalar = new ArrayList<>();
    String sql = "SELECT * FROM odalar WHERE durum = 'DOLU'";
    
    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            Oda oda = new Oda();
            oda.setOdaNumarası(rs.getInt("oda_no"));
            oda.setOdaDurumu(OdaDurumu.DOLU);
            oda.setFiyat(rs.getDouble("fiyat")); // Yeni eklenen fiyat sütunu
            doluOdalar.add(oda);
        }
    } catch (Exception e) {
        System.out.println("Hata: " + e.getMessage());
    }
    return doluOdalar;
}
}   
    
    
