/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.UserRole;
import com.oybakotel.GUI.OdaSecimEkrani;

/**
 *
 * @author ahmet
 */
public interface OdaSecim {
    
    public default void odaSecim(UserRole rol){
        OdaSecimEkrani t = new OdaSecimEkrani(rol);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
