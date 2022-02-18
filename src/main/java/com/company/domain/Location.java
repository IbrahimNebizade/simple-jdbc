package com.company.domain;

import java.util.Objects;

public class Location {
    private Long id;
    private String streetAdress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private Country country;

    public Location() {
    }
    public Location(Long id){
        this.id=id;
    }
    public Location(Long id, String streetAdress, String postalCode, String city, String stateProvince, Country country) {
        this.id = id;
        this.streetAdress = streetAdress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", streetAdress='" + streetAdress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) && Objects.equals(streetAdress, location.streetAdress) && Objects.equals(postalCode, location.postalCode) && Objects.equals(city, location.city) && Objects.equals(stateProvince, location.stateProvince) && Objects.equals(country, location.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetAdress, postalCode, city, stateProvince, country);
    }
}
