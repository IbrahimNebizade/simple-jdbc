package com.company.domain;

import java.util.Objects;

public class Country {
    private String id;
    private String name;
    private Region region;

    public Country(String id) {
        this.id = id;
    }

    public Country() {

    }

    public Country(String id, String name, Region region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) && Objects.equals(name, country.name) && Objects.equals(region, country.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", region=" + region +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }
}
