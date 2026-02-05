package com.kwerdu.geoguessrlearn.ui.pages;

import com.kwerdu.geoguessrlearn.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class QuestionPage extends Page
{
    @Autowired
    GameService gameService;

    @Override
    protected JPanel createContent() {
        return gameService.getQuestion();
    }
}
