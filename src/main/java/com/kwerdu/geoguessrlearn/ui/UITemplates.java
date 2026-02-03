package com.kwerdu.geoguessrlearn.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.Component;

public class UITemplates {
    public static JPanel Question(JComponent question, JComponent topLeft, JComponent topRight,
                                  JComponent bottomLeft, JComponent bottomRight){
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.add(question);
        panel.add(quarters(topLeft, topRight, bottomLeft, bottomRight));
        return panel;
    }

    public static JPanel quarters(JComponent topLeft, JComponent topRight,
                                  JComponent bottomLeft, JComponent bottomRight) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(topLeft);
        panel.add(topRight);
        panel.add(bottomLeft);
        panel.add(bottomRight);
        return panel;
    }
}
