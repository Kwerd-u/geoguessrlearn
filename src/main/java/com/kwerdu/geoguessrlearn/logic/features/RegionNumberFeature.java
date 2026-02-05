package com.kwerdu.geoguessrlearn.logic.features;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.swing.*;
import java.awt.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class RegionNumberFeature extends RegionFeature {

    public RegionNumberFeature() {
        setType("RegionNumberFeature");
    }

    @Override
    public JPanel render() {
        JPanel card = new JPanel();
        JLabel regionNumberLabel = new JLabel(String.valueOf(getValue()));
        regionNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(regionNumberLabel);
        return card;
    }
}
