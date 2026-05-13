/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybakotel.GUI;

import com.oybak.otel.Personel;

public interface OdaSecim {
    
    public default void odaSecim(Personel p){
        OdaSecimEkrani t = new OdaSecimEkrani(p);//oda şeçim ekranı nesnesı olusuturuz , parametre olarak personel alırız
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
