package com.kwerdu.geoguessrlearn.logic;

import com.kwerdu.geoguessrlearn.logic.features.RegionFeature;
import com.kwerdu.geoguessrlearn.ui.AnswerButton;
import com.kwerdu.geoguessrlearn.ui.Navigator;
import com.kwerdu.geoguessrlearn.ui.UITemplates;
import com.kwerdu.geoguessrlearn.ui.pages.QuestionPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;

@Service
public class GameService {
    @Autowired
    @Lazy
    QuestionPage questionPage;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    Navigator  navigator;

    private RegionFeature feature;
    private boolean flag = true;

    public GameService() {}

    public void checkAnswer() {
        highlightAllButtonsRecursively(questionPage.getPanel());
        new Timer(1000, e -> {

            navigator.showQuestionPage();

            ((Timer)e.getSource()).stop();
        }).start();
    }

    private void highlightAllButtonsRecursively(JComponent parent) {
        for (Component comp : parent.getComponents()) {
            if (comp instanceof AnswerButton btn) {
                btn.isCorrect();
                System.out.println(flag);
            } else if (comp instanceof JComponent container) {
                highlightAllButtonsRecursively(container);
            }
        }
    }

    public JPanel getQuestion(){
        flag = true;
        System.out.println(flag);
        JPanel panel;

        Country selectedCountry = countryRepository.getSelectedCountry();
        List<Region> regions = selectedCountry.getRegions();

        for (Region region : regions) {
            region.updateAccuracy();
        }

        regions.sort(Comparator.comparing(Region::getAccuracy).thenComparing(Region::getName));

        List<Region> selectedRegions = regions.subList(0, 4);
        Region selectedRegion = selectedRegions.get(0);
        List<RegionFeature> regionFeatures = selectedRegion.getFeatures();

        regionFeatures.sort(Comparator.comparing(RegionFeature::getAccuracy));

        RegionFeature selectedRegionFeature = regionFeatures.get(0);
        feature = selectedRegionFeature;
        String selectedRegionFeatureType = selectedRegionFeature.getType();


        Random random = new Random();

        if (random.nextInt(2) == 0) { //вопрос - название региона, ответы - фичи
            List<RegionFeature> wrongFeatures = new ArrayList<>();
            for (int i = 1; i < 4; i++) {
                Region wrongRegion = selectedRegions.get(i);
                wrongFeatures.add(wrongRegion.getFeatureWithType(selectedRegionFeatureType));
            }

            List<AnswerButton> answerButtons = new ArrayList<>();
            answerButtons.add(new AnswerButton(selectedRegionFeature.getValue(), true, this));
            answerButtons.add(new AnswerButton(wrongFeatures.get(0).getValue(), false, this));
            answerButtons.add(new AnswerButton(wrongFeatures.get(1).getValue(), false, this));
            answerButtons.add(new AnswerButton(wrongFeatures.get(2).getValue(), false, this));

            Collections.shuffle(answerButtons);

            JLabel label = new JLabel(selectedRegion.getName());
            label.setFont(new Font("Segoe UI", Font.BOLD, 50));
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            panel = UITemplates.Question(
                    label,
                    answerButtons.get(0),
                    answerButtons.get(1),
                    answerButtons.get(2),
                    answerButtons.get(3)
            );


        }
        else { //вопрос - фича, ответы - названия регионов
            JLabel label = new JLabel(selectedRegionFeature.getValue());
            label.setFont(new Font("Segoe UI", Font.BOLD, 50));
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            List<AnswerButton> answerButtons = new ArrayList<>();
            answerButtons.add(new AnswerButton(selectedRegion.getName(), true, this));
            answerButtons.add(new AnswerButton(selectedRegions.get(1).getName(), false, this));
            answerButtons.add(new AnswerButton(selectedRegions.get(2).getName(), false, this));
            answerButtons.add(new AnswerButton(selectedRegions.get(3).getName(), false, this));

            Collections.shuffle(answerButtons);

            panel = UITemplates.Question(
                    label,
                    answerButtons.get(0),
                    answerButtons.get(1),
                    answerButtons.get(2),
                    answerButtons.get(3)
            );
        }
        return panel;
    }

    public void tryRight(){
        if (flag) {
            System.out.println(feature.getAccuracy());
            feature.accuracyUp();
            System.out.println(feature.getAccuracy());
        }
    }

    public void tryWrong(){
        if (flag) {
            feature.accuracyDown();
        }
    }

    public void setFlagFalse(){
        flag = false;
    }


}
