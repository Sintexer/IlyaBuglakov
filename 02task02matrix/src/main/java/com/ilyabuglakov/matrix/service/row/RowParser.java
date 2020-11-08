package com.ilyabuglakov.matrix.service.row;

import com.ilyabuglakov.matrix.exception.RowParseException;
import com.ilyabuglakov.matrix.model.Row;
import com.ilyabuglakov.matrix.model.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class RowParser {
    /**
     * @param rowString pattern "1 3 6 2" - integers, divided by space
     * @return Row of provided integers
     */
    public Row parseRow(String rowString) throws RowParseException {
        Row row = new Row();
        List<Cell> cells = new ArrayList<>();
        try {
            for (String num : rowString.split(" ")) {
                cells.add(Cell.of(Integer.parseInt(num)));
            }
        } catch (NumberFormatException e) {
            throw new RowParseException("Error while parsing integer");
        }
        if (cells.isEmpty())
            throw new RowParseException("There are no integers in provided String");
        row.setCells(cells);
        return row;
    }
}
