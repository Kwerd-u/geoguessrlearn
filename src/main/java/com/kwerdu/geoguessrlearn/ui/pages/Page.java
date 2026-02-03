package com.kwerdu.geoguessrlearn.ui.pages;

import com.kwerdu.geoguessrlearn.ui.UIService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

public abstract class Page {
    protected JPanel panel;

    protected Page() {
        this.panel = new JPanel();  // Пустая панель
    }

    public void showSelf(ApplicationContext context) {
        UIService uiService = context.getBean(UIService.class);
        uiService.ShowPanel(panel);
    }

    public void refresh() {
        panel = createContent();
    }

    protected abstract JPanel createContent();

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}

