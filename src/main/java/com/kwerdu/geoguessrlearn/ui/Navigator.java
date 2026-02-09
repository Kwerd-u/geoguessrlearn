package com.kwerdu.geoguessrlearn.ui;

import com.kwerdu.geoguessrlearn.ui.pages.Page;
import com.kwerdu.geoguessrlearn.ui.pages.QuestionPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Navigator {
    @Autowired
    private ApplicationContext context;

    public void showQuestionPage(){
        QuestionPage questionPage = context.getBean(QuestionPage.class);
        questionPage.refresh();
        context.getBean(UIService.class).ShowPanel(questionPage.getPanel());
    }
}

