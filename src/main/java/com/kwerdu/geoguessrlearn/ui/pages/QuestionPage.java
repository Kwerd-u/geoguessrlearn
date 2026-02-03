package com.kwerdu.geoguessrlearn.ui.pages;

import com.kwerdu.geoguessrlearn.logic.Country;
import com.kwerdu.geoguessrlearn.logic.CountryRepository;
import com.kwerdu.geoguessrlearn.logic.Region;
import com.kwerdu.geoguessrlearn.logic.features.RegionNumberFeature;
import com.kwerdu.geoguessrlearn.ui.AnswerButton;
import com.kwerdu.geoguessrlearn.ui.UITemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

@Component()
public class QuestionPage extends Page
{
    @Autowired
    CountryRepository countryRepository;

    @Override
    protected JPanel createContent() {
        Country country = countryRepository.getRandomCountry();
        Region region = country.getRandomRegion();
        JPanel questionPanel = new JPanel();
        JLabel label = new JLabel(region.getName());
        label.setFont(new Font("Segoe UI", Font.BOLD, 50));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AnswerButton button1 = new AnswerButton("1", true);
        AnswerButton button2 = new AnswerButton("2", false);
        AnswerButton button3 = new AnswerButton("3", false);
        AnswerButton button4 = new AnswerButton("4", false);
        /*
            рандомим фичу
            рандомим название или фичу отгадываем
            список кнопок
            список регионов
            взять рандомную кнопку
            прикрепить к ней регион
            взять еще 3 региона и прикрепить к остальным кнопкам
            задать тру-фолс

            сделать сервис кнопок
        */
        questionPanel = UITemplates.Question(label, button1, button2, button3, button4);
        return questionPanel;
    }
}
