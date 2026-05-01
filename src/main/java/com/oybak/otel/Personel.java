/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

/**
 *
 * @author Yunus              
 */

public class Personel extends Person{
    private double maas;
    private String isTipi;
    private String uzmanlikAlani;
    private String parola;
    
	
    public Personel(String name, String lastName, long tcNo, double maas, String isTipi, String uzmanlikAlani,String parola) {
        super(name, lastName, tcNo);
        this.maas = maas;
        this.isTipi = isTipi;
        this.uzmanlikAlani= uzmanlikAlani;
        this.parola=parola;
       
    }
    
    @Override
    public void bilgileriYazdir() {
        System.out.println("Personel İsim ve Soy İsim"+getName()+getLastName()+"Tc No"+getTcNo()+"TC");
	System.out.println("Görevi"+isTipi+"Maaşı"+maas+"TL");
    }
		public double getMaas() {
			return maas;
		}
		public void setMaas(double maas){
			if (maas>0) {
				this.maas=maas;
			}
		}
		public String getIsTipi() {
			return isTipi;
		}
		public void setIsTipi(String isTipi) {
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
