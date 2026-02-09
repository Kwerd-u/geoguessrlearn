package com.kwerdu.geoguessrlearn.ui;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

@Service
public class UIService {
    private JFrame frame;

    @PostConstruct
    public void init() {
        frame = new JFrame("GeoGuessr Learn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }

    public void ShowPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}
