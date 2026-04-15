/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yunus;

/**
 *
 * @author Yunus
 */                          //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak

import java.util.ArrayList;
import java.util.List;

public class Oda {
	private int odaNumarası;   //Örn:3002
	private String odaDurumu;  // Bakımda  Müsait Dolu
	private String odaTipi;  //Tek kişilik  Çift Kişilik   
	private String ekOzellikler;  // Deniz Manzararlı  Geniş Balkon
	private List<Musteri> odadakiMusteriler;
	
	public Oda(int odaNumarası, String odaDurumu, String odaTipi, String ekOzellikler) { //oda constructor 
		super();
		this.odaNumarası = odaNumarası;
		this.odaDurumu = odaDurumu;
		this.odaTipi = odaTipi;
		this.ekOzellikler = ekOzellikler;
		this.odadakiMusteriler = new ArrayList<Musteri>();//Odada birden fazla musteri kalması durumunda gerekli
	}
	//odaya musteri ekleme methodu
	public void musteriEkle(Musteri musteri) {
		
		odadakiMusteriler.add(musteri); //arraylist sayesınde musterı eklenır
		
	}
	public void musterileriTemizle() {
	    odadakiMusteriler.clear(); // Listeyi tamamen boşaltır
	}
	
	//odadaki kisi sayisi alınırken kullanılan method
	public int getKisiSayisi() {
		
		return odadakiMusteriler.size();
	}
	
	

	public int getOdaNumarası() {
		return odaNumarası;
	}

	public void setOdaNumarası(int odaNumarası) {
		if (odaNumarası>0) { //oda numarası "0"dan küçük olamaz
			this.odaNumarası = odaNumarası;
		}
	}

	public String getOdaDurumu() {
		return odaDurumu;
	}

	public void setOdaDurumu(String odaDurumu) {
		this.odaDurumu = odaDurumu;
	}

	public String getOdaTipi() {
		return odaTipi;
	}

	public void setOdaTipi(String odaTipi) {
		this.odaTipi = odaTipi;
	}

	public String getEkOzellikler() {
		return ekOzellikler;
	}

	public void setEkOzellikler(String ekOzellikler) {
		this.ekOzellikler = ekOzellikler;
	}
	
	public void odaBilgileri() {
		
		System.out.println("Oda Numarası:"+odaNumarası+"Oda Tipi:"+odaTipi+"Ek Özellikler"+ekOzellikler+"Oda Müsaitlik Durumu"+odaDurumu);
	}
	

}

