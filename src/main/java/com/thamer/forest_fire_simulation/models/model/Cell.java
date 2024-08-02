package com.thamer.forest_fire_simulation.models.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thamer.forest_fire_simulation.models.enums.CellStatus;

import java.io.Serializable;
import java.util.Objects;

public class Cell implements Serializable {

    private static final long serialVersionUID = -8827779080476341167L;

    private CellStatus cellStatus = CellStatus.GREEN;

    public Cell() {
    }

    @JsonCreator
    public Cell(@JsonProperty("cellStatus") CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    @JsonProperty("cellStatus")
    public CellStatus getCellStatus() {
        return cellStatus;
    }

    @JsonProperty("cellStatus")
    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Boolean isFire() {
        return this.getCellStatus().equals(CellStatus.FIRE);
    }

    public boolean isGreen() {
        return this.getCellStatus().equals(CellStatus.GREEN);
    }

    public boolean isASH() {
        return this.getCellStatus().equals(CellStatus.ASH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return cellStatus == cell.cellStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellStatus);
    }
}
