package com.ilyabuglakov.matrix.model.cell;

import com.ilyabuglakov.matrix.model.cell.state.CellState;
import com.ilyabuglakov.matrix.model.cell.state.FilledState;

import java.util.Objects;

/**
 * Cell class represents Matrix cell with Integer content
 */
public class Cell {
    private int content;
    private CellState state;

    private Cell(int content, CellState state) {
        this.content = content;
        this.setState(state);
    }

    private Cell(int content) {
        this.content = content;
        this.setState(new FilledState(this));
    }

    /**
     * Creates new Matrix cell with provided state and content
     *
     * @param content - cell content value
     * @param state   - cell beginning state
     * @return -new Cell, created from provided parameters
     */
    public static Cell of(int content, CellState state) {
        return new Cell(content, state);
    }

    /**
     * Creates new Matrix cell with provided content
     *
     * @param content - cell content value
     * @return - new Cell, created from provided parameters
     */
    public static Cell of(int content) {
        return new Cell(content);
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return content == cell.getContent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return String.valueOf(content);
    }
}
