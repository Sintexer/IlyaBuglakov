package com.ilyabuglakov.matrix.model;

import com.ilyabuglakov.matrix.model.cell.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Row class contains List of Cells
 */
public class Row {
    private List<Cell> cells;

    public Row() {
    }

    public Cell get(int index) {
        return cells.get(index);
    }

    public void setCells(List<Cell> cells) {
        this.cells = new ArrayList<>(cells);
    }

    public int size() {
        return cells.size();
    }

    @Override
    public String toString() {
        return cells.toString();
    }
}
