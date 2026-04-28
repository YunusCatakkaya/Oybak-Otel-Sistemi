/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybak.otel;

/**
 *
 * @author ahmet
 */
public interface VeriTabanı {

    public default Oda odaBilgileri(int oda_no){
    Oda bulunanOda = new Oda(); // Verileri dolduracağımız boş bir kova
        String url = "jdbc:sqlite:otel_otomasyon.db";
        String odaNo = null;
        String sql = "SELECT * FROM odalar WHERE oda_no = '" + odaNo + "'";

        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url);
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                // Veritabanından gelenleri nesneye doldur
                bulunanOda.setOdaNumarası(rs.getInt("oda_no"));
                bulunanOda.setOdaDurumu(com.oybak.otel.enums.OdaDurumu.valueOf(rs.getString("durum")));
                // Diğer set işlemlerini buraya ekle (tipi, ek özellikler vb.)
                System.out.println(odaNo + " verileri veritabanından çekildi.");
            }
        } catch (Exception e) {
            System.out.println("Sorgu hatası: " + e.getMessage());
        }
        
        return bulunanOda; // Dolu nesneyi geri gönder
    }
}
