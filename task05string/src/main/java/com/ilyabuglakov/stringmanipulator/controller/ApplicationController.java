package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

/**
 * ApllicationController
 * Main controller class. Contains view and String content.
 * Implemented as Singleton. Method start() runs the work of application menu
 */
public class ApplicationController {

    private static ApplicationController instance = new ApplicationController();

    private CommandController commandController = CommandController.getInstance();
    private ConsoleView view = ConsoleView.getInstance();
    private String applicationString = "";


    private ApplicationController() {
    }

    /**
     * @return instance of singleton class ApplicationController
     */
    public static ApplicationController getInstance() {
        return instance;
    }

    /**
     * Runs the main menu of application
     */
    public void start() {
        CommandName choice;
        do {
            choice = view.getCommand();
            commandController.executeCommand(choice);
        } while (!choice.equals(CommandName.EXIT));
    }

    /**
     * @return ConsoleView - view of application
     */
    public ConsoleView getView() {
        return view;
    }

    /**
     * @return content String
     */
    public String getApplicationString() {
        return applicationString;
    }

    /**
     * @param applicationString new content of the application
     */
    public void setApplicationString(String applicationString) {
        this.applicationString = applicationString;
    }
}
