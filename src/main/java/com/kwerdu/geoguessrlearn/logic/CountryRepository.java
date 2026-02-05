package com.kwerdu.geoguessrlearn.logic;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryRepository {
    private final List<Country> countries;
    private Country selectedCountry;


    public CountryRepository() {
        this.countries = loadCountries();
    }

    private List<Country> loadCountries() {
        try {
            ClassPathResource resource = new ClassPathResource("countries.json");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resource.getInputStream(),
                    new TypeReference<List<Country>>() {});
        } catch (Exception e) {
            System.err.println("Ошибка JSON: " + e.getMessage());
            return new ArrayList<>();  // Пустой список при ошибке
        }
    }

    public Country getRandomCountry() {
        return countries.get((int)(Math.random() * countries.size()));
    }

    public void selectCountry() {selectedCountry = countries.get(0);}
    public Country getSelectedCountry() {return selectedCountry;}
}
