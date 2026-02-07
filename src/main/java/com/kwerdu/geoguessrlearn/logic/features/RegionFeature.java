package com.kwerdu.geoguessrlearn.logic.features;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.swing.*;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegionNumberFeature.class, name = "RegionNumberFeature"),
        @JsonSubTypes.Type(value = RegionPhoneFeature.class, name = "RegionPhoneFeature"),
        @JsonSubTypes.Type(value = RegionNameFeature.class, name = "RegionNameFeature"),
        //@JsonSubTypes.Type(value = LicensePlateFeature.class, name = "licensePlate")
})
public abstract class RegionFeature {
    private int accuracy;
    private String value;
    private String name;

    private int[] lastFiveGuesses = new int[5];

    @JsonIgnore
    private int roundsAgo = 0;
    @JsonIgnore
    private int choiceFactor = 0;

    @JsonIgnore
    private String type;

    public RegionFeature() {}
    public abstract JPanel render();

    public void updateChoiceFactor(){
        updateAccuracy();
        choiceFactor = accuracy - roundsAgo * 5;
    }
    @JsonIgnore
    public int getChoiceFactor() {
        return choiceFactor;
    }

    public void pickUp(){
        roundsAgo = 0;
    }
    public void nextRound(){
        roundsAgo++;
    }


    public int getAccuracy() {
        return accuracy;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void guess(boolean right){
        if (right){
            insertGuess(1);
        }
        else {
            insertGuess(-1);
        }
        updateAccuracy();
    }

    private void insertGuess(int guess){
        int a = lastFiveGuesses[0];
        for (int i = 1; i < lastFiveGuesses.length; i++){
            lastFiveGuesses[i - 1] = guess;
            guess = a;
            a = lastFiveGuesses[i];
        }
    }

    private void updateAccuracy(){
        int sum = 0;
        int count = 0;
        for (int i = 0; i < lastFiveGuesses.length; i++) {
            if (lastFiveGuesses[i] != 0) {
                if (lastFiveGuesses[i] == 1) {
                    sum += lastFiveGuesses[i];
                }
                count++;
            }
        }
        if (count != 0) {
            accuracy = sum * 100 / count;
        }
        else {
            accuracy = 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getLastFiveGuesses() {
        return lastFiveGuesses;
    }

    public void setLastFiveGuesses(int[] lastFiveGuesses) {
        this.lastFiveGuesses = lastFiveGuesses;
    }
}
