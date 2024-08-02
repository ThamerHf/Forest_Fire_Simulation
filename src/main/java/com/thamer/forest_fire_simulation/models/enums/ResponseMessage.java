package com.thamer.forest_fire_simulation.models.enums;

public enum ResponseMessage {

    INTERNAL_ERROR_MESSAGE("Server Internal Error"),
    WRONG_FOREST_DIMENSION(
            "The forest dimensions are incorrect. Please provide an initialized grid with valid dimensions.");
    private String message;

    private ResponseMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}