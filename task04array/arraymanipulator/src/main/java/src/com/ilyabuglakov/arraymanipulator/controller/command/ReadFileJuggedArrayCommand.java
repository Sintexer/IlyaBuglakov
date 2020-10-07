package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayIntFileReader;
import src.com.ilyabuglakov.arraymanipulator.service.JuggedArrayIntReader;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

import java.io.IOException;

public class ReadFileJuggedArrayCommand implements Command {
    @Override
    public void execute() {
        JuggedArrayIntReader fileReader = new JuggedArrayIntReader();
        ApplicationController controller = ApplicationController.getInstance();
        try {
            controller.setArray(fileReader.readJuggedArray(controller.JUGGED_ARRAY_PATH));
        } catch (IOException e) {
            controller.getView().showMessage(MessageId.WRONG_PATH);
        }
    }
}
