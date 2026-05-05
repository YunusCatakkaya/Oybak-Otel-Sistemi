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
    public default boolean tcKontrol(String tc){
        try {
            long sayi = Long.parseLong(tc);
            int sonHane = (int) (sayi%10);
            sayi = sayi/10;
            int toplam = 0;
            
            if(!(sayi > 999999999L) && (sayi < 10000000000L)){
                JOptionPane.showMessageDialog((Component) this, "Geçerli bir TC no giriniz!");
                return false;
            }
            
            while(sayi >= 1){
                int hane =  (int) (sayi%10);
                sayi = sayi/10;
                toplam = toplam + hane;
            }
            
            if((toplam%10) != sonHane){
                JOptionPane.showMessageDialog((Component) this, "Geçerli bir TC no giriniz!");
                return false;
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Hata: Metin geçerli bir sayı formatında değil!");
        }
        return true;
    }
    public default boolean tcVarMi(long tcNo) {
    String sql = "SELECT COUNT(*) FROM calisanlar WHERE tc_no = ?";
    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setLong(1, tcNo);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0; // Eğer sonuç 0'dan büyükse bu TC kayıtlıdır
        }
    } catch (Exception e) {
        System.out.println("TC kontrol hatası: " + e.getMessage());
    }
    return false;
}
}
