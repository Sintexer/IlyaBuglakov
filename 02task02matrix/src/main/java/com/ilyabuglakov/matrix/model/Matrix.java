package com.ilyabuglakov.matrix.model;

import com.ilyabuglakov.matrix.model.cell.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matrix {

    private List<Row> rows = Collections.emptyList();

    private Matrix() {
    }

    private static class SingletonHolder {
        public static final Matrix HOLDER_INSTANCE = new Matrix();
    }

    public static Matrix getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public Cell getCell(int row, int column) {
        return rows.get(row).get(column);
    }

    public int size() {
        return rows.size();
    }

    public void setRow(int index, Row row) {
        rows.set(index, row);
    }

    public Row getRow(int index) {
        return rows.get(index);
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = new ArrayList<>(rows);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Row row : rows) {
            stringBuilder.append(row).append("\n");
        }
        return stringBuilder.toString();
    }
}
