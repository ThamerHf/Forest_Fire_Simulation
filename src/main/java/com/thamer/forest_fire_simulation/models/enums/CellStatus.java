package com.thamer.forest_fire_simulation.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CellStatus {
    GREEN("GREEN"),
    FIRE("FIRE"),
    ASH("ASH");

    private final String value;

    CellStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static CellStatus fromValue(String value) {
        for (CellStatus status : CellStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
