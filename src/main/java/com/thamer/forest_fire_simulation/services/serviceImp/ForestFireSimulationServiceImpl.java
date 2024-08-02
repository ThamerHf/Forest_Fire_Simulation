package com.thamer.forest_fire_simulation.services.serviceImp;

import com.thamer.forest_fire_simulation.config.ForestProperties;
import com.thamer.forest_fire_simulation.config.MersenneProperties;
import com.thamer.forest_fire_simulation.models.enums.CellStatus;
import com.thamer.forest_fire_simulation.models.enums.ResponseMessage;
import com.thamer.forest_fire_simulation.models.exceptions.CustomizedException;
import com.thamer.forest_fire_simulation.models.model.Forest;
import com.thamer.forest_fire_simulation.services.service.ForestFireSimulationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.MersenneTwister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@Slf4j
public class ForestFireSimulationServiceImpl implements ForestFireSimulationService {

    private final ForestProperties forestProperties;

    private final MersenneProperties mersenneProperties;

    @Autowired
    public ForestFireSimulationServiceImpl(ForestProperties forestProperties, MersenneProperties mersenneProperties) {
        this.forestProperties = forestProperties;
        this.mersenneProperties = mersenneProperties;
    }

    @Override
    public Forest initGrid() {
        return new Forest(forestProperties.getH(), forestProperties.getL());
    }

    @Override
    public List<Forest> simulate(Forest initializedForest) throws CustomizedException {
        if (forestProperties.getH() != initializedForest.getForestGrid().length
        || forestProperties.getL() != initializedForest.getForestGrid()[0].length) {
            throw new CustomizedException(ResponseMessage.WRONG_FOREST_DIMENSION.toString(), HttpStatus.BAD_REQUEST);
        }

        List<Forest> forestBurnSteps = new ArrayList<>();
        Forest initForest = new Forest(forestProperties.getH(), forestProperties.getL());
        initForest.copyForest(initializedForest);
        forestBurnSteps.add(initForest);
        MersenneTwister generator = this.initGenerator();
        log.info("Simulation started");
        while (initializedForest.isBurning()) {
            Forest forest = this.simulateNextStep(initializedForest, generator);
            forestBurnSteps.add(forest);
        }

        return forestBurnSteps;
    }

    private Forest simulateNextStep(Forest forest, MersenneTwister generator) {
        Forest forest1 = new Forest(forestProperties.getH(), forestProperties.getL());
        forest.setBurning(false);
        for(int i = 0; i < forestProperties.getH(); i++) {
            for(int j = 0; j < forestProperties.getL(); j++) {
                if (forest.getForestGrid()[i][j].isFire()) {
                    this.burnCell(forest, forest1, i, j + 1, generator.nextDouble());
                    this.burnCell(forest, forest1, i, j - 1, generator.nextDouble());
                    this.burnCell(forest, forest1, i + 1, j, generator.nextDouble());
                    this.burnCell(forest, forest1,i - 1, j, generator.nextDouble());

                    forest1.getForestGrid()[i][j].setCellStatus(CellStatus.ASH);
                } else if (forest.getForestGrid()[i][j].isASH()) {
                    forest1.getForestGrid()[i][j].setCellStatus(CellStatus.ASH);
                }
            }
        }

        forest.copyForest(forest1);
        return forest1;
    }

    private boolean isInTheForestGrid(int x, int y) {
        return x >= 0 && x < forestProperties.getH()
                && y >= 0 && y < forestProperties.getL();
    }

    private boolean willCellBurn(double randomValue, boolean isCellGreen) {
        return (randomValue <= forestProperties.getProbability()) && isCellGreen;
    }

    private void burnCell(Forest forest, Forest forestToModify, int x, int y, double randomValue) {
        if (this.isInTheForestGrid(x, y)) {
            if (this.willCellBurn(randomValue, forest.getForestGrid()[x][y].isGreen())) {
                forestToModify.getForestGrid()[x][y].setCellStatus(CellStatus.FIRE);
                forestToModify.setBurning(true);
            }
        }
    }

    private MersenneTwister initGenerator() {
        if (mersenneProperties.isInitWithSeed()) {
            log.info("Initialize generator with seed = " + mersenneProperties.getSeed());
            return new MersenneTwister(mersenneProperties.getSeed());
        }

        log.info("Initialize generator without seed");
        return new MersenneTwister();
    }

}
