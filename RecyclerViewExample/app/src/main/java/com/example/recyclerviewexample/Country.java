package com.example.recyclerviewexample;

public class Country {

    private String name;
    private long population;
    private int flagResId;
    private boolean isGood;

    public Country(String name, long population, int flagResId, boolean isGood) {
        this.name = name;
        this.population = population;
        this.flagResId = flagResId;
        this.isGood = isGood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
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
}
