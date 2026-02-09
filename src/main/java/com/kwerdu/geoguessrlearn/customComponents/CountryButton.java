package com.kwerdu.geoguessrlearn.customComponents;

import com.kwerdu.geoguessrlearn.logic.Country;
import com.kwerdu.geoguessrlearn.logic.CountryRepository;
import com.kwerdu.geoguessrlearn.ui.Navigator;

import javax.swing.*;

public class CountryButton extends JButton {
    private Country country;
    private CountryRepository countryRepository;
    public CountryButton(Country country, CountryRepository countryRepository){
        this.countryRepository = countryRepository;
        this.country = country;
        setText(country.getName());
        addActionListener(e-> selectCountry());
    }

    public void selectCountry(){
        countryRepository.selectCountry(country);
    }
}
