/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

/**
 *
 * @author Yunus
 */                              //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak

public class Musteri extends Person {
         
    private String cinsiyet;
    private int yas;
    private int kaldigiOdaNo;
    private double kasayaKatkisi;
	
    public Musteri(String name, long tcNo) {
			
        super(name, tcNo);              
        this.cinsiyet=cinsiyet;
        this.yas=yas;
        this.kaldigiOdaNo = kaldigiOdaNo;
        this.kasayaKatkisi = 0;//ilk giriste katki 0 			
    }
    @Override
    public String bilgileriYazdir() {
        return("Müsteri İsim ve Soyisim" + getName() +"Tc No"+getTcNo()+"TC"+"Cinsiyet"+cinsiyet+"Yaş"+yas
                +"Kaldığı Oda"+kaldigiOdaNo+"Kasaya Katkısı"+kasayaKatkisi+"TL");
    }
    
    public int getKaldıgıOdaNo() {
            return kaldigiOdaNo;
    }
    public void setKaldıgıOdaNo(int kaldigiOdaNo) {
            if(kaldigiOdaNo>0) {
                    this.kaldigiOdaNo = kaldigiOdaNo;
            }
    }
    public double getKasayaKatkisi() {
            return kasayaKatkisi;
    }
    public void setKasayaKatkisi(double kasayaKatkisi) {
            if (kasayaKatkisi>0) {
            this.kasayaKatkisi = kasayaKatkisi;
            }
    }
    public void kasayaKatkiEkle(double miktar) {
        if (miktar > 0) {  //eklenen para 0'dan büyük olmalı
            this.kasayaKatkisi+=miktar;
        } else{
            System.out.println("Hata: Eklenecek miktar 0'dan büyük olmalıdır!");
        }    

    }

    public void kasayaKatkiCikar(double miktar) {
        if (miktar > 0 && this.kasayaKatkisi >= miktar) {  //müşterinin kasaya katkısından daha fazlasını iade edemeyiz
        this.kasayaKatkisi -= miktar;
        }
    }	
}

