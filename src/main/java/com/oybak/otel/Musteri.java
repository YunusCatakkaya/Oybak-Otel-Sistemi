package com.oybak.otel;

/**
 * Musteri sınıfı, otelde konaklayan kişileri temsil eder.
 * Person soyut sınıfından miras alarak isim ve TC bilgilerini taşır.
 */
public class Musteri extends Person {

    // Constructor: Person sınıfındaki name ve tcNo alanlarını doldurur
    public Musteri(String name, long tcNo) {
        super(name, tcNo);
    }

    /**
     * Person sınıfından gelen soyut metodun uygulanması.
     * Müşteri bilgilerini profesyonel bir formatta döndürür.
     */
    @Override
    public String bilgileriYazdir() {
        return "Müşteri Bilgileri -> İsim: " + getName() + " | TC No: " + getTcNo();
    }
    

}