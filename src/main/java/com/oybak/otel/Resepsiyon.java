/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;

/**
 * 
 * @author Yunus                      //implements IVeriTabani edıp ıcındekı fonskyıonları yazılcak
 */ 

public class Resepsiyon extends Personel{

	public Resepsiyon(String name, String lastName, long tcNo, String cinsiyet, int yas, double maas, String isTipi) {
		super(name, lastName, tcNo, cinsiyet, yas, maas, "Resepsiyon");
	}
		
	public void musteriGirisYap(Oda secilenOda , Musteri yeniMusteri) { //Parametre almayı unutma.
			if (secilenOda.getOdaDurumu().equals(OdaDurumu.MUSAİT)) {
				
				secilenOda.musteriEkle(yeniMusteri);//odaya musterı eklıyoruz
				secilenOda.setOdaDurumu(OdaDurumu.DOLU); //odanın durumunu dolu yapıyoruz
				
				System.out.println("İşlem Başarılı "+yeniMusteri.getName()+"İsimli Müşteri"+secilenOda.getOdaNumarası()+"Numaralı Odaya Giriş Yaptı");
				System.out.println("Odada şu an "+secilenOda.getKisiSayisi()+"Kişi Kalıyor..");
			}else {
	            System.out.println("İşlem Başarısız: " + secilenOda.getOdaNumarası() + " numaralı oda şu anda " + secilenOda.getOdaDurumu() + "!");
	        }
		}
		
	public void musteriCıkısYap(Oda secilenOda , Musteri yenMusteri) {
		if (secilenOda.getOdaDurumu().equals(OdaDurumu.DOLU)) {
			
			secilenOda.setOdaDurumu(OdaDurumu.MUSAİT);//oda durumunu tekrar musait yaouyorz
			secilenOda.musterileriTemizle(); //odadakı musterılerı temızlıyoruz
			
				System.out.println(secilenOda.getOdaNumarası() + " numaralı odanın çıkış işlemi yapıldı. Oda artık Müsait.");
            
        
		}else {
            System.out.println("Hata: Bu oda zaten dolu değil!");
			}
			
		}
	public void odemeAl(Musteri musteri , double alinanMiktar) {
		if (alinanMiktar>0) {
			musteri.kasayaKatkiEkle(alinanMiktar);
			System.out.println(musteri.getName() + " isimli müşteriden " + alinanMiktar + " TL tahsil edildi.");
            System.out.println("Müşterinin toplam harcaması: " + musteri.getKasayaKatkisi() + " TL oldu.");
			}
		
		}
        
	public void paraIadeEt(Musteri musteri, double iadeMiktari){
            if(iadeMiktari > 0){
                if(musteri.getKasayaKatkisi() >= iadeMiktari){
                    musteri.kasayaKatkiCikar(iadeMiktari);
                    
                    System.out.println("İşlem Başarılı: " + musteri.getName() + " isimli müşteriye " + iadeMiktari + " TL iade edildi.");
                    System.out.println("Müşterinin kasada kalan bakiyesi (toplam harcaması): " + musteri.getKasayaKatkisi() + " TL oldu.");
                } else{
                    System.out.println("Hata: Müşteriye otele ödediği toplam tutardan (" + musteri.getKasayaKatkisi() + " TL) daha fazla iade yapılamaz!");
                }
            } else{
                System.out.println("Hata: İade edilecek miktar sıfırdan büyük olmalıdır!");
            }
        }
	
	}
	


