/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;
import com.oybak.otel.enums.OdaOzelligi;
import java.util.List;

/**
 *
 * @author ahmet
 */
public interface OdaGoruntuleme {
    default void OdaGoruntule(int odaNumarası,  String odaTipi, List<OdaOzelligi>ekOzellikler, OdaDurumu odaDurumu){
        System.out.println("Oda Numarası:"+odaNumarası+"Oda Tipi:"+odaTipi+"Ek Özellikler"+ekOzellikler+"Oda Müsaitlik Durumu"+odaDurumu);
    }
}
