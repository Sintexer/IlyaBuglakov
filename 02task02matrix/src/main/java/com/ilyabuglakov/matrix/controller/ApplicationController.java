package com.ilyabuglakov.matrix.controller;

import com.ilyabuglakov.matrix.bean.CommandName;
import com.ilyabuglakov.matrix.bean.MessageName;
import com.ilyabuglakov.matrix.model.Matrix;
import com.ilyabuglakov.matrix.view.ConsoleView;

public class ApplicationController {
    private CommandController commandController = CommandController.getInstance();
    private ConsoleView view = ConsoleView.getInstance();

    private ApplicationController() {
    }

    private static class SingletonHolder {
        public static final ApplicationController HOLDER_INSTANCE = new ApplicationController();
    }

    /**
     * @return instance of singleton class ApplicationController
     */
    public static ApplicationController getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    /**
     * Runs the main menu of application
     */
    public void start() {
        CommandName choice;
        do {
            choice = view.getCommand();
            if (!commandController.getCommand(choice).applicableToEmptyMatrix() &&
                    Matrix.getInstance().size() == 0) {
                view.showMessage(MessageName.INIT_MATRIX_FIRST);
            } else {
                commandController.executeCommand(choice);
            }
        } while (!choice.equals(CommandName.EXIT));
    }

    /**
     * @return ConsoleView - view of application
     */
    public ConsoleView getView() {
        return view;
    }


}
