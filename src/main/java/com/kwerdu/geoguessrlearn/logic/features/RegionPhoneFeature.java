package com.kwerdu.geoguessrlearn.logic.features;

import javax.swing.*;

public class RegionPhoneFeature extends RegionFeature {

    public RegionPhoneFeature() {
        setType("RegionPhoneFeature");
    }

    @Override
    public JPanel render() {
        JPanel card = new JPanel();
        JLabel regionPhoneLabel = new JLabel(String.valueOf(getValue()));
        regionPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(regionPhoneLabel);
        return card;
    }
}
