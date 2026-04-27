/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;
import static com.oybak.otel.enums.OdaDurumu.BAKIMDA;
import static com.oybak.otel.enums.OdaDurumu.DOLU;
import static com.oybak.otel.enums.OdaDurumu.MUSAİT;
import com.oybak.otel.enums.OdaOzelligi;
import java.util.List;
import java.util.Scanner;

public class TeknikEkip extends Personel implements OdaGoruntuleme{
    
    private String uzmanlikAlanı;

    public TeknikEkip(String uzmanlikAlani, String name, String lastName, long tcNo, String cinsiyet, int yas, double maas, String isTipi) {
        super(name, lastName, tcNo, cinsiyet, yas, maas, "TeknikEkip");
        this.uzmanlikAlanı = uzmanlikAlanı;
    }
    
    Scanner scan = new Scanner(System.in);
    
    /**
     * @param odaNumarası
     * @param odaTipi
     * @param ekOzellikler
     * @param odaDurumu
     */
    @Override
    public void OdaGoruntule(int odaNumarası,  String odaTipi, List<OdaOzelligi>ekOzellikler, OdaDurumu odaDurumu){
    }
    //overload
    public void odaGoruntule(int odaNumarası,  String odaTipi, List<OdaOzelligi>ekOzellikler, OdaDurumu odaDurumu, String bakım){
        System.out.println("Bakım sebebi"+ bakım);
    }
    
    public void odaBakimAl(Oda oda) {
        switch(oda.getOdaDurumu()){
            case BAKIMDA:
                System.out.println("Oda zaten bakımda.");
            case DOLU:
                System.out.println("İşlemi iptal etmek için 1 \n İşleme devam etmek için 2'yi tuşlayınız.");
                int secim = scan.nextInt();
                switch(secim){
                    case 1 -> System.out.println("İşlem iptal ediliyor.");
                    case 2 -> {
                        System.out.println("İşlem gerçekleştiriliyor.");
                        oda.setOdaDurumu(OdaDurumu.BAKIMDA);
                        System.out.println("İşlem gerçekleşti.");
                }
                    default -> System.out.println("Geçersiz tuşlama yapıldı işlem iptal ediliyor.");
        }
            case MUSAİT:
                System.out.println("İşlem gerçekleştiriliyor.");
                oda.setOdaDurumu(OdaDurumu.BAKIMDA);
                System.out.println("İşlem gerçekleşti.");        
        }  
    }
    
    public void OdaBakimCikar(Oda oda){
        switch(oda.getOdaDurumu()){
            case BAKIMDA:
                System.out.println("İşlem gerçekleştiriliyor.");
                oda.setOdaDurumu(OdaDurumu.MUSAİT);
                System.out.println("İşlem gerçekleşti.");
            case DOLU:
                System.out.println("Oda bakımda değildir.");
            case MUSAİT:
                System.out.println("Oda bakımda değildir.");
        }
    }
}
