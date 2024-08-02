package com.thamer.forest_fire_simulation.api;

import com.thamer.forest_fire_simulation.models.exceptions.CustomizedException;
import com.thamer.forest_fire_simulation.models.model.Forest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface ForestFireSimulationApi {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String getEmptyGrid(Model model) throws CustomizedException;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Forest> getEmptyGrid() throws CustomizedException;

    @PostMapping(value = "/simulate", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Forest>> simulate(@RequestBody(required = true) Forest initializedForest) throws CustomizedException;

    @PostMapping(value = "/simulate", produces = MediaType.TEXT_HTML_VALUE)
    public String simulate(@RequestBody(required = true) Forest initializedForest, Model model) throws CustomizedException;

}
