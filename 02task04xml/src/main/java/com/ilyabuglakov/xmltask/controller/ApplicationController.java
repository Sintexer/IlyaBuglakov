package com.ilyabuglakov.xmltask.controller;


import com.ilyabuglakov.xmltask.model.CommandName;
import com.ilyabuglakov.xmltask.model.MessageName;
import com.ilyabuglakov.xmltask.view.ConsoleView;

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
            //TODO command execution
        } while (!choice.equals(CommandName.EXIT));
    }

    /**
     * @return ConsoleView - view of application
     */
    public ConsoleView getView() {
        return view;
    }


}
