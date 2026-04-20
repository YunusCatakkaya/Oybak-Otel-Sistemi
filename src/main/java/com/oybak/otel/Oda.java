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
	private OdaDurumu odaDurumu;  // Bakımda  Müsait Dolu
	private String odaTipi;  //Tek kişilik  Çift Kişilik   
	private List<OdaOzelligi>ekOzellikler; // Deniz Manzararlı  
	private List<Musteri> odadakiMusteriler;
        private int kapasite;//odanın kapasitresinii belirlemek için
        
	
	public Oda(int odaNumarası, OdaDurumu odaDurumu, String odaTipi, String ekOzellikler,int kapasite) { //oda constructor 
		super();
		this.odaNumarası = odaNumarası;
		this.odaDurumu = odaDurumu;
		this.odaTipi = odaTipi;
		this.ekOzellikler = new ArrayList<OdaOzelligi>();
		this.odadakiMusteriler = new ArrayList<Musteri>();//Odada birden fazla musteri kalması durumunda gerekli
                this.kapasite=kapasite;
                
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
        //ozellık ekleme methodu
	public void ozellikEkle(OdaOzelligi ozellik){
            if(!ekOzellikler.contains(ozellik)){
                
                ekOzellikler.add(ozellik);
            }
        }
        //Odadan ozellık cıkarma methodu
        public void ozellikCikar(OdaOzelligi ozellik){
            ekOzellikler.remove(ozellik);
           }
        //odanın sahip oldugu ozellıklerı listeleme
        public List<OdaOzelligi> getEkOzellikler() {
                 return ekOzellikler;
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
        
	public String getOdaTipi() {
		return odaTipi;
	}

	public void setOdaTipi(String odaTipi) {
		this.odaTipi = odaTipi;
	}
	
	public void odaBilgileri() {
		
		System.out.println("Oda Numarası:"+odaNumarası+"Oda Tipi:"+odaTipi+"Ek Özellikler"+ekOzellikler+"Oda Müsaitlik Durumu"+odaDurumu);
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

         

}

