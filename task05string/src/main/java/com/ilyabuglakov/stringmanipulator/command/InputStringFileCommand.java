package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.exception.ReadException;
import com.ilyabuglakov.stringmanipulator.file.FileBufferedIterator;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

import java.io.IOException;

/**
 * This command allows user to read applicationString from file
 */
public class InputStringFileCommand implements Command {
    @Override
    public void execute() {

        ConsoleView view = ApplicationController.getInstance().getView();
        final int BUFFER_SIZE = 256;
        try (FileBufferedIterator input = new FileBufferedIterator(ApplicationController.INIT_PATH, BUFFER_SIZE)) {
            StringBuilder fileContent = new StringBuilder();
            while (input.hasNext())
                fileContent.append(input.next());
            ApplicationController.getInstance().setApplicationString(fileContent.toString());
        } catch (ReadException e) {
            view.showMessage(MessageId.FILE_IS_EMPTY);
        } catch (IOException e) {
            view.showMessage(MessageId.CANT_OPEN_FILE);
        }

    }
}
