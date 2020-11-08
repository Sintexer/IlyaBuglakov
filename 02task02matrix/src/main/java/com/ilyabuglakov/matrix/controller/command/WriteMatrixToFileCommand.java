package com.ilyabuglakov.matrix.controller.command;

import com.ilyabuglakov.matrix.service.PathService;
import com.ilyabuglakov.matrix.service.file.FileMatrixWriter;

public class WriteMatrixToFileCommand implements Command {

    private static final String FILE_NAME = "matrixOut.txt";

    @Override
    public void execute() {
        FileMatrixWriter writer = new FileMatrixWriter();
        writer.writeMatrix(PathService.getInstance().getResourcePath(FILE_NAME));
    }
}
