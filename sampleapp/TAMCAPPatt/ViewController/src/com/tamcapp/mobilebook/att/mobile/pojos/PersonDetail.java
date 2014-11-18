package com.tamcapp.mobilebook.att.mobile.pojos;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class PersonDetail {
    
    private int id;
    private String firstName;
    private String lastName;
    private String countryName;
    private String organization;
    private String email;
    private String phone;
    private String picture;
    private String bio;
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PersonDetail() {
        super();
    }
    
    
    public PersonDetail (int id, String firstName, String lastName, String countryName, String organization, String email, String phone, String picture, String bio){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryName = countryName;
        this.organization = organization;
        this.email = email;
        this.phone = phone;
        this.picture = picture;
        this.bio = bio;
        
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

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
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

  

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganization() {
        return organization;
    }

    public void setPicture(String picture) {
        String oldPicture = this.picture;
        this.picture = picture;
        propertyChangeSupport.firePropertyChange("picture", oldPicture, picture);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public String getPicture() {
        return picture;
    }
}
