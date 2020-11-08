package com.ilyabuglakov.matrix.model.cell.state;

import com.ilyabuglakov.matrix.model.cell.Cell;

/**
 * FilledState represents Cell with useful content
 */
public class FilledState extends CellState {
    /**
     * Cell passes himself to state to let state
     * change that cell, if needed.
     *
     * @param cell - context
     */
    public FilledState(Cell cell) {
        super(cell);
    }

    @Override
    public boolean changeContent(int content) {
        return false;
    }
}
