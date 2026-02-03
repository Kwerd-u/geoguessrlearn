package com.kwerdu.geoguessrlearn.ui;

import com.kwerdu.geoguessrlearn.ui.pages.Page;
import com.kwerdu.geoguessrlearn.ui.pages.QuestionPage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Navigator {
    private final ApplicationContext context;
    private final UIService uiService;

    public Navigator(ApplicationContext context, UIService uiService) {
        this.context = context;
        this.uiService = uiService;
    }

    public void showPage(Class<? extends Page> pageClass) {
        Page page = context.getBean(pageClass);
        uiService.ShowPanel(page.getPanel());
    }

    public void showQuestionPage(){
        QuestionPage questionPage = context.getBean(QuestionPage.class);
        questionPage.refresh();
        uiService.ShowPanel(questionPage.getPanel());
    }
}

