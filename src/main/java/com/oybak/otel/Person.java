/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

/**
 *
 * @author Yunus
 */
public abstract class Person {
	private String name;
	private String lastName;
	private long tcNo ;
	
	public Person(String name , String lastName,long tcNo) {
		
			this.name=name;
			this.lastName=lastName;
			this.tcNo=tcNo;
	}
	public abstract void bilgileriYazdir();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getTcNo() {
        return tcNo;
    }

    public void setTcNo(long tcNo) {
        this.tcNo = tcNo;
    }
	
}
