package com.kwerdu.geoguessrlearn.logic.features;

import com.kwerdu.geoguessrlearn.logic.GameService;
import com.kwerdu.geoguessrlearn.ui.AnswerButton;

import javax.swing.*;
import java.net.URL;

public class RegionGeopositionFeature extends RegionFeature{
    public RegionGeopositionFeature() {
        setType("RegionGeopositionFeature");
    }

    @Override
    public AnswerButton getAnswerButton(GameService gameService, boolean correct) {
        AnswerButton btn = new AnswerButton("", correct, gameService, this);

        URL url = getClass().getResource(getValue()); // например "/images/moscow.png"
        if (url == null) {
            System.out.println("Не найден ресурс: " + getValue());
            return btn;
        }

        btn.setIcon(new ImageIcon(url));
        btn.setText(""); // на всякий
        return btn;
    }

    @Override
    public JLabel getQuestion() {
        URL url = getClass().getResource(getValue());
        if (url == null) return new JLabel("NO IMAGE: " + getValue());
        return new JLabel(new ImageIcon(url));
    }

}
