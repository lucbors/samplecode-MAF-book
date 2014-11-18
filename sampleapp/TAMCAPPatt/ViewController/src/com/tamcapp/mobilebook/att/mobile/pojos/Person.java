package com.tamcapp.mobilebook.att.mobile.pojos;

public class Person {
    
    private int id;
    private String firstName;
    private String lastName;
    private String countryName;
    private String organization;
      public Person() {
        super();
    }
    
    
    public Person (int id, String firstName, String lastName, String countryName, String organization){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryName = countryName;
        this.organization = organization;
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

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganization() {
        return organization;
    }
}
