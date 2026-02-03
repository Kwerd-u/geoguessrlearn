package com.kwerdu.geoguessrlearn.logic;

import java.util.List;

public class Country {
    private String name;
    private List<Region> regions;

    public Country(){}

    public Country(String name, List<Region> regionList) {
        this.name = name;
        this.regions = regionList;
    }

    public Region getRandomRegion() {
        return regions.get((int)(Math.random() * regions.size()));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
}
