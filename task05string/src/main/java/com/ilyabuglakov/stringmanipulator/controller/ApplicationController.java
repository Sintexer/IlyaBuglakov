package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

public class ApplicationController {

    private ConsoleView view = new ConsoleView();

    public void start(){
        view.showMessage(MessageId.HELLO);
        view.showMessage(CommandName.EXIT);
    }

}
