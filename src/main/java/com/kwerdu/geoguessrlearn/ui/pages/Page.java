package com.kwerdu.geoguessrlearn.ui.pages;

import com.kwerdu.geoguessrlearn.ui.UIService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

public abstract class Page {
    protected JPanel panel = new JPanel(new BorderLayout());

    public void showSelf(ApplicationContext context) {
        UIService uiService = context.getBean(UIService.class);
        uiService.ShowPanel(panel);
    }

    public void refresh() {
        JPanel newContent = createContent();
        panel.removeAll();
        panel.add(newContent, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();
    }

    protected abstract JPanel createContent();

    public JPanel getPanel() {
        return panel;
    }
}

