package com.blogspot.lucbors.ch5.mobile.pojo;

public class Attendee implements Comparable{
    
    private int id;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String postal;
    private String state;
    private String country;
    private String email;
    private String phone;
    
    public Attendee (int id, String firstName, String lastName, String  streetAddress, String city, String postal, String state, String country, String email, String phone){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        
        this.city = city;
        this.postal=postal ;
        this.state = state;
        
        this.country = country;
        this.email = email;
        this.phone = phone;
        
    }
    public Attendee() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getPostal() {
        return postal;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

   
    public int compareTo(Object o) {
        
        Attendee a = (Attendee)o;
        return this.getCountry().compareTo(a.getCountry());
        
    }
}
