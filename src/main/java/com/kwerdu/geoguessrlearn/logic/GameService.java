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
            } else if (comp instanceof JComponent container) {
                highlightAllButtonsRecursively(container);
            }
        }
    }

    public JPanel getQuestion(){
        flag = true;
        JPanel panel;
        List<AnswerButton> answerButtons = new ArrayList<>();
        JLabel label = new JLabel();
        JLabel questionLabel =  new JLabel();


        Country selectedCountry = countryRepository.getSelectedCountry();

        selectedCountry.nextRoundRegions();

        List<Region> regions = selectedCountry.getRegions();
        regions.sort(Comparator.comparing(Region::getChoiceFactor));
        List<Region> selectedRegions = regions.subList(0, 4);


        Region selectedRegion = selectedRegions.get(0);
        selectedRegion.pickUp();

        List<RegionFeature> regionFeatures = selectedRegion.getFeatures();
        regionFeatures.sort(Comparator.comparing(RegionFeature::getAccuracy));

        RegionFeature selectedRegionFeature1 = regionFeatures.get(0);
        feature = selectedRegionFeature1;
        RegionFeature selectedRegionFeature2 = regionFeatures.get(1);


        String selectedRegionFeatureType1 = selectedRegionFeature1.getType();
        String selectedRegionFeatureType2 = selectedRegionFeature2.getType();


        List<RegionFeature> Features1 = new ArrayList<>();
        Features1.add(selectedRegionFeature1);
        List<RegionFeature> Features2 = new ArrayList<>();
        Features2.add(selectedRegionFeature2);

        for (int i = 1; i < 4; i++) {
            Region region = selectedRegions.get(i);
            Features1.add(region.getFeatureWithType(selectedRegionFeatureType1));
            Features2.add(region.getFeatureWithType(selectedRegionFeatureType2));
        }

        Random random = new Random();

        if (random.nextBoolean()) {
            questionLabel.setText(selectedRegionFeature1.getName() + " — " + selectedRegionFeature2.getName());
            label.setText(selectedRegionFeature1.getValue());

            answerButtons.add(new AnswerButton(Features2.get(0).getValue(), true, this, Features2.get(0)));
            answerButtons.add(new AnswerButton(Features2.get(1).getValue(), false, this, Features2.get(1)));
            answerButtons.add(new AnswerButton(Features2.get(2).getValue(), false, this, Features2.get(2)));
            answerButtons.add(new AnswerButton(Features2.get(3).getValue(), false, this, Features2.get(3)));

        }
        else {
            questionLabel.setText(selectedRegionFeature2.getName() + " — " + selectedRegionFeature1.getName());
            label.setText(selectedRegionFeature2.getValue());

            answerButtons.add(new AnswerButton(Features1.get(0).getValue(), true, this, Features1.get(0)));
            answerButtons.add(new AnswerButton(Features1.get(1).getValue(), false, this, Features1.get(1)));
            answerButtons.add(new AnswerButton(Features1.get(2).getValue(), false, this, Features1.get(2)));
            answerButtons.add(new AnswerButton(Features1.get(3).getValue(), false, this, Features1.get(3)));
        }

        Collections.shuffle(answerButtons);

        label.setFont(new Font("Segoe UI", Font.BOLD, 50));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        questionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);


        panel = UITemplates.Question(
                questionLabel,
                label,
                answerButtons.get(0),
                answerButtons.get(1),
                answerButtons.get(2),
                answerButtons.get(3)
        );



        return panel;
    }

    public void tryRight() {
        if (flag) {
            feature.accuracyUp();
        }
    }

    public void tryWrong(RegionFeature feature_){
        if (flag) {
            feature.accuracyDown();
            feature_.accuracyDown();
        }
    }

    public void setFlagFalse(){
        flag = false;
    }


}
