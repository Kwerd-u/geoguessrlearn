package com.kwerdu.geoguessrlearn.ui.pages;

import com.kwerdu.geoguessrlearn.customComponents.CountryButton;
import com.kwerdu.geoguessrlearn.logic.Country;
import com.kwerdu.geoguessrlearn.logic.CountryRepository;
import com.kwerdu.geoguessrlearn.ui.Navigator;
import com.kwerdu.geoguessrlearn.ui.UITemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainPage extends Page {
    @Autowired
    Navigator navigator;
    @Autowired
    CountryRepository  countryRepository;


    @Override
    protected JPanel createContent() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("GeoGuessr Learn", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(255, 0, 0));

        List<CountryButton> buttonList = new ArrayList<>();
        for (Country country : countryRepository.getCountries()){
            buttonList.add(new CountryButton(country, countryRepository));
        }

        JScrollPane countryPane = UITemplates.countryScrollPane(buttonList);
        countryPane.setPreferredSize(new Dimension((int) (screenSize.width * 0.66), (int) (screenSize.height * 0.33)));

        panel.add(title, BorderLayout.CENTER);
        panel.add(countryPane, BorderLayout.SOUTH);
        return  panel;
    }
}

