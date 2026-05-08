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
    
  
    
  public void personelEkle(Personel p) {
    // Sorguya uzmanlik_alanı eklendi (Toplam 6 adet '?' oldu)
    String sql = "INSERT INTO calisanlar(ad_soyad, tc_no, maas, is_tipi, Parola, uzmanlik_alani) VALUES(?,?,?,?,?,?)";
    
    try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        String rol = String.valueOf(p.getIsTipi());
        
        pstmt.setString(1, p.getName());
        pstmt.setLong(2, p.getTcNo());
        pstmt.setDouble(3, p.getMaas());
        pstmt.setString(4, rol);
        pstmt.setString(5, p.getParola()); 
        pstmt.setString(6, p.getUzmanlikAlani()); // Personel nesnesinden gelen uzmanlık bilgisi

        pstmt.executeUpdate();
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Veritabanı Hatası: " + e.getMessage());
        e.printStackTrace();
        
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
  
  // Yonetim.java içine eklenecek metod
public String gecmisMusteriAra(String arananIsim) {
    if (arananIsim == null || arananIsim.trim().isEmpty() || arananIsim.equals("İsim Soyisim:")) {
        return "Lütfen geçerli bir isim giriniz.";
    }

    StringBuilder sb = new StringBuilder();
    String sql = "SELECT ad_soyad, tc_no, oda_no, giris_tarihi, cikis_tarihi, kasa_katki FROM gecmis_musteriler WHERE ad_soyad LIKE ?";

    try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, "%" + arananIsim.trim() + "%");
        java.sql.ResultSet rs = pstmt.executeQuery();

        boolean bulundu = false;
        while (rs.next()) {
            bulundu = true;
            sb.append("Müşteri Ad Soyad: ").append(rs.getString("ad_soyad")).append("\n")
              .append("TC Kimlik No: ").append(rs.getString("tc_no")).append("\n")
              .append("Oda Numarası: ").append(rs.getString("oda_no")).append("\n")
              .append("Giriş Tarihi: ").append(rs.getString("giris_tarihi")).append("\n")
              .append("Çıkış Tarihi: ").append(rs.getString("cikis_tarihi")).append("\n")
              .append("Kasaya Katkı: ").append(rs.getInt("kasa_katki")).append(" TL\n")
              .append("-------------------------------------------\n");
        }

        if (!bulundu) {
            return "Sistemde '" + arananIsim + "' isminde bir geçmiş kayıt bulunamadı.";
        }

        return sb.toString();

    } catch (java.sql.SQLException e) {
        return "Veritabanı hatası: " + e.getMessage();
    }
}
  
// Yonetim.java içine eklenecek metod
public String paraIadeYap(String tcNoStr, String iadeMiktariStr) {
    if (tcNoStr == null || tcNoStr.trim().isEmpty() || iadeMiktariStr == null || iadeMiktariStr.trim().isEmpty()) {
        return "Lütfen TC No ve miktar alanlarını doldurunuz!";
    }

    try {
        long tcNo = Long.parseLong(tcNoStr.trim());
        double iadeMiktari = Double.parseDouble(iadeMiktariStr.replace(",", "."));
        
        if (iadeMiktari <= 0) {
            return "İade miktarı 0'dan büyük olmalıdır!";
        }

        // Sorguyu ad_soyad yerine tc_no'ya göre yapıyoruz
        String kontrolSql = "SELECT kasa_katki, ad_soyad FROM guncel_musteriler WHERE tc_no = ?";
        
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(VeriTabani.URL);
             java.sql.PreparedStatement pstmtKontrol = conn.prepareStatement(kontrolSql)) {
            
            pstmtKontrol.setLong(1, tcNo);
            java.sql.ResultSet rs = pstmtKontrol.executeQuery();

            if (rs.next()) {
                double mevcutBakiye = rs.getDouble("kasa_katki");
                String adSoyad = rs.getString("ad_soyad");

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
        }
    } catch (NumberFormatException e) {
        return "HATA: Lütfen TC ve miktar alanlarına geçerli sayılar giriniz!";
    } catch (java.sql.SQLException e) {
        return "VERİTABANI HATASI: " + e.getMessage();
    }
    return "Beklenmedik bir hata oluştu.";
}

public String odaOzellikGuncelle(String odaNo, String tekYatak, String ciftYatak, String manzara, String balkon, String jakuzi, String fiyat) {
    if (odaNo == null || odaNo.isEmpty() || odaNo.equals("Oda Numarası:")) {
        return "Lütfen önce geçerli bir Oda Numarası giriniz!";
    }

    try (java.sql.Connection con = java.sql.DriverManager.getConnection(URL)) {
        StringBuilder query = new StringBuilder("UPDATE odalar SET ");
        boolean degisiklikVar = false;

        if (!tekYatak.equals("Seçiniz")) {
            query.append("tek_kisilik_yatak = ").append(tekYatak);
            degisiklikVar = true;
        }
        if (!ciftYatak.equals("Seçiniz")) {
            if (degisiklikVar) query.append(", ");
            query.append("cift_kisilik_yatak = ").append(ciftYatak);
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
            return "BAŞARILI: " + odaNo + " numaralı oda güncellendi.";
        } else {
            return "UYARI: " + odaNo + " numaralı oda veritabanında bulunamadı!";
        }
    } catch (Exception e) {
        return "VERİTABANI HATASI: " + e.getMessage();
    }
}
}
 

