/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

/**
 *
 * @author Yunus
 */                        
import com.oybak.otel.enums.OdaDurumu;
import java.util.ArrayList;
import java.util.List;


public class Oda {
    private int odaNumarası;   //Örn:3002
    private int tekKisilikYatak;
    private int ciftKisilikYatak;
    private boolean denizManzarası;
    private boolean balkon;
    private boolean jakuzi;
    private OdaDurumu odaDurumu;  // Bakımda  Müsait Dolu
    private String bakımSebebi;
    private List<Musteri> odadakiMusteriler;
    private int kapasite;//odanın kapasitresinii belirlemek için
    private double fiyat;
    boolean odenmeDurumu;
        
	
    public Oda(int odaNumarası, OdaDurumu odaDurumu, String odaTipi, String ekOzellikler,int kapasite) { //oda constructor 
	this.odaNumarası = odaNumarası;
	this.odaDurumu = odaDurumu;
	this.odadakiMusteriler = new ArrayList<Musteri>();//Odada birden fazla musteri kalması durumunda gerekli
        this.kapasite=kapasite;
    }

    public Oda() {
    }
    //odaya musteri ekleme methodu
    public void musteriEkle(Musteri musteri) {
        if (getKisiSayisi()<kapasite) {//odaya gelen kişilerin sayısı kapasiteden fazla olma hatasını onler
            odadakiMusteriler.add(musteri); //arraylist sayesınde musterı eklenır
        }else{
            System.out.println("Hata: Oda kapasitesi dolu");
        }
    }
    public void musterileriTemizle() {
	odadakiMusteriler.clear(); // Listeyi tamamen boşaltır
    }
    public String getOzelliklerHTML() {
        String ozellikMetni = "<html>"
                + "<b>Fiyat:</b> " + this.getFiyat() + " TL / Gece<br><br>"
                + "<b>Yatak Kapasitesi:</b><br>"
                + "- " + this.getTekKisilikYatak() + " Adet Tek Kişilik<br>"
                + "- " + this.getCiftKisilikYatak() + " Adet Çift Kişilik<br><br>"
                + "<b>Ekstra Özellikler:</b><br>"
                + "✔️ Ücretsiz Wi-Fi<br>"      
                + "✔️ Kahvaltı Dahil<br>";     
        
        if (this.isDenizManzarasi()) { 
            ozellikMetni += "✔️ Deniz Manzarası<br>";
        }
        if (this.isBalkon()) {
            ozellikMetni += "✔️ Balkon<br>";
        }
        if (this.isJakuzi()) {
            ozellikMetni += "✔️ Jakuzi<br>";
        }
        
        ozellikMetni += "</html>";
        return ozellikMetni;
    }
	
	//odadaki kisi sayisi alınırken kullanılan method
    public int getKisiSayisi() {
	return odadakiMusteriler.size();
    }

    public int getOdaNumarasi() {
	return odaNumarası;
    }

    public void setOdaNumarasi(int odaNumarası) {
	if (odaNumarası>0) { //oda numarası "0"dan küçük olamaz
            this.odaNumarası = odaNumarası;
	}
    }

    public int getTekKisilikYatak() {
        return tekKisilikYatak;
    }

    public void setTekKisilikYatak(int tekKisilikYatak) {
        this.tekKisilikYatak = tekKisilikYatak;
    }

    public int getCiftKisilikYatak() {
        return ciftKisilikYatak;
    }

    public void setCiftKisilikYatak(int ciftKisilikYatak) {
        this.ciftKisilikYatak = ciftKisilikYatak;
    }

    public boolean isDenizManzarasi() {
        return denizManzarası;
    }

    public void setDenizManzarasi(boolean denizManzarası) {
        this.denizManzarası = denizManzarası;
    }

    public boolean isBalkon() {
        return balkon;
    }

    public void setBalkon(boolean balkon) {
        this.balkon = balkon;
    }

    public boolean isJakuzi() {
        return jakuzi;
    }

    public void setJakuzi(boolean jakuzi) {
        this.jakuzi = jakuzi;
    }

    public String getBakimSebebi() {
        return bakımSebebi;
    }

    public void setBakimSebebi(String bakımSebebi) {
        this.bakımSebebi = bakımSebebi;
    }
        
    public OdaDurumu getOdaDurumu() {
        return odaDurumu;
    }

    public void setOdaDurumu(OdaDurumu odaDurumu) {
        this.odaDurumu = odaDurumu;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }
                
    public List<Musteri> getOdadakiMusteriler() {
        return odadakiMusteriler;
    }
        
    public double getFiyat() {
                 return fiyat;
    }
        
    public void setFiyat(double fiyat) {
        if (fiyat >= 0) {
                this.fiyat = fiyat;
        }
    }

    public void setOdenmeDurumu(boolean odenmeDurumu) {
        this.odenmeDurumu = odenmeDurumu;
    }
    
    public boolean isOdenmeDurumu() {
        return odenmeDurumu;
    }
}

