package com.kwerdu.geoguessrlearn.ui;

import com.kwerdu.geoguessrlearn.logic.GameService;
import com.kwerdu.geoguessrlearn.logic.Region;
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
        if (correct) {
            gameService.tryRight();
            gameService.setFlagFalse();
        }
        else  {
            gameService.tryWrong(regionFeature);
            gameService.setFlagFalse();
        }
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

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
