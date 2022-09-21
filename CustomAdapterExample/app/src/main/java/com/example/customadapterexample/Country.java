package com.example.customadapterexample;

public class Country {
    private String name;
    private int flagResId;
    private boolean isGood;
    private long population;
    private boolean hasPopulation;

    public Country(String name, int flagResId, boolean isGood) {
        this.name = name;
        this.flagResId = flagResId;
        this.isGood = isGood;
    }

    public Country(String name, int flagResId, boolean isGood, long population) {
        this.name = name;
        this.flagResId = flagResId;
        this.isGood = isGood;
        this.population = population;
        hasPopulation = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlagResId() {
        return flagResId;
    }

    public void setFlagResId(int flagResId) {
        this.flagResId = flagResId;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public boolean isHasPopulation() {
        return hasPopulation;
    }

    public void setHasPopulation(boolean hasPopulation) {
        this.hasPopulation = hasPopulation;
    }
}
