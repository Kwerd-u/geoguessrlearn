package com.kwerdu.geoguessrlearn.ui.pages;

import com.kwerdu.geoguessrlearn.ui.UIService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

public abstract class Page {
    protected JPanel panel;

    protected Page() {
        this.panel = new JPanel();
    }

    public void showSelf(ApplicationContext context) {
        UIService uiService = context.getBean(UIService.class);
        uiService.ShowPanel(panel);
    }

    public void refresh() {
        panel.removeAll();
        panel.add(createContent());
        panel.revalidate();
        panel.repaint();

        Component parent = panel.getParent();
        if (parent != null) {
            parent.revalidate();
            parent.repaint();
        }
    }

    protected abstract JPanel createContent();

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}

