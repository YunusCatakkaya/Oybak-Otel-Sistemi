/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybakotel.GUI;

/**
 *
 * @author ahmet
 */
public interface OdalaraGecis {
    public default void odalaraGecis(int odaNo){
        OdaGUI t = new OdaGUI(odaNo);
        t.setLocationRelativeTo(null);
        t.setVisible(true);
    }
}
