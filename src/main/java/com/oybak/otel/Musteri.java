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
	private int kaldigiOdaNo;
	private double kasayaKatkisi;
	
	public Musteri(String name, String lastName, String tcNo, String cinsiyet, int yas, int kaldigiOdaNo) {
			
			super(name, lastName, tcNo, cinsiyet, yas);
			this.kaldigiOdaNo = kaldigiOdaNo;
			this.kasayaKatkisi = 0.0;//ilk giriste katki 0 
			
	}
	@Override
    public void bilgileriYazdir() {
  	  System.out.println("Müsteri İsim ve Soyisim"+name+lastName+"Tc No"+tcNo+"TC"+"Cinsiyet"+cinsiyet+"Yaş"+yas);
  	  System.out.println("Kaldığı Oda"+kaldigiOdaNo+"Kasaya Katkısı"+kasayaKatkisi+"TL");
  	  
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
		this.kasayaKatkisi+=miktar;
		
	}
	

	
  }

