/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;
import static com.oybak.otel.enums.OdaDurumu.BAKIMDA;
import static com.oybak.otel.enums.OdaDurumu.DOLU;
import static com.oybak.otel.enums.OdaDurumu.MUSAİT;
import com.oybak.otel.enums.OdaOzelligi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class TeknikEkip extends Personel implements OdaGoruntuleme{
    
    private String uzmanlikAlanı;

    public TeknikEkip(String uzmanlikAlani, String name, String lastName, long tcNo, String cinsiyet, int yas, double maas, String isTipi) {
        super(name, lastName, tcNo, cinsiyet, yas, maas, "TeknikEkip");
        this.uzmanlikAlanı = uzmanlikAlanı;
    }
    
    /**
     * @param odaNumarası
     * @param odaTipi
     * @param ekOzellikler
     * @param odaDurumu
     */
    @Override
    public void OdaGoruntule(int odaNumarası,  String odaTipi, List<OdaOzelligi>ekOzellikler, OdaDurumu odaDurumu){
    }
    //overload
    public void odaGoruntule(int odaNumarası,  String odaTipi, List<OdaOzelligi>ekOzellikler, OdaDurumu odaDurumu, String bakım){
        System.out.println("Bakım sebebi"+ bakım);
    }
    
    public static void odaBakimAl(int oda, String sebep) {
        String url = "jdbc:sqlite:otel_otomasyon.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            
            // Burada oda.getOdaNo() yerine doğrudan parametre olan odaNo'yu kullanıyoruz
            String sql = "UPDATE odalar SET durum = 'BAKIMDA', ek_ozellikler = '" + sebep + "' WHERE oda_no = " + oda;
            
            stmt.executeUpdate(sql);
            System.out.println(oda + " numaralı oda için veritabanı güncellendi.");
            
            // NOT: Burada oda.setOdaDurumu diyemezsin çünkü elimizde nesne yok, sadece sayı var.
            // Nesneyi zaten GUI tarafında interface kullanarak güncelledik.
            
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }
    
    public void OdaBakimCikar(Oda oda){
        switch(oda.getOdaDurumu()){
            case BAKIMDA:
                System.out.println("İşlem gerçekleştiriliyor.");
                oda.setOdaDurumu(OdaDurumu.MUSAİT);
                System.out.println("İşlem gerçekleşti.");
            case DOLU:
                System.out.println("Oda bakımda değildir.");
            case MUSAİT:
                System.out.println("Oda bakımda değildir.");
        }
    }

    public String getUzmanlikAlanı() {
        return uzmanlikAlanı;
    }

    public void setUzmanlikAlanı(String uzmanlikAlanı) {
        this.uzmanlikAlanı = uzmanlikAlanı;
    }
}
