package com.kwerdu.geoguessrlearn.logic;

import com.kwerdu.geoguessrlearn.logic.features.RegionFeature;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Region {
    private String name;
    private List<RegionFeature> features = new ArrayList<>();

    public Region(){}
    public Region(String name) {this.name = name;}
    public Region(String name, List<RegionFeature> features) {this.name = name;this.features = features;}

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
}