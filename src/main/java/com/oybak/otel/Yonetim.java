/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.UserRole;
import static com.oybak.otel.enums.UserRole.YONETIM;

/**
 *
 * @author Yunus                       //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak
 */

//y
public class Yonetim extends Personel implements VeriTabani, Hatalar{
    public Yonetim(String name, long tcNo, double maas, UserRole isTipi,String parola) {
        super(name,tcNo,maas,YONETIM,parola);
    }
    //aaaa
    
    /**
     * Sadece Yönetici sınıfına özel personel ekleme fonksiyonu.
     * Bu metod VeriTabanı interface'inde tanımlı değildir, 
     * bu yüzden sadece Yonetim nesneleri üzerinden erişilebilir.
     */
    
  
    
    // Yonetim.java içindeki personelEkle metodunu bununla değiştir
    public String personelEkle(String ad, String soyad, String tcStr, String maasStr, String parola, String rolStr, String uzmanlik) {
    // 1. Boşluk Kontrolü
    if (ad.trim().isEmpty() || soyad.trim().isEmpty() || tcStr.trim().isEmpty() || 
        maasStr.trim().isEmpty() || parola.trim().isEmpty() || rolStr.equals("Seçiniz")) {
        return "UYARI: Lütfen tüm alanları eksiksiz doldurunuz!";
    }

    try {
        long tc = Long.parseLong(tcStr);
        double maas = Double.parseDouble(maasStr);

        if (tcVarMi(tc)) {
            return "HATA: Bu TC numarası zaten kayıtlı!";
        }

        String finalUzmanlik = rolStr.equals("TEKNIKPERSONEL") ? uzmanlik : "";
        String sql = "INSERT INTO calisanlar(ad_soyad, tc_no, maas, is_tipi, Parola, uzmanlik_alani) VALUES(?,?,?,?,?,?)";
        
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, ad + " " + soyad);
            pstmt.setLong(2, tc);
            pstmt.setDouble(3, maas);
            pstmt.setString(4, rolStr);
            pstmt.setString(5, parola);
            pstmt.setString(6, finalUzmanlik);

            pstmt.executeUpdate();
            return "BAŞARILI: " + ad + " " + soyad + " sisteme kaydedildi.";
        }
    } catch (Exception e) {
        return "HATA: " + e.getMessage();
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
  
  
  

public String paraIadeYap(String tcNoStr, String iadeMiktariStr) {
    if (tcNoStr == null || tcNoStr.trim().isEmpty() || iadeMiktariStr == null || iadeMiktariStr.trim().isEmpty()) {
        return "Lütfen TC No ve miktar alanlarını doldurunuz!";
    }

    try {
        long tcNo = Long.parseLong(tcNoStr.trim());
        double iadeMiktari = Double.parseDouble(iadeMiktariStr.replace(",", "."));
        // İade miktarını kontrol ediyoruz
        if (iadeMiktari <= 0) {
            return "İade miktarı 0'dan büyük olmalıdır!";
        }
        // Sorguyu TC'ye göre yapıyoruz
        String kontrolSql = "SELECT kasa_katki, ad_soyad FROM guncel_musteriler WHERE tc_no = ?";
        
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
             java.sql.PreparedStatement pstmtKontrol = conn.prepareStatement(kontrolSql)) {
            
            pstmtKontrol.setLong(1, tcNo);
            java.sql.ResultSet rs = pstmtKontrol.executeQuery();

            if (rs.next()) {
                double mevcutBakiye = rs.getDouble("kasa_katki");
                String adSoyad = rs.getString("ad_soyad");
                //İade işlemlerini yapıyoruz
                if (iadeMiktari > mevcutBakiye) {
                    return "HATA: " + adSoyad + " isimli müşterinin toplam ödemesi " + mevcutBakiye + " TL. Daha yüksek iade yapılamaz!";
                } else {
                    // Güncelleme sorgusu da TC No'ya göre
                    String guncelleSql = "UPDATE guncel_musteriler SET kasa_katki = kasa_katki - ? WHERE tc_no = ?";
                    try (java.sql.PreparedStatement pstmtGuncelle = conn.prepareStatement(guncelleSql)) {
                        pstmtGuncelle.setDouble(1, iadeMiktari);
                        pstmtGuncelle.setLong(2, tcNo);
                        
                        int sonuc = pstmtGuncelle.executeUpdate();
                        if (sonuc > 0) {
                            return "BAŞARILI: " + adSoyad + " (" + tcNo + ") kişisine " + iadeMiktari + " TL iade edildi.\nGüncel Bakiye: " + (mevcutBakiye - iadeMiktari) + " TL";
                        }
                    }
                }
            } else {
                return "UYARI: '" + tcNo + "' TC numarasına sahip bir müşteri kaydı bulunamadı!";
            }
        //Hata kısımları    
        }
    } catch (NumberFormatException e) {
        return "HATA: Lütfen TC ve miktar alanlarına geçerli sayılar giriniz!";
    } catch (java.sql.SQLException e) {
        return "VERİTABANI HATASI: " + e.getMessage();
    }
    return "Beklenmedik bir hata oluştu.";
}

public String odaOzellikGuncelle(String odaNo, String yeniTekY, String yeniCiftY, String manzara, String balkon, String jakuzi, String fiyat) {
    if (odaNo == null || odaNo.isEmpty() || odaNo.equals("Oda Numarası:")) {
        return "Lütfen önce geçerli bir Oda Numarası giriniz!";
    }

    try (java.sql.Connection con = java.sql.DriverManager.getConnection(URL)) {
        // Mevcut yatak bilgilerini veri tabanından çek
        int mevcutTekYatak = 0;
        int mevcutCiftYatak = 0;
        
        String selectSql = "SELECT tek_kisilik_yatak, cift_kisilik_yatak FROM odalar WHERE oda_no = ?";
        try (java.sql.PreparedStatement pstSelect = con.prepareStatement(selectSql)) {  
            pstSelect.setString(1, odaNo);
            java.sql.ResultSet rs = pstSelect.executeQuery();
            if (rs.next()) {
                mevcutTekYatak = rs.getInt("tek_kisilik_yatak");
                mevcutCiftYatak = rs.getInt("cift_kisilik_yatak");
            } else {
                return "UYARI: " + odaNo + " numaralı oda bulunamadı!";
            }
        }

        // Kullanıcı "Seçiniz" dediyse mevcut olanı koru, sayı seçtiyse yenisini al
        int kontrolTekY = yeniTekY.equals("Seçiniz") ? mevcutTekYatak : Integer.parseInt(yeniTekY);
        int kontrolCiftY = yeniCiftY.equals("Seçiniz") ? mevcutCiftYatak : Integer.parseInt(yeniCiftY);

        // Hesaplama: (Tek Yatak * 1) + (Çift Yatak * 2)
        int toplamKapasite = (kontrolTekY * 1) + (kontrolCiftY * 2);

        if (toplamKapasite > 4) {
            return "HATA: Oda kapasitesi (4) aşıldı! Seçtiğiniz düzen " + toplamKapasite + " kişilik.\n" +
                   "Lütfen yatak sayılarını azaltın.";
        }

      
        StringBuilder query = new StringBuilder("UPDATE odalar SET ");
        boolean degisiklikVar = false;

        if (!yeniTekY.equals("Seçiniz")) {
            query.append("tek_kisilik_yatak = ").append(yeniTekY);
            degisiklikVar = true;
        }
        if (!yeniCiftY.equals("Seçiniz")) {
            if (degisiklikVar) query.append(", ");
            query.append("cift_kisilik_yatak = ").append(yeniCiftY);
            degisiklikVar = true;
        }
        if (!manzara.equals("Seçiniz")) {
            if (degisiklikVar) query.append(", ");
            query.append("deniz_manzarasi = ").append(manzara.equals("Var") ? "'true'" : "'false'");
            degisiklikVar = true;
        }
        if (!balkon.equals("Seçiniz")) {
            if (degisiklikVar) query.append(", ");
            query.append("balkon = ").append(balkon.equals("Var") ? "'true'" : "'false'");
            degisiklikVar = true;
        }
        if (!jakuzi.equals("Seçiniz")) {
            if (degisiklikVar) query.append(", ");
            query.append("jakuzi = ").append(jakuzi.equals("Var") ? "'true'" : "'false'");
            degisiklikVar = true;
        }
        if (!fiyat.isEmpty() && !fiyat.equals("Fiyat:") && !fiyat.equals("Fiyat (TL)")) {
            if (degisiklikVar) query.append(", ");
            query.append("fiyat = ").append(fiyat.replace(",", "."));
            degisiklikVar = true;
        }

        if (!degisiklikVar) return "Herhangi bir özellik seçilmedi, güncelleme yapılmadı.";

        query.append(" WHERE oda_no = ?");
        java.sql.PreparedStatement pst = con.prepareStatement(query.toString());
        pst.setString(1, odaNo);

        if (pst.executeUpdate() > 0) {
            return "BAŞARILI: " + odaNo + " numaralı oda kapasiteye uygun olarak güncellendi.";
        } else {
            return "HATA: Güncelleme sırasında bir sorun oluştu!";
        }
    } catch (Exception e) {
        return "VERİTABANI HATASI: " + e.getMessage();
    }
}
}
 

