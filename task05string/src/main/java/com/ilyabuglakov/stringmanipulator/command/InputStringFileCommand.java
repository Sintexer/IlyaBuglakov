package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.exception.ReadException;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;
import com.ilyabuglakov.stringmanipulator.view.reader.FileReader;

import java.io.IOException;

/**
 * This command allows user to read applicationString from file
 */
public class InputStringFileCommand implements Command {
    @Override
    public void execute() {

        ConsoleView view = ApplicationController.getInstance().getView();
        try {
            FileReader reader = new FileReader();
            String info = reader.readString(ApplicationController.INIT_PATH);
            ApplicationController.getInstance().setApplicationString(info);
        } catch (ReadException e) {
            view.showMessage(MessageId.FILE_IS_EMPTY);
        } catch (IOException e) {
            view.showMessage(MessageId.CANT_OPEN_FILE);
        }

    }
}
