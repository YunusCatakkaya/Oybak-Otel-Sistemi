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
            conn.setAutoCommit(false); 

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
    
    // Ödenmemiş ve Dolu olan odaların listesini getirir
    public static java.util.ArrayList<String> getOdenmemisDoluOdalar() {
        java.util.ArrayList<String> odalar = new java.util.ArrayList<>();
        String sql = "SELECT oda_no FROM odalar WHERE durum = 'DOLU' AND (odenme_durumu = 'false' OR odenme_durumu IS NULL)";
        
        try (Connection conn = DriverManager.getConnection(VeriTabani.URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                odalar.add(String.valueOf(rs.getInt("oda_no")));
            }
        } catch (Exception e) {
            System.err.println("Oda yükleme hatası: " + e.getMessage());
        }
        return odalar;
    }
    
    // Odanın kalma süresine göre toplam fiyatını hesaplar
    public static double hesaplaToplamFiyat(int odaNo) {
        double tabanFiyat = 0;
        long gunSayisi = 1; 
        
        String sqlOda = "SELECT fiyat FROM odalar WHERE oda_no = ?";
        try (Connection conn = DriverManager.getConnection(VeriTabani.URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlOda)) {
            pstmt.setInt(1, odaNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tabanFiyat = rs.getDouble("fiyat");
                }
            }
        } catch (Exception e) {
            System.err.println("Fiyat çekme hatası: " + e.getMessage());
            return -1;
        }

        String sqlMusteri = "SELECT giris_tarihi, cikis_tarihi FROM guncel_musteriler WHERE oda_no = ? LIMIT 1";
        try (Connection conn = DriverManager.getConnection(VeriTabani.URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlMusteri)) {
            pstmt.setInt(1, odaNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String girisStr = rs.getString("giris_tarihi");
                    String cikisStr = rs.getString("cikis_tarihi");
                    
                    if (girisStr != null && cikisStr != null && !girisStr.isEmpty() && !cikisStr.isEmpty()) {
                        java.time.format.DateTimeFormatter formatlayici = java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        java.time.LocalDate giris = java.time.LocalDate.parse(girisStr, formatlayici);
                        java.time.LocalDate cikis = java.time.LocalDate.parse(cikisStr, formatlayici);
                        
                        gunSayisi = java.time.temporal.ChronoUnit.DAYS.between(giris, cikis);
                        if (gunSayisi <= 0) gunSayisi = 1; 
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Tarih çekme hatası: " + e.getMessage());
        }

        return tabanFiyat * gunSayisi;
    }
    
    // Odaya ait daha önceden ödenmiş miktarı (kasa katkı) getirir
    public static double odenenMiktariGetir(int odaNo) {
        double odenen = 0.0;
        String sql = "SELECT kasa_katki FROM guncel_musteriler WHERE oda_no = ?";
        try (Connection conn = DriverManager.getConnection(VeriTabani.URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, String.valueOf(odaNo));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                odenen = rs.getDouble("kasa_katki");
            }
        } catch (Exception e) {
            System.err.println("Ödeme Sorgu Hatası: " + e.getMessage());
        }
        return odenen;
    }
    
    // Ödeme Alma işlemini yapar (Transaction kullanır). Başarılıysa parayı ödeyen TC'yi, başarısızsa null döner.
    public static String odemeAl(int odaNo, double odaFiyati) {
        String sqlOdaGuncelle = "UPDATE odalar SET odenme_durumu = 'true' WHERE oda_no = ?";
        String sqlIlkMusteriBul = "SELECT tc_no FROM guncel_musteriler WHERE oda_no = ? ORDER BY rowid ASC LIMIT 1";
        String sqlMusteriGuncelle = "UPDATE guncel_musteriler SET kasa_katki = COALESCE(kasa_katki, 0) + ? WHERE tc_no = ?";
        
        try (Connection conn = DriverManager.getConnection(VeriTabani.URL)) {
            conn.setAutoCommit(false); 
            
            try (PreparedStatement pstmtOda = conn.prepareStatement(sqlOdaGuncelle);
                 PreparedStatement pstmtMusteriBul = conn.prepareStatement(sqlIlkMusteriBul);
                 PreparedStatement pstmtMusteri = conn.prepareStatement(sqlMusteriGuncelle)) {
                
                pstmtOda.setInt(1, odaNo);
                pstmtOda.executeUpdate();
                
                pstmtMusteriBul.setInt(1, odaNo);
                String ilkMusteriTc = null;
                try (ResultSet rs = pstmtMusteriBul.executeQuery()) {
                    if (rs.next()) {
                        ilkMusteriTc = rs.getString("tc_no"); 
                    }
                }
                
                if (ilkMusteriTc != null) {
                    pstmtMusteri.setDouble(1, odaFiyati);
                    pstmtMusteri.setString(2, ilkMusteriTc);
                    pstmtMusteri.executeUpdate();
                }
                
                conn.commit();
                return ilkMusteriTc; // İşlem başarılı, parayı ödeyen TC'yi geri yolla
                
            } catch (Exception ex) {
                conn.rollback();
                System.err.println("Ödeme işlemi veritabanı hatası: " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Ödeme bağlantı hatası: " + e.getMessage());
            return null;
        }
    }
}       

    