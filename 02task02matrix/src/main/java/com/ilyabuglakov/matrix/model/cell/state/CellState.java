package com.ilyabuglakov.matrix.model.cell.state;

import com.ilyabuglakov.matrix.model.cell.Cell;

/**
 * Interface of all Cell states.
 * Cell derives change content behaviour to CellState object.
 */
public abstract class CellState {

    protected Cell cell;

    /**
     * Cell passes himself to state to let state
     * change that cell, if needed.
     *
     * @param cell - context
     */
    public CellState(Cell cell) {
        this.cell = cell;
    }

    /**
     * Method to change cell's content
     * This method should return if cell was changed or not
     *
     * @param content - new content of cell
     * @return true, if cell's content was changed
     */
    public abstract boolean changeContent(int content);
}
