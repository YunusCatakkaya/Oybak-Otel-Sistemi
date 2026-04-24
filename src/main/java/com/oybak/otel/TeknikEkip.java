/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;
import com.oybak.otel.enums.OdaOzelligi;
import java.util.List;

/**
 *
 * @author Yunus                      //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak
 */
public class TeknikEkip extends Personel implements OdaGoruntuleme{
    
    private String uzmanlikAlanı;

    public TeknikEkip(String uzmanlikAlani, String name, String lastName, long tcNo, String cinsiyet, int yas, double maas, String isTipi) {
        super(name, lastName, tcNo, cinsiyet, yas, maas, "TeknikEkip");
        this.uzmanlikAlanı = uzmanlikAlanı;
    }
    
    /**
     * @param odaNumarası
     * @param odaTipi
     * @param ekOzellikler
     * @param odaDurumu
     */
    @Override
    public void OdaGoruntule(int odaNumarası,  String odaTipi, List<OdaOzelligi>ekOzellikler, OdaDurumu odaDurumu){
    }
    //overload
    public void odaGoruntule(int odaNumarası,  String odaTipi, List<OdaOzelligi>ekOzellikler, OdaDurumu odaDurumu, String bakım){
        System.out.println("Bakım sebebi"+ bakım);
    }
    
    
   
                //ODA İŞLEMLERİ
         //public void odayiBakimaAl (equal kullanılır)
         //public void odayiBakimdanCikar
}
