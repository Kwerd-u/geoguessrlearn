package com.kwerdu.geoguessrlearn.ui.pages;

import com.kwerdu.geoguessrlearn.ui.UIService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

public abstract class Page {
    protected JPanel panel;

    protected Page() {
        this.panel = new JPanel(new BorderLayout());
    }

    public void showSelf(ApplicationContext context) {
        UIService uiService = context.getBean(UIService.class);
        uiService.ShowPanel(panel);
    }

    public void refresh() {
        // НЕ удаляем panel — обновляем КОНТЕНТ!
        JPanel newContent = createContent();


        // 🔥 Заменяем только содержимое!
        panel.removeAll();
        panel.add(newContent, BorderLayout.CENTER);  // ЯВНО указываем позицию!

        panel.revalidate();
        panel.repaint();
    }



    protected abstract JPanel createContent();

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}

