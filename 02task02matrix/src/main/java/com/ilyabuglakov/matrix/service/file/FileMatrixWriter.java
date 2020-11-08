package com.ilyabuglakov.matrix.service.file;

import com.ilyabuglakov.matrix.model.Matrix;
import com.ilyabuglakov.matrix.model.Row;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes Matrix to output file
 */
public class FileMatrixWriter {

    Logger logger = Logger.getLogger(this.getClass());

    public void writeMatrix(String path) {
        Matrix matrix = Matrix.getInstance();
        StringBuilder stringMatrix = new StringBuilder();
        for (Row row : matrix.getRows()) {
            for (int i = 0; i < row.size(); ++i) {
                stringMatrix.append(row.get(i).getContent()).append(" ");
            }
            stringMatrix.append("\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(stringMatrix.toString());
        } catch (IOException e) {
            logger.error("Error while writing matrix to file", e);
        }
    }

}
