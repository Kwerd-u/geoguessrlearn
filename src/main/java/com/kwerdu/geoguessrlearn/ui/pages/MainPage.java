package com.kwerdu.geoguessrlearn.ui.pages;

import com.kwerdu.geoguessrlearn.ui.Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Component
public class MainPage extends Page {

    public MainPage() {
        refresh();
    }

    @Autowired
    Navigator navigator;

    @Override
    protected JPanel createContent() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("GeoGuessr Learn", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(0, 122, 255));

        JButton learnButton = new JButton("Начать обучение");
        learnButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        learnButton.addActionListener(this::onLearnClick);

        JLabel statusLabel = new JLabel("Готов к обучению...", SwingConstants.CENTER);

        panel.add(title, BorderLayout.NORTH);
        panel.add(learnButton, BorderLayout.CENTER);
        panel.add(statusLabel, BorderLayout.SOUTH);
        return  panel;
    }

    private void onLearnClick(ActionEvent e) {
        navigator.showQuestionPage();
    }
}

