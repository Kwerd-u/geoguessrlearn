package com.kwerdu.geoguessrlearn.ui;

import javax.swing.*;
import java.awt.*;

public class AnswerButton extends JButton {
    private boolean correct;

    public AnswerButton(String text, boolean b) {
        super(text);
        correct = b;
        this.addActionListener(e -> isCorrect());
    }

    public void isCorrect() {
        if (correct) {
            this.setBackground(Color.BLUE);
        }
        else  {
            this.setBackground(Color.RED);
        }
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
