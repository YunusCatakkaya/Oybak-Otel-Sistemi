/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel.models;
//sdfgdsdg
public class Oda {
    private String odaNo;
    private String tipi;
    private String ozellikler;
    private String durum;

    // Boş constructor (Bazen gerekebilir)
    public Oda() {}

    // Veritabanından veri çekerken kullanacağın constructor
    public Oda(int id, String odaNo, String tipi, String ekOzellikler, String durum) {
        this.odaNo = odaNo;
        this.tipi = tipi;
        this.ozellikler = ozellikler;
        this.durum = durum;
    }

    // Getter Metotları (Arayüzde verileri okumak için)
    public String getOdaNo(){ 
        return odaNo; }
    
    public String getOzellikler(){ 
        return ozellikler; 
    }
}
