package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayIntFileReader;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

import java.io.IOException;

public class ReadArrayFileCommand implements Command {
    @Override
    public void execute() {
        ArrayIntFileReader fileReader = new ArrayIntFileReader();
        ApplicationController controller = ApplicationController.getInstance();
        try {
            controller.setArray(fileReader.readArray(controller.ARRAY_PATH));
        } catch (IOException e) {
            controller.getView().showMessage(MessageId.WRONG_PATH);
        }
    }
}
