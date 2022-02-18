package com.company.domain;

import java.util.Objects;

public class Region {
    private Long id;
    private String regionName;

    public Region() {
    }

    public Region(Long id, String regionName) {
        this.id = id;
        this.regionName = regionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(id, region.id) && Objects.equals(regionName, region.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regionName);
    }
}
