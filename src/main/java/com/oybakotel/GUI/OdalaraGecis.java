/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybakotel.GUI;

import com.oybak.otel.GUIResepsiyon.MusteriCikarma;
import com.oybak.otel.Oda;
import com.oybak.otel.Personel;
import com.oybak.otel.enums.OdaDurumu;
import com.oybak.otel.enums.UserRole;
import com.oybak.otel.VeriTabani;

/**
 *
 * @author ahmet
 */
public interface OdalaraGecis extends VeriTabani {
    public default void odalaraGecis(int odaNo, Personel p){
        
        // Tıklanan odanın durumunu veri tabanından çekin
        Oda oda = odaBilgileri(odaNo); 
        
        // Kullanıcı MUSTERIEKLEME rolündeyse ve oda MUSAIT değilse işlemi iptal et
        if (p != null && p.getIsTipi() == UserRole.MUSTERIEKLEME && oda.getOdaDurumu() != OdaDurumu.MUSAIT) {
            return; // Hiçbir ekran açma, işlemi kes
        }
        
        
        if (p != null && p.getIsTipi() == UserRole.MUSTERICIKARMA) {
            // OdaGUI yerine MusteriCikarma ekranını açıyoruz
            // Not: MusteriCikarma yapıcı metodunun (constructor) (odaNo, p) aldığını varsayarak yazdım.
            // Eğer sadece (p) alıyorsa veya farklıysa o kısmı kendi ekranınıza göre düzenleyin.
            MusteriCikarma cikarmaEkrani = new MusteriCikarma(odaNo, p);
            cikarmaEkrani.setLocationRelativeTo(null);
            cikarmaEkrani.setVisible(true);
            return; // Kodun aşağıya inip OdaGUI'yi de açmasını engelliyoruz
        }
        
        OdaGUI t = new OdaGUI(odaNo, p);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
