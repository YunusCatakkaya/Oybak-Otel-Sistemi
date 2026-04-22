/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    // Veritabanı dosyasının yolu. 
    // "jdbc:sqlite:otel_otomasyon.db" -> Proje ana dizinindeki dosyaya bakar.
    private static final String URL = "jdbc:sqlite:otel_otomasyon.db";

    public static Connection getConnection() throws SQLException {
        // Bu metod çağrıldığında bağlantıyı açıp geri döndürür.
        return DriverManager.getConnection(URL);
    }
    
    // Bağlantıyı test etmek için küçük bir metod
    public static void checkConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Bağlantı Başarılı: Veritabanına ulaşıldı!");
            }
        } catch (SQLException e) {
            System.out.println("Bağlantı Hatası: " + e.getMessage());
        }
    }
}
