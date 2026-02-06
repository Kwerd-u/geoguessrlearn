package com.kwerdu.geoguessrlearn.logic.features;

import javax.swing.*;

public class RegionNameFeature extends RegionFeature{
    public RegionNameFeature() {
        setType("NameFeature");
    }

    @Override
    public JPanel render() {
        JPanel card = new JPanel();
        JLabel regionNameLabel = new JLabel(String.valueOf(getValue()));
        regionNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(regionNameLabel);
        return card;
    }
}
