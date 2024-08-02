package com.thamer.forest_fire_simulation.controllers;

import com.thamer.forest_fire_simulation.api.ForestFireSimulationApi;
import com.thamer.forest_fire_simulation.config.ForestProperties;
import com.thamer.forest_fire_simulation.models.exceptions.CustomizedException;
import com.thamer.forest_fire_simulation.models.model.Cell;
import com.thamer.forest_fire_simulation.models.model.Forest;
import com.thamer.forest_fire_simulation.services.service.ForestFireSimulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ForestFireSimulationController implements ForestFireSimulationApi {

    private final ForestFireSimulationService forestFireSimulationService;

    @Autowired
    public ForestFireSimulationController(ForestFireSimulationService forestFireSimulationService,
                                          ForestProperties forestProperties) {
        this.forestFireSimulationService = forestFireSimulationService;
    }


    @Override
    public String getEmptyGrid(Model model) throws CustomizedException {
        try {
            Forest forest = this.forestFireSimulationService.initGrid();
            model.addAttribute("initForest", forest.getForestGrid());
        } catch (Exception e) {
            this.logException("Error when initializing forest grid: ", e);
            throw new CustomizedException("Error when initializing forest grid: ",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return "selection";
    }

    @Override
    public ResponseEntity<Forest> getEmptyGrid() throws CustomizedException {
        Forest forest = null;
        try {
            forest = this.forestFireSimulationService.initGrid();
        } catch (Exception e) {
            this.logException("Error when initializing forest grid: ", e);
            throw new CustomizedException("Error when initializing forest grid: ",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(forest);
    }

    @Override
    public ResponseEntity<List<Forest>> simulate(Forest initializedForest) throws CustomizedException {
        List<Forest> forests = new ArrayList<>();
        try {
            forests = this.forestFireSimulationService.simulate(initializedForest);
        } catch (Exception e) {
            this.logException("Error when simulating forest fire ", e);
            throw new CustomizedException("Error when simulating forest fire ",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(forests);
    }

    @Override
    public String simulate(Forest initializedForest, Model model) throws CustomizedException {
        List<Forest> forests = new ArrayList<>();
        try {
            forests = this.forestFireSimulationService.simulate(initializedForest);
            model.addAttribute("steps", forests);
        } catch (Exception e) {
            this.logException("Error when simulating forest fire ", e);
            throw new CustomizedException("Error when simulating forest fire ",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return "simulation";
    }

    private void logException(String message, Exception e) {
        log.error(message + e.getMessage());
    }


}
