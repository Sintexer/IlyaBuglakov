package com.ilyabuglakov.matrix.model.cell.state;

import com.ilyabuglakov.matrix.model.cell.Cell;

/**
 * Represents Empty Cell.
 * After changing content changing itself to FilledState object.
 */
public class EmptyState extends CellState {

    /**
     * Cell passes himself to state to let state
     * change that cell, if needed.
     *
     * @param cell - context
     */
    public EmptyState(Cell cell) {
        super(cell);
    }

    @Override
    public boolean changeContent(int content) {
        cell.setContent(content);
        cell.setState(new FilledState(cell));
        return true;
    }
}
