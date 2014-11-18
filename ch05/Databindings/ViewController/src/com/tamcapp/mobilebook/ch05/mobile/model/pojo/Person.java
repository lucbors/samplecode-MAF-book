package com.tamcapp.mobilebook.ch05.mobile.model.pojo;


public class Person {
    
    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private String email;
    private String phone;
    private String photo;
    public Person() {
        super();
    }
    
    
    public Person (int id, String firstName, String lastName, String country, String email, String phone, String photo){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
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

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }
}
