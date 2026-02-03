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
    public RegionFeature() {}
    public abstract JPanel render();
}
