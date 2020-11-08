package com.ilyabuglakov.matrix.service.file;

import com.ilyabuglakov.matrix.exception.ReadMatrixException;
import com.ilyabuglakov.matrix.exception.RowParseException;
import com.ilyabuglakov.matrix.model.Row;
import com.ilyabuglakov.matrix.model.cell.Cell;
import com.ilyabuglakov.matrix.model.cell.state.EmptyState;
import com.ilyabuglakov.matrix.service.row.RowParser;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads Matrix with empty diagonal from input file.
 */
public class FileMatrixReader {

    Logger logger = Logger.getLogger(this.getClass());

    public List<Row> readMatrix(String path) throws ReadMatrixException {
        List<Row> rows = new ArrayList<>();

        try (Scanner lines = new Scanner(new BufferedReader(new FileReader(path)))) {
            RowParser parser = new RowParser();
            while (lines.hasNextLine()) {
                Row row = parser.parseRow(lines.nextLine());
                rows.add(row);
            }
            long distinctRowSizes = rows.stream()
                    .map(Row::size)
                    .distinct()
                    .count();
            if (distinctRowSizes != 1)
                throw new ReadMatrixException("Rows in the file are not the same size");
        } catch (IOException | RowParseException e) {
            logger.error(e.getMessage(), e.getCause());
            throw new ReadMatrixException("Wrong file format or file is missing");
        }

        for (int i = 0; i < rows.size(); ++i) {
            Cell current = rows.get(i).get(i);
            current.setState(new EmptyState(current));
        }

        return rows;
    }
}
