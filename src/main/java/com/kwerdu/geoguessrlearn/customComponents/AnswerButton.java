package com.kwerdu.geoguessrlearn.customComponents;

import com.kwerdu.geoguessrlearn.logic.GameService;
import com.kwerdu.geoguessrlearn.logic.features.RegionFeature;

import javax.swing.*;
import java.awt.*;

public class AnswerButton extends JButton {
    private boolean correct;
    private GameService gameService;
    private RegionFeature regionFeature;


    public AnswerButton(String text, boolean b, GameService gameService, RegionFeature regionFeature) {
        super(text);
        correct = b;
        this.gameService = gameService;
        this.addActionListener(e -> {
            changeAccuracy();
            this.gameService.checkAnswer();
        });
        this.regionFeature = regionFeature;
    }

    private void changeAccuracy(){
        gameService.guess(correct, regionFeature);
    }

    public void isCorrect() {
        if (correct) {
            this.setBackground(Color.BLUE);
        }
        else  {
            this.setBackground(Color.RED);
        }
        repaint();
        revalidate();
    }
}
