package com.kwerdu.geoguessrlearn.ui;

import com.kwerdu.geoguessrlearn.logic.GameService;

import javax.swing.*;
import java.awt.*;

public class AnswerButton extends JButton {
    private boolean correct;
    private GameService gameService;

    public AnswerButton(String text, boolean b, GameService gameService) {
        super(text);
        correct = b;
        this.gameService = gameService;
        this.addActionListener(e -> {
            changeAccuracy();
            this.gameService.checkAnswer();
        });
    }

    private void changeAccuracy(){
        if (correct) {
            gameService.tryRight();
            gameService.setFlagFalse();
        }
        else  {
            gameService.tryWrong();
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
