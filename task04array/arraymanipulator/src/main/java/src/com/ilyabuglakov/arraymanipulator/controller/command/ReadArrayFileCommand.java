package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.service.PathService;
import src.com.ilyabuglakov.arraymanipulator.service.array.ArrayIntFileReader;
import src.com.ilyabuglakov.arraymanipulator.model.MessageId;

import java.io.IOException;

public class ReadArrayFileCommand implements Command {
    @Override
    public void execute() {
        ArrayIntFileReader fileReader = new ArrayIntFileReader();
        ApplicationController controller = ApplicationController.getInstance();
        String path = PathService.getInstance().getResourcePath(controller.ARRAY_PATH);
        try {
            controller.setArray(fileReader.readArray(path.substring(1)));
        } catch (IOException e) {
            controller.getView().showMessage(MessageId.WRONG_PATH);
        }
    }
}
