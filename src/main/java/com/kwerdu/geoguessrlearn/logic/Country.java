package com.kwerdu.geoguessrlearn.logic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kwerdu.geoguessrlearn.logic.features.RegionFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Country {
    private String name;
    private List<Region> regions;

    @JsonIgnore
    private List<Region> regionPool = new ArrayList<>();
    @JsonIgnore
    private List<Region> otherRegionPool = new ArrayList<>();;
    @JsonIgnore
    private int regionPoolAccuracy;

    public Country() {
    }

    public void updateOtherRegionPool(){
        otherRegionPool.addAll(regions);
    }
    public void updateRegionPool(){
        while ((regionPoolAccuracy > 70) || (regionPool.size() < 4) || (otherRegionPool.size() > 0)) {
            Region tempRegion = otherRegionPool.get((int) (Math.random() * otherRegionPool.size()));
            regionPool.add(tempRegion);
            otherRegionPool.remove(tempRegion);
        }

        for (Region region : regionPool) {
            System.out.println(region.getName());
        }

        updateRegionPoolAccuracy();
    }
    public void updateRegionPoolAccuracy(){
        int sum = 0;
        int count = 0;
        for (Region region : regionPool) {
            sum += region.getAccuracy();
            count++;
        }
        regionPoolAccuracy = sum / count;
    }

    @JsonIgnore
    public Region getRandomRegion() {
        return regions.get((int)(Math.random() * regions.size()));
    }

    public void nextRoundRegions(){
        for(Region region : regions){
            region.nextRound();
            region.updateChoiceFactor();
        }
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

    @JsonIgnore
    public List<Region> getRegionPool() {
        return regionPool;
    }

    public void setRegionPool(List<Region> regionPool) {
        this.regionPool = regionPool;
    }

    @JsonIgnore
    public List<Region> getOtherRegionPool() {
        return otherRegionPool;
    }

    public void setOtherRegionPool(List<Region> otherRegionPool) {
        this.otherRegionPool = otherRegionPool;
    }

    @JsonIgnore
    public int getRegionListAccuracy() {
        return regionPoolAccuracy;
    }
}
