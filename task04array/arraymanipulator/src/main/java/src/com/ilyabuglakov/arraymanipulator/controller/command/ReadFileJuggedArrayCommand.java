package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.service.PathService;
import src.com.ilyabuglakov.arraymanipulator.service.juggedarray.JuggedArrayIntReader;
import src.com.ilyabuglakov.arraymanipulator.model.MessageId;

import java.io.IOException;

public class ReadFileJuggedArrayCommand implements Command {
    @Override
    public void execute() {
        JuggedArrayIntReader fileReader = new JuggedArrayIntReader();
        ApplicationController controller = ApplicationController.getInstance();
        String path = PathService.getInstance().getResourcePath(controller.JUGGED_ARRAY_PATH);
        try {
            controller.setArray(fileReader.readJuggedArray(path.substring(1)));
        } catch (IOException e) {
            controller.getView().showMessage(MessageId.WRONG_PATH);
        }
    }
}
