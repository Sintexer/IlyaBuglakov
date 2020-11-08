package com.ilyabuglakov.matrix.controller.command;

import com.ilyabuglakov.matrix.controller.ApplicationController;
import com.ilyabuglakov.matrix.model.Matrix;
import com.ilyabuglakov.matrix.view.ConsoleView;

public class ShowMatrixCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.show(Matrix.getInstance());
    }
}
