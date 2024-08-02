package com.thamer.forest_fire_simulation.models.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Arrays;

@Slf4j
public class Forest implements Serializable {

    private static final long serialVersionUID = -2428125667349243194L;

    @Getter
    @Setter
    private Cell[][] forestGrid;

    private boolean isBurning = false;

    public Forest(int h, int l) {
        this.forestGrid = new Cell[h][l];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                this.forestGrid[i][j] = new Cell();
            }
        }
    }

    public Forest(Forest forest) {
        this.forestGrid = this.copyForestGrid(forest.getForestGrid());
    }

    public Cell[][] copyForestGrid(Cell[][] original) {
        if (original == null) {
            return null;
        }

        int rows = original.length;
        Cell[][] copy = new Cell[rows][];

        for (int i = 0; i < rows; i++) {
            copy[i] = new Cell[original[i].length];
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = new Cell(original[i][j].getCellStatus());
            }
        }

        return copy;
    }

    public void copyForest(Forest forest) {
        this.setForestGrid(this.copyForestGrid(forest.getForestGrid()));
        this.setBurning(forest.isBurning());
    }

    public boolean isBurning() {
        if (!this.isBurning) {
            int i = 0;
            while (i < this.getForestGrid().length && !this.isBurning) {
                int j = 0;
                while (j < this.getForestGrid()[0].length && !this.isBurning) {
                    if (this.getForestGrid()[i][j].isFire()) {
                        this.setBurning(true);
                    }

                    j++;
                }

                i++;
            }
        }

        return this.isBurning;
    }

    public void setBurning(boolean burning) {
        isBurning = burning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forest forest = (Forest) o;
        return Arrays.equals(forestGrid, forest.forestGrid);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(forestGrid);
    }
}
