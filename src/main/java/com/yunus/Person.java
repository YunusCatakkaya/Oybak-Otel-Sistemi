/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yunus;

/**
 *
 * @author Yunus
 */
public abstract class Person {
	protected String name;
	protected String lastName;
	protected String tcNo ;
	protected String cinsiyet;
	protected int yas;
	
	public Person(String name , String lastName,String tcNo,String cinsiyet,int yas ) {
		
			this.name=name;
			this.lastName=lastName;
			this.tcNo=tcNo;
			this.cinsiyet=cinsiyet;
			this.yas=yas;
	}
	public abstract void bilgileriYazdir();
	
}
