package com.blogspot.lucbors.ch5.mobile.pojo;

public class Distribution {
    
    
    private String country;
    private int count;
    public Distribution() {
        super();
    }
    
    
    public Distribution (String country, int count){
        this.country=country;
        this.count= count;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
