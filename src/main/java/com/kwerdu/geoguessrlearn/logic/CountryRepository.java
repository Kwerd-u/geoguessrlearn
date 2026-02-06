package com.kwerdu.geoguessrlearn.logic;

import jakarta.annotation.PreDestroy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryRepository {
    private List<Country> countries = new ArrayList<>();
    private Country selectedCountry;
    private final Path dataFile = Paths.get("data/countries.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public CountryRepository() {
        loadCountries();  // Загружаем из data/countries.json ИЛИ resources
    }

    private void loadCountries() {
        try {
            // 1. Пробуем data/countries.json (сохранённый прогресс)
            if (Files.exists(dataFile)) {
                countries = mapper.readValue(dataFile.toFile(),
                        new TypeReference<List<Country>>() {});
                System.out.println("✅ Загружен прогресс: " + dataFile);
                return;
            }

            // 2. Если нет — дефолт из resources
            ClassPathResource resource = new ClassPathResource("countries.json");
            countries = mapper.readValue(resource.getInputStream(),
                    new TypeReference<List<Country>>() {});
            System.out.println("📥 Загружен дефолт");

            // 3. Сразу сохраняем копию
            saveCountries();

        } catch (Exception e) {
            System.err.println("💥 Ошибка загрузки: " + e.getMessage());
            countries = new ArrayList<>();
        }
    }

    // 🔥 СОХРАНЯЕМ ПОСЛЕ КАЖДОГО ОТВЕТА!
    public void saveCountries() {
        try {
            Files.createDirectories(dataFile.getParent());
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(dataFile.toFile(), countries);
            System.out.println("💾 Сохранено accuracy");
        } catch (Exception e) {
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    @PreDestroy
    public void onShutdown() {
        saveCountries();  // Финальное сохранение
    }

    public Country getRandomCountry() {
        return countries.get((int)(Math.random() * countries.size()));
    }

    public void selectCountry() {selectedCountry = countries.get(0);}
    public Country getSelectedCountry() {return selectedCountry;}
}
