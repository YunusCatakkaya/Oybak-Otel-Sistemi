/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;


import com.oybak.otel.enums.UserRole;
import static com.oybak.otel.enums.UserRole.RESEPSIYON;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * 
 * @author Yunus                      //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak
 */ 

public class Resepsiyon extends Personel{

    public Resepsiyon(String name, String lastName, long tcNo, double maas, UserRole isTipi,String parola) {
            super(name, tcNo, maas, RESEPSIYON ,parola);
    }

    // 1. Müşteri Çıkarma (Odayı Boşaltma) İşlemi
    public static boolean odayiBosalt(int odaNo) {
        String sqlGecmiseTasi = "INSERT INTO gecmis_musteriler (ad_soyad, tc_no, oda_no, giris_tarihi, cikis_tarihi, kasa_katki) " +
                                "SELECT ad_soyad, tc_no, oda_no, giris_tarihi, DATE('now'), kasa_katki FROM guncel_musteriler WHERE oda_no = ?";
        
        String sqlMusteriSil = "DELETE FROM guncel_musteriler WHERE oda_no = ?";
        
        String sqlOdaGuncelle = "UPDATE odalar SET durum = 'MUSAIT', odenme_durumu = 'false' WHERE oda_no = ?";

        try (Connection conn = DriverManager.getConnection(VeriTabani.URL)) {
            conn.setAutoCommit(false); // Atomik işlem (Transaction)

            try (PreparedStatement ps1 = conn.prepareStatement(sqlGecmiseTasi);
                 PreparedStatement ps2 = conn.prepareStatement(sqlMusteriSil);
                 PreparedStatement ps3 = conn.prepareStatement(sqlOdaGuncelle)) {
                
                ps1.setInt(1, odaNo); ps1.executeUpdate();
                ps2.setInt(1, odaNo); ps2.executeUpdate();
                ps3.setInt(1, odaNo); ps3.executeUpdate();

                conn.commit(); 
                return true;   // İşlem başarılı
            } catch (SQLException e) {
                conn.rollback(); 
                System.err.println("SQL Hatası (Oda Boşaltma): " + e.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.err.println("Veritabanı Bağlantı Hatası: " + e.getMessage());
            return false;
        }
    }
    
    // Dönüş değerleri -> 0: Eklendi (Yer var), 1: Eklendi (Oda tam doldu), -1: Hata (Zaten dolu), -2: Veritabanı Hatası
    public static int musteriEkle(int odaNo, String musteriAd, String musteriTc, String girisStr, String cikisStr) {
        try (Connection conn = DriverManager.getConnection(VeriTabani.URL)) {
            
            // A) Kapasiteyi kontrol et
            int kapasite = 0;
            String sqlKapasite = "SELECT tek_kisilik_yatak, cift_kisilik_yatak FROM odalar WHERE oda_no = ?";
            try (PreparedStatement pstmtCap = conn.prepareStatement(sqlKapasite)) {
                pstmtCap.setInt(1, odaNo);
                ResultSet rsCap = pstmtCap.executeQuery();
                if(rsCap.next()){
                    kapasite = rsCap.getInt("tek_kisilik_yatak") + (rsCap.getInt("cift_kisilik_yatak") * 2);
                }
            }
            
            int mevcutMusteri = 0;
            String sqlCount = "SELECT COUNT(*) AS kisi_sayisi FROM guncel_musteriler WHERE oda_no = ?";
            try (PreparedStatement pstmtCount = conn.prepareStatement(sqlCount)) {
                pstmtCount.setString(1, String.valueOf(odaNo));
                ResultSet rsCount = pstmtCount.executeQuery();
                if(rsCount.next()){
                    mevcutMusteri = rsCount.getInt("kisi_sayisi");
                }
            }
            
            if (mevcutMusteri >= kapasite) {
                return -1; // Oda zaten dolu
            }

            // B) Müşteriyi veritabanına ekle
            String sqlMusteri = "INSERT INTO guncel_musteriler (ad_soyad, tc_no, oda_no, giris_tarihi, cikis_tarihi, kasa_katki) VALUES (?, ?, ?, ?, ?, 0)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlMusteri)) {
                pstmt.setString(1, musteriAd);
                pstmt.setString(2, musteriTc);
                pstmt.setString(3, String.valueOf(odaNo));
                pstmt.setString(4, girisStr);
                pstmt.setString(5, cikisStr);
                pstmt.executeUpdate();
            }
            
            mevcutMusteri++; // Kayıt atıldığı için sayıyı 1 artırıyoruz
            
            // C) Kapasite dolduysa odayı DOLU yap
            if (mevcutMusteri >= kapasite) {
                odayiDoluYap(odaNo);
                return 1; // Eklendi, kapasite sınırına ulaştı
            }
            
            return 0; // Eklendi, hala kapasite var
            
        } catch (Exception e) {
            System.err.println("SQL Hatası (Müşteri Ekleme): " + e.getMessage());
            return -2;
        }
    }
    
    public static void odayiDoluYap(int odaNo) {
        String sql = "UPDATE odalar SET durum = 'DOLU' WHERE oda_no = ?";
        try (Connection conn = DriverManager.getConnection(VeriTabani.URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, odaNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Oda güncelleme hatası: " + e.getMessage());
        }
    }
    
    public static boolean isOdaOdenmis(int odaNo) {
        String sql = "SELECT odenme_durumu FROM odalar WHERE oda_no = ?";
        try (Connection conn = DriverManager.getConnection(VeriTabani.URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, odaNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String durum = rs.getString("odenme_durumu");
                    // Eğer durum "true" ise ödenmiştir (true döner), false veya null ise ödenmemiştir (false döner).
                    return "true".equalsIgnoreCase(durum);
                }
            }
        } catch (Exception e) {
            System.err.println("Ödeme kontrol hatası: " + e.getMessage());
        }
        // Eğer veritabanına ulaşılamazsa güvenli tarafta kalıp çıkışa izin verme
        return false; 
    }
}       
