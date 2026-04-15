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
	
		public Personel(String name, String lastName, String tcNo, String cinsiyet,int yas , double maas, String isTipi) {
			super(name, lastName, tcNo, cinsiyet,yas);
			this.maas = maas;
			this.isTipi = isTipi;
	}
		@Override
		public void bilgileriYazdir() {
			System.out.println("Personel İsim ve Soy İsim"+name+lastName+"Tc No"+tcNo+"TC");
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
		

}
