package com.ilyabuglakov.matrix.controller.command;

import com.ilyabuglakov.matrix.bean.MessageName;
import com.ilyabuglakov.matrix.controller.ApplicationController;
import com.ilyabuglakov.matrix.exception.ReadMatrixException;
import com.ilyabuglakov.matrix.model.Matrix;
import com.ilyabuglakov.matrix.model.Row;
import com.ilyabuglakov.matrix.service.PathService;
import com.ilyabuglakov.matrix.service.file.FileMatrixReader;
import com.ilyabuglakov.matrix.view.ConsoleView;
import org.apache.log4j.Logger;

import java.util.List;

public class ReadMatrixCommand implements Command {

    private final String FILE_NAME = "init.txt";
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        String path = PathService.getInstance().getResourcePath(FILE_NAME);
        List<Row> rows = null;
        try {
            rows = new FileMatrixReader().readMatrix(path);
        } catch (ReadMatrixException e) {
            logger.error(e.getMessage(), e.getCause());
            view.showMessage(MessageName.FILE_READ_ERROR);
        }
        Matrix.getInstance().setRows(rows);
    }

    @Override
    public boolean applicableToEmptyMatrix() {
        return true;
    }
}
