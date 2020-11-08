package com.ilyabuglakov.matrix.model.cell.state;

import com.ilyabuglakov.matrix.model.cell.Cell;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents Empty Cell. Locks when anything trying to change cell's content.
 * After changing content changing itself to FilledState object.
 */
public class EmptyLockState extends CellState {

    private ReentrantLock lock = new ReentrantLock();

    /**
     * Cell passes himself to state to let state
     * change that cell, if needed.
     *
     * @param cell - context
     */
    public EmptyLockState(Cell cell) {
        super(cell);
    }

    @Override
    public boolean changeContent(int content) {
        if (!lock.tryLock()) {
            return false;
        }
        cell.setContent(content);
        cell.setState(new FilledState(cell));
        lock.unlock();
        return true;
    }
}
