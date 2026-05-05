/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybakotel.GUI;

import com.oybak.otel.Personel;
import com.oybak.otel.enums.UserRole;

/**
 *
 * @author ahmet
 */
public interface OdalaraGecis {
    public default void odalaraGecis(int odaNo, Personel p){
        OdaGUI t = new OdaGUI(odaNo, p);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
