/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;
import com.oybak.otel.enums.OdaOzelligi;
import com.oybak.otel.enums.UserRole;
import static com.oybak.otel.enums.UserRole.TEKNIKPERSONEL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;//selam
import java.util.List;

public class TeknikEkip extends Personel {
    

    public TeknikEkip(String name, String lastName, long tcNo, double maas, UserRole isTipi,String uzmanlikAlani,String parola) {
        super(name, tcNo, maas, TEKNIKPERSONEL ,uzmanlikAlani,parola);
        
    }
    
    /**
     * @param odaNumarası
     * @param odaTipi
     * @param ekOzellikler
     * @param odaDurumu
     */
    
    //overload
    public void odaGoruntule(int odaNumarası,  String odaTipi, List<OdaOzelligi>ekOzellikler, OdaDurumu odaDurumu, String bakım){
        System.out.println("Bakım sebebi"+ bakım);
    }
    
    public static void odaBakimAl(int oda, String sebep) {
        String url = VeriTabani.URL;
        String sql = "UPDATE odalar SET durum = 'BAKIMDA', bakim_sebebi = ? WHERE oda_no = ?";
    
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
            pstmt.setString(1, sebep);
            pstmt.setInt(2, oda);
        
            int etkilenenSatir = pstmt.executeUpdate();
            if ((etkilenenSatir > 0) && (etkilenenSatir < 2)) {
                System.out.println(oda + " numaralı oda bakımda olarak güncellendi.");
            } else if(etkilenenSatir == 0){
                System.out.println("Veritabanında hiçbir oda etkilenmedi.");
            }else{
                System.out.println("Veritabanında çok sayıda oda etkilendi.");
            }
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }
    
    public static void odaBakimdanCikar(int oda){
        String url = VeriTabani.URL;
        String sql = "UPDATE odalar SET durum = ?, bakim_sebebi = ? WHERE oda_no = ?";
    
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
            pstmt.setString(1, "MUSAIT");
            pstmt.setString(2, "Bakımda değil.");
            pstmt.setInt(3, oda);
        
            int etkilenenSatir = pstmt.executeUpdate();
            if ((etkilenenSatir > 0) && (etkilenenSatir < 2)) {
                System.out.println(oda + " numaralı oda bakımda olarak güncellendi.");
            } else if(etkilenenSatir == 0){
                System.out.println("Veritabanında hiçbir oda etkilenmedi.");
            }else{
                System.out.println("Veritabanında çok sayıda oda etkilendi.");
            }
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    
}
