package com.oybak.otel;

import com.oybak.otel.enums.OdaDurumu;

public class Oda {
    private int odaNumarası;   //Örn:3002
    private int tekKisilikYatak;
    private int ciftKisilikYatak;
    private boolean denizManzarası;
    private boolean balkon;
    private boolean jakuzi;
    private OdaDurumu odaDurumu;  // Bakımda  Müsait Dolu
    private String bakımSebebi;
    private double fiyat;
    private boolean odenmeDurumu;
        
    // Parametreli Constructor (Gereksiz parametreler silindi)
    public Oda(int odaNumarası, OdaDurumu odaDurumu) { 
        this.odaNumarası = odaNumarası;
        this.odaDurumu = odaDurumu;
    }

    // Boş Constructor (Veritabanından veri çekerken çok işe yarar)
    public Oda() {
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
    
    // --- AKILLI METOT: Kapasiteyi kendi kendine hesaplar ---
    public int getKapasite() {
        return this.tekKisilikYatak + (this.ciftKisilikYatak * 2);
    }

    // --- STANDART GETTER VE SETTER'LAR ---
    public int getOdaNumarasi() {
        return odaNumarası;
    }

    public void setOdaNumarasi(int odaNumarası) {
        if (odaNumarası > 0) { //oda numarası "0"dan küçük olamaz
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