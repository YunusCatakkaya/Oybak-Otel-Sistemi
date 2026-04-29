/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

/**
 *
 * @author Yunus
 */                        
import com.oybak.otel.enums.OdaOzelligi;
import com.oybak.otel.enums.OdaDurumu;
import java.util.ArrayList;
import java.util.List;


public class Oda {
	private int odaNumarası;   //Örn:3002
        private int tekKisilikYatak;
        private int ciftKisilikYatak;
        private boolean denizManzarası;
        private boolean minibar;
        private boolean jakuzi;
	private OdaDurumu odaDurumu;  // Bakımda  Müsait Dolu
        private String bakımSebebi;
	private List<Musteri> odadakiMusteriler;
        private int kapasite;//odanın kapasitresinii belirlemek için
        private double fiyat;
        
	
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

         

}

