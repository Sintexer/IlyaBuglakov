package com.ilyabuglakov.stringmanipulator.view;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.IOController;
import com.ilyabuglakov.stringmanipulator.controller.MessageController;

public class ConsoleView {

    private MessageController messageController = new MessageController();
    private IOController ioController = new IOController();

    public void showMessage(Enum<?> id){
        ioController.show(messageController.getMessage(id));
    }

}
