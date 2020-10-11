package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

public class ApplicationController {

    private static ApplicationController instance = new ApplicationController();
    private CommandController commandController = CommandController.getInstance();
    private ConsoleView view = ConsoleView.getInstance();
    private String content = "";

    private ApplicationController(){}

    public static ApplicationController getInstance(){
        return instance;
    }

    public void start(){
        CommandName choice;
        do{
            choice = view.getCommand();
            commandController.executeCommand(choice);
        }while (!choice.equals(CommandName.EXIT));
    }

    public ConsoleView getView() {
        return view;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
