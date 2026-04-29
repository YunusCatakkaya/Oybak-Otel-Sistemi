/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Yunus                      //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak
 */ 

public class Resepsiyon extends Personel{

	public Resepsiyon(String name, String lastName, long tcNo, String cinsiyet, int yas, double maas, String isTipi) {
		super(name, lastName, tcNo, cinsiyet, yas, maas, "Resepsiyon");
	}
        
        public void musteriGirisYap(Oda secilenOda, Musteri yeniMusteri) {
        if (secilenOda.getOdaDurumu().equals(OdaDurumu.MUSAİT)) {
            
            // 1. RAM üzerindeki nesne güncellemeleri
            secilenOda.musteriEkle(yeniMusteri);
            secilenOda.setOdaDurumu(OdaDurumu.DOLU);
            
            
            String url = VeriTabanı.URL;
            String sql = "UPDATE odalar SET durum = 'DOLU' WHERE oda_no = ?";
            
            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, secilenOda.getOdaNumarası());
                pstmt.executeUpdate();
                
                System.out.println("İşlem Başarılı: " + yeniMusteri.getName() + " isimli müşteri " + secilenOda.getOdaNumarası() + " numaralı odaya giriş yaptı.");
                System.out.println("Odada şu an " + secilenOda.getKisiSayisi() + " kişi kalıyor.");
                
            } catch (Exception e) {
                System.out.println("Veritabanı Hatası (Giriş): " + e.getMessage());
            }
        } else {
            System.out.println("İşlem Başarısız: " + secilenOda.getOdaNumarası() + " numaralı oda müsait değil!");
        }
    }        
		
	public void musteriCıkısYap(Oda secilenOda, Musteri yenMusteri) {
        if (secilenOda.getOdaDurumu().equals(OdaDurumu.DOLU)) {
            
            // Odanın durumunu sistem hafızasında tekrar 'MUSAİT' yapar
            secilenOda.setOdaDurumu(OdaDurumu.MUSAİT);
            // Odadaki müşteri listesini tamamen temizler
            secilenOda.musterileriTemizle();
            String url = VeriTabanı.URL;
            // Odalar tablosundaki ilgili odanın durumunu 'MUSAİT' yapacak SQL sorgusu
            String sql = "UPDATE odalar SET durum = 'MUSAİT' WHERE oda_no = ?";
            
            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                // SQL sorgusundaki '?' yerine çıkış yapılan odanın numarasını yerleştirir
                pstmt.setInt(1, secilenOda.getOdaNumarası());
                // Sorguyu çalıştırarak veritabanındaki oda durumunu günceller
                pstmt.executeUpdate();
                
                System.out.println(secilenOda.getOdaNumarası() + " numaralı odanın çıkış işlemi yapıldı. Oda artık Müsait.");
            } catch (Exception e) {
                System.out.println("Veritabanı Hatası (Çıkış): " + e.getMessage());
            }
        } else {
            // Eğer oda zaten DOLU değilse çıkış yapılamayacağını belirtir
            System.out.println("Hata: Bu oda zaten dolu değil!");
        }
    }
        
        public void odemeAl(Musteri musteri, double alinanMiktar) {
        if (alinanMiktar > 0) {
            
            musteri.kasayaKatkiEkle(alinanMiktar);
            
            String url = VeriTabanı.URL;
            // Müşteriler tablosunda ilgili müşterinin 'kasaya_katkisi' sütununu güncelleyecek SQL sorgusu
            String sql = "UPDATE musteriler SET kasaya_katkisi = ? WHERE tc_no = ?";
            
            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                // 1. soru işareti: Müşterinin güncel (arttırılmış) toplam kasaya katkısı
                pstmt.setDouble(1, musteri.getKasayaKatkisi());
                // 2. soru işareti: Hangi müşteri olduğunu belirlemek için TC Kimlik Numarası
                pstmt.setLong(2, musteri.getTcNo());
                pstmt.executeUpdate();
                
                System.out.println(musteri.getName() + " isimli müşteriden " + alinanMiktar + " TL tahsil edildi.");
            } catch (Exception e) {
                System.out.println("Veritabanı Hatası (Ödeme): " + e.getMessage());
            }
        }
    }
        
	public void paraIadeEt(Musteri musteri, double iadeMiktari) {
        if (iadeMiktari > 0 && musteri.getKasayaKatkisi() >= iadeMiktari) {
            
            musteri.kasayaKatkiCikar(iadeMiktari);
            
            String url = VeriTabanı.URL;
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
}
	


