package com.kwerdu.geoguessrlearn.logic.features;

import javax.swing.*;
import java.awt.*;

public class RegionNumberFeature extends RegionFeature {
    private int value;

    public RegionNumberFeature() {}

    public RegionNumberFeature(int number) {
        this.value = number;
    }

    @Override
    public JPanel render() {
        JPanel card = new JPanel();
        JLabel regionNumberLabel = new JLabel(String.valueOf(value));
        regionNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(regionNumberLabel);
        return card;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
