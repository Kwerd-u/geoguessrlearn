package com.kwerdu.geoguessrlearn.logic.features;

import com.kwerdu.geoguessrlearn.logic.GameService;
import com.kwerdu.geoguessrlearn.ui.AnswerButton;

import javax.swing.*;

public class RegionNumberFeature extends RegionFeature {

    public RegionNumberFeature() {
        setType("RegionNumberFeature");
    }

    @Override
    public AnswerButton getAnswerButton(GameService gameService, boolean b) {
        AnswerButton answerButton = new AnswerButton(getValue(), b, gameService, this);
        return answerButton;
    }

    @Override
    public JLabel getQuestion() {
        JLabel label = new JLabel(getValue());
        return label;
    }
}
