package com.thamer.forest_fire_simulation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("forest")
public class ForestProperties {

    private int h;

    private int l;

    private double probability;

    public int getH() {
        return h;
    }

    public int getL() {
        return l;
    }

    public double getProbability() {
        return probability;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setL(int l) {
        this.l = l;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
