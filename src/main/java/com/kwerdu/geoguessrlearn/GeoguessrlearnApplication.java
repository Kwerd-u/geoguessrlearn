package com.kwerdu.geoguessrlearn;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import com.kwerdu.geoguessrlearn.ui.pages.MainPage;
import javax.swing.*;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GeoguessrlearnApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(GeoguessrlearnApplication.class)
                        .headless(false)
                        .web(WebApplicationType.NONE)
                        .run(args);

        SwingUtilities.invokeLater(() -> {
            MainPage mainPage = context.getBean(MainPage.class);
            mainPage.showSelf(context);
        });
    }
}

