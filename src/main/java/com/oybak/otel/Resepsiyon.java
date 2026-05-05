/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;
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

    public void musteriGirisYap(Oda secilenOda, Musteri yeniMusteri) {
        if (secilenOda.getOdaDurumu().equals(OdaDurumu.MUSAIT)) {

            // 1. RAM üzerindeki nesne güncellemeleri
            secilenOda.musteriEkle(yeniMusteri);
            secilenOda.setOdaDurumu(OdaDurumu.DOLU);


            String url = VeriTabani.URL;
            String sql = "UPDATE odalar SET durum = 'DOLU' WHERE oda_no = ?";

            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, secilenOda.getOdaNumarasi());
                pstmt.executeUpdate();

                System.out.println("İşlem Başarılı: " + yeniMusteri.getName() + " isimli müşteri " + secilenOda.getOdaNumarasi() + " numaralı odaya giriş yaptı.");
                System.out.println("Odada şu an " + secilenOda.getKisiSayisi() + " kişi kalıyor.");

            } catch (Exception e) {
                System.out.println("Veritabanı Hatası (Giriş): " + e.getMessage());
            }
        } else {
            System.out.println("İşlem Başarısız: " + secilenOda.getOdaNumarasi() + " numaralı oda müsait değil!");
        }
    }        
		
    public void musteriCıkısYap(Oda secilenOda, Musteri yenMusteri) {
        if (secilenOda.getOdaDurumu().equals(OdaDurumu.DOLU)) {

            // Odanın durumunu sistem hafızasında tekrar 'MUSAIT' yapar
            secilenOda.setOdaDurumu(OdaDurumu.MUSAIT);
            // Odadaki müşteri listesini tamamen temizler
            secilenOda.musterileriTemizle();
            String url = VeriTabani.URL;
            // Odalar tablosundaki ilgili odanın durumunu 'MUSAIT' yapacak SQL sorgusu
            String sql = "UPDATE odalar SET durum = 'MUSAİT' WHERE oda_no = ?";

            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // SQL sorgusundaki '?' yerine çıkış yapılan odanın numarasını yerleştirir
                pstmt.setInt(1, secilenOda.getOdaNumarasi());
                // Sorguyu çalıştırarak veritabanındaki oda durumunu günceller
                pstmt.executeUpdate();

                System.out.println(secilenOda.getOdaNumarasi() + " numaralı odanın çıkış işlemi yapıldı. Oda artık Müsait.");
            } catch (Exception e) {
                System.out.println("Veritabanı Hatası (Çıkış): " + e.getMessage());
            }
        } else {
            // Eğer oda zaten DOLU değilse çıkış yapılamayacağını belirtir
            System.out.println("Hata: Bu oda zaten dolu değil!");
        }
    }
        
        // Resepsiyon.java içinde revize edilmiş hali
    public void odemeAl(Musteri musteri, Oda secilenOda) {
        double odaFiyatı = secilenOda.getFiyat(); // Fiyatı odadan çekiyoruz
    
        if (odaFiyatı > 0) {
            musteri.kasayaKatkiEkle(odaFiyatı);
        
            String url = VeriTabani.URL;
            String sql = "UPDATE musteriler SET kasaya_katkisi = ? WHERE tc_no = ?";
        
            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
                 pstmt.setDouble(1, musteri.getKasayaKatkisi());
                 pstmt.setLong(2, musteri.getTcNo());
                 pstmt.executeUpdate();
            
                 System.out.println(secilenOda.getOdaNumarasi() + " nolu oda ücreti olan " + odaFiyatı + " TL tahsil edildi.");
            } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
            }
        }
    }
        
    public void paraIadeEt(Musteri musteri, double iadeMiktari) {
        if (iadeMiktari > 0 && musteri.getKasayaKatkisi() >= iadeMiktari) {
            
            musteri.kasayaKatkiCikar(iadeMiktari);
            
            String url = VeriTabani.URL;
            String sql = "UPDATE musteriler SET kasaya_katkisi = ? WHERE tc_no = ?";
            
            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                // 1. soru işareti: İade edildikten sonra müşteride kalan net katkı payı
                pstmt.setDouble(1, musteri.getKasayaKatkisi());
                // 2. soru işareti: Hangi müşteri olduğunu bulmak için TC No
                pstmt.setLong(2, musteri.getTcNo());
                pstmt.executeUpdate();
                
                System.out.println("İşlem Başarılı: " + musteri.getName() + " isimli müşteriye " + iadeMiktari + " TL iade edildi.");
            } catch (Exception e) {
                System.out.println("Veritabanı Hatası (İade): " + e.getMessage());
            }
        } else {
            // Eğer iade edilmek istenen para müşterinin verdiği paradan fazlaysa hata verir
            System.out.println("Hata: Geçersiz iade miktarı veya yetersiz bakiye!");
        }
    }
    public void musteriArama(String musteriIsmi) {
        String url = VeriTabani.URL;
        
        // İsmin bir kısmı girilse bile bulabilmesi için LIKE ve % kullanıyoruz
        String sql = "SELECT * FROM guncel_musteriler WHERE ad_soyad LIKE ?";
        
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            // Girilen ismin sağına ve soluna % ekleyerek içerenleri bulmasını sağlıyoruz
            pstmt.setString(1, "%" + musteriIsmi + "%");
            ResultSet rs = pstmt.executeQuery();
            
            boolean bulundu = false;
            System.out.println("--- Müşteri Arama Sonuçları ---");
            
            while (rs.next()) {
                bulundu = true;
                // Veritabanı tablonuzdaki sütun isimleriyle eşleşen verileri çekiyoruz
                String adSoyad = rs.getString("ad_soyad");
                String tcNo = rs.getString("tc_no");
                String odaNo = rs.getString("oda_no");
                String girisTarihi = rs.getString("giris_tarihi");
                String cikisTarihi = rs.getString("cikis_tarihi");
                int kasaKatki = rs.getInt("kasa_katki");
                
                // Konsola (veya isterseniz GUI'ye) basılacak çıktı
                System.out.println("Ad Soyad: " + adSoyad + 
                                   " | TC: " + tcNo + 
                                   " | Oda No: " + odaNo + 
                                   " | Giriş: " + girisTarihi + 
                                   " | Çıkış: " + cikisTarihi + 
                                   " | Kasaya Katkı: " + kasaKatki + " TL");
            }
            
            if (!bulundu) {
                System.out.println("Sistemde '" + musteriIsmi + "' isminde güncel bir müşteri bulunamadı.");
            }
            System.out.println("-------------------------------");
            
        } catch (SQLException e) {
            System.out.println("Veritabanı Hatası (Müşteri Arama): " + e.getMessage());
        }
    }
}       
        
	


