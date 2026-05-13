/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oybak.otel;

/**
 *y
 * @author Yunus
 */
public abstract class Person {
    private String name;
    private long tcNo ;
	
    public Person(String name, long tcNo) {
        this.name=name;
        this.tcNo=tcNo;
    }
    public abstract String bilgileriYazdir();//log kayıtları için kullanıyoruz
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTcNo() {
        return tcNo;
    }

    public void setTcNo(long tcNo) {
        this.tcNo = tcNo;
    }
	
}
