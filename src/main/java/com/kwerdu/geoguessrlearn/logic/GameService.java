package com.kwerdu.geoguessrlearn.logic;

import com.kwerdu.geoguessrlearn.logic.features.RegionFeature;
import com.kwerdu.geoguessrlearn.customComponents.AnswerButton;
import com.kwerdu.geoguessrlearn.ui.Navigator;
import com.kwerdu.geoguessrlearn.ui.UIService;
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
    @Autowired
    UIService uiService;
    @Autowired
    ImageProvider imageProvider;

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
        JLabel label;
        JLabel tipLabel;
        Country selectedCountry = countryRepository.getSelectedCountry();

        selectedCountry.nextRoundRegions();

        List<Region> regions = selectedCountry.getRegionPool();
        regions.sort(Comparator.comparing(Region::getChoiceFactor));

        List<Region> selectedRegions = regions.subList(0, 4);
        Region selectedRegion = selectedRegions.get(0);

        selectedRegion.pickUp();

        List<RegionFeature> regionFeatures = selectedRegion.getFeatures();
        regionFeatures.sort(Comparator.comparing(RegionFeature::getChoiceFactor).thenComparing(new Comparator<RegionFeature>() {
            @Override
            public int compare(RegionFeature o1, RegionFeature o2) {
                Random random = new Random();
                if (random.nextBoolean()){
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }));
        for (RegionFeature regionFeature : regionFeatures){
            System.out.println(regionFeature.getName() + ":" + regionFeature.getChoiceFactor());
        }

        RegionFeature selectedRegionFeature = regionFeatures.get(0);
        feature = selectedRegionFeature;
        feature.pickUp();

        for (RegionFeature regionFeature : regionFeatures){
            System.out.println(regionFeature.getName() + ":" + regionFeature.getChoiceFactor());
        }

        List<RegionFeature> Features = new ArrayList<>();
        Features.add(selectedRegionFeature);

        String selectedRegionFeatureType = selectedRegionFeature.getType();
        for (int i = 1; i < 4; i++) {
            Region region = selectedRegions.get(i);
            Features.add(region.getFeatureWithType(selectedRegionFeatureType));
        }

        Random random = new Random();

        if (random.nextBoolean()) {
            label = new JLabel(imageProvider.getImageIcon(selectedCountry, selectedRegion));

            answerButtons.add(Features.get(0).getAnswerButton(this, true));
            for (int i = 1; i < 4; i++){
                answerButtons.add(Features.get(i).getAnswerButton(this, false));
            }
        }
        else {
            label = selectedRegionFeature.getQuestion();

            AnswerButton tempButton = new AnswerButton("", true, this, selectedRegionFeature);
            tempButton.setIcon(imageProvider.getImageIcon(selectedCountry, selectedRegion));

            answerButtons.add(tempButton);

            for (int i = 1; i < 4; i++){
                tempButton = new AnswerButton("", false, this, Features.get(i));
                tempButton.setIcon(imageProvider.getImageIcon(selectedCountry, regions.get(i)));

                answerButtons.add(tempButton);
            }
        }

        tipLabel = new JLabel(selectedRegionFeature.getName());
        tipLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tipLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);


        Collections.shuffle(answerButtons);

        label.setFont(new Font("Segoe UI", Font.BOLD, 50));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);


        panel = UITemplates.Question(
                tipLabel,
                label,
                answerButtons.get(0),
                answerButtons.get(1),
                answerButtons.get(2),
                answerButtons.get(3)
        );

        return panel;
    }

    public void guess(boolean right, RegionFeature feature2) {
        if (flag) {
            feature.guess(right);
            feature2.guess(right);
            countryRepository.getSelectedCountry().updateRegionPoolAccuracy();
        }
    }
}
