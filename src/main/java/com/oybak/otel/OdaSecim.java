/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybak.otel;

import com.oybakotel.GUI.OdaSecimEkrani;

/**
 *
 * @author ahmet
 */
public interface OdaSecim {
    
    public default void odaSecim(){
        OdaSecimEkrani t = new OdaSecimEkrani();
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
