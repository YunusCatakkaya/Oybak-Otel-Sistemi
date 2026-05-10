/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybak.otel;

import static com.oybak.otel.VeriTabani.URL;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmet
 */
public interface Hatalar {
    public default void tcKontrol(String tc){
        try {
            //girilen tc'nin uzunluk kontrolü
            if (tc.length() != 11) {
                JOptionPane.showMessageDialog((Component) this, "TC Kimlik numarası 11 haneli olmalıdır!");
                throw new IllegalArgumentException("Hatalı TC"); 
            }
            //aldığımız tc'yi sayi değişkenine atıyoruz
            //tc'nin son hanesini castingle int tipinde sonHane değişkenine atıyouz
            //atadıktan sonra son haneyi sayi değişkeninden silliyoruz
            long sayi = Long.parseLong(tc);
            int sonHane = (int) (sayi%10);
            sayi = sayi/10;
            int toplam = 0;
            
            //ilk 10 haneyi topluyoruz
            while(sayi >= 1){
                int hane =  (int) (sayi%10);
                sayi = sayi/10;
                toplam = toplam + hane;
            }
            
            //ilk 10 hanenin toplamının 11. haneye eşit olup olmadığını ve 11. hanenin çiftliğini kontrol ediyoruz
            //eğer uygun değilse haat mesajı verdiriyoruz
            if(((toplam%10) != sonHane) || ((sonHane%2) !=0)){
                JOptionPane.showMessageDialog((Component) this, "Geçerli bir TC no giriniz! Hata:001");
                throw new IllegalArgumentException("Hatalı TC"); 
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog((Component) this, "Hata: TC Kimlik numarası sadece sayılar1an oluşmalıdır!");
            throw new IllegalArgumentException("Hatalı TC"); 
        }
    }
    
    //veritabanındaki calisanlar table'ında tc'nin olup olmadığını kontrol ediyoruz
    //varsa true yoksa false döndürüyoruz
    public default boolean tcVarMi(long tcNo) {
        String sql = "SELECT COUNT(*) FROM calisanlar WHERE tc_no = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, tcNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (Exception e) {
            System.out.println("TC kontrol hatası: " + e.getMessage());
        }
        return false;
    }
}
