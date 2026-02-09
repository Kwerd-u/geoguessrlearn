package com.kwerdu.geoguessrlearn.ui;

import com.kwerdu.geoguessrlearn.customComponents.CountryButton;

import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.util.List;

public class UITemplates {
    public static JPanel Question(JComponent tip, JComponent question, JComponent topLeft, JComponent topRight,
                                  JComponent bottomLeft, JComponent bottomRight){

        JPanel questionPanel = new JPanel(new BorderLayout());


        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));

        questionPanel.add(tip, BorderLayout.NORTH);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.add(question);
        panel.add(quarters(topLeft, topRight, bottomLeft, bottomRight));

        questionPanel.add(panel, BorderLayout.CENTER);
        return questionPanel;
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

    public static JScrollPane countryScrollPane(List<CountryButton> buttonList){
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (JButton button : buttonList) {
            JPanel item = new JPanel(new BorderLayout());
            item.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            item.add(button, BorderLayout.CENTER);
            listPanel.add(item);
        }

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        return scroll;
    }

}
