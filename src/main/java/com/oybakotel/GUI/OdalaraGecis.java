/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybakotel.GUI;

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
        
        OdaGUI t = new OdaGUI(odaNo, p);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
