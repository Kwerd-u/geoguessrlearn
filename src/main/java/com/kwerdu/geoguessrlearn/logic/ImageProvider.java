package com.kwerdu.geoguessrlearn.logic;

import org.springframework.stereotype.Service;

import javax.swing.*;
@Service
public class ImageProvider {
    public ImageIcon getImageIcon(Country country, Region region){
        return new ImageIcon(getClass().getResource("/positions/" + country.getName() + "/" + region.getName() + ".jpg"));
    }
}
