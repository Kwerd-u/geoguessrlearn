package com.kwerdu.geoguessrlearn.logic.features;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.swing.*;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegionNumberFeature.class, name = "regionNumber"),
        //@JsonSubTypes.Type(value = PhoneCodeFeature.class, name = "phoneCode"),
        //@JsonSubTypes.Type(value = LicensePlateFeature.class, name = "licensePlate")
})
public abstract class RegionFeature {
    private int accuracy;
    private String value;
    private String type;

    public RegionFeature() {}
    public abstract JPanel render();
    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void accuracyUp(){
        accuracy += 10;
    }

    public void accuracyDown(){
        accuracy -= 10;
    }
}
