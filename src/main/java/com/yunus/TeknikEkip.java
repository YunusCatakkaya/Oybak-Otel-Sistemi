/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yunus;

/**
 *
 * @author Yunus                      //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak
 */
public class TeknikEkip extends Personel {
    
    private String uzmanlikAlanı;

    public TeknikEkip(String uzmanlikAlani, String name, String lastName, String tcNo, String cinsiyet, int yas, double maas, String isTipi) {
        super(name, lastName, tcNo, cinsiyet, yas, maas, isTipi);
        this.uzmanlikAlanı = uzmanlikAlanı;
    }
   
                //ODA İŞLEMLERİ
         //public void odayiBakimaAl
         //public void odayiBakimdanCikar
}
