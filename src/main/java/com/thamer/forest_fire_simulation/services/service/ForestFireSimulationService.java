package com.thamer.forest_fire_simulation.services.service;

import com.thamer.forest_fire_simulation.models.exceptions.CustomizedException;
import com.thamer.forest_fire_simulation.models.model.Forest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ForestFireSimulationService {
    public Forest initGrid();

    public List<Forest> simulate(Forest initializedForest) throws CustomizedException;
}
