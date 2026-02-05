package com.kwerdu.geoguessrlearn.logic;

import com.kwerdu.geoguessrlearn.logic.features.RegionFeature;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Region {
    private String name;
    private List<RegionFeature> features = new ArrayList<>();
    private int accuracy;

    public Region(){}
    public Region(String name) {this.name = name;}
    public Region(String name, List<RegionFeature> features) {this.name = name;this.features = features;}

    public void updateAccuracy(){
        int sum = 0;
        int count = 0;
        for (RegionFeature feature : features) {
            sum += feature.getAccuracy();
            count++;
        }
        accuracy = sum / count;
    }

    public void addFeature(RegionFeature feature) {
        features.add(feature);
    }

    public String getName() {return name;}

    public List<RegionFeature> getFeatures() {
        return features;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeatures(List<RegionFeature> features) {
        this.features = features;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public RegionFeature getFeatureWithType(String type){
        for(RegionFeature feature : features){
            if(feature.getType().equals(type)){
                return feature;
            }
        }
        return null;
    }
}