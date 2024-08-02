package com.thamer.forest_fire_simulation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mersenne")
public class MersenneProperties {

    private int seed;

    private boolean initWithSeed;

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public boolean isInitWithSeed() {
        return initWithSeed;
    }

    public void setInitWithSeed(boolean initWithSeed) {
        this.initWithSeed = initWithSeed;
    }
}
