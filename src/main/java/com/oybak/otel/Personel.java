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
    private String cinsiyet;
    private int yas;
	
    public Personel(String name, String lastName, long tcNo, String cinsiyet,int yas , double maas, String isTipi) {
        super(name, lastName, tcNo);
        this.maas = maas;
        this.isTipi = isTipi;
        this.cinsiyet=cinsiyet;
        this.yas=yas;
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
                public String getCinsiyet() {
			return cinsiyet;
		}
		public void setCinsiyet(String cinsiyet) {
			this.cinsiyet = cinsiyet;
		}
                public int getYas() {
			return yas;
		}
		public void setYas(int yas) {
			this.yas = yas;
		}
		

}
