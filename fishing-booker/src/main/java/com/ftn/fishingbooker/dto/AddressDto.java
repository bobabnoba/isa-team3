package com.ftn.fishingbooker.dto;

public class AddressDto {

    public Long id;
    public String street;
    public String city;
    public String country;
    public Long zipCode;

    public Long getId() { return id; }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
