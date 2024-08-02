package com.thamer.forest_fire_simulation.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GenericResponseDto implements Serializable {

    private  static  final  long serialVersionUID = -1738208626726064922L;

    private String message;

    public GenericResponseDto(String message) {
        this.message = message;
    }

    public GenericResponseDto() {

    }
}