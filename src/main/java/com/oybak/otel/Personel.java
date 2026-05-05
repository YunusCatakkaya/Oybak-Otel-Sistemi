/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

import com.oybak.otel.enums.UserRole;

/**
 *
 * @author Yunus              
 */

public class Personel extends Person{
    private double maas;
    private UserRole isTipi;
    private String uzmanlikAlani;
    private String parola;
    
	
    public Personel(String name, long tcNo, double maas, UserRole isTipi, String uzmanlikAlani, String parola) {
        super(name, tcNo);
        this.maas = maas;
        this.isTipi = isTipi;
        this.uzmanlikAlani = uzmanlikAlani;
        this.parola = parola;       
    }
    public Personel(String name, long tcNo, double maas, UserRole isTipi, String parola) {
        super(name, tcNo);
        this.maas = maas;
        this.isTipi = isTipi;
        this.parola = parola;       
    }
    
    @Override
    public String bilgileriYazdir() {
        return("Personel İsim ve Soy İsim"+getName()+"Tc No"+getTcNo()+"TC");
    }
    public String bilgileriYazdir(String isTipi, String maas) {
        return("Personel İsim ve Soy İsim"+getName()+"Tc No"+getTcNo()+"TC"+ "Görevi"+isTipi+"Maaşı"+maas+"TL");
    }
    public double getMaas() {
            return maas;
    }
    public void setMaas(double maas){
        if (maas>0) {
                this.maas=maas;
        }
    }
    public  UserRole getIsTipi() {
            return isTipi;
    }
    public void setIsTipi(UserRole isTipi) {
            this.isTipi = isTipi;
    }
    public String getUzmanlikAlani() {
            return uzmanlikAlani;
    }
    public void setUzmanlikAlani(String uzmanlikAlani) {
            this.uzmanlikAlani=uzmanlikAlani;
    }
    public String getParola() {
            return parola;
    }
    public void setParola(String parola) {
            this.parola = parola;
    }
}
