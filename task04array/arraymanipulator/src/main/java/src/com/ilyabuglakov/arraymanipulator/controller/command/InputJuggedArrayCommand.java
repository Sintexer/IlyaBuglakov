package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.juggedarray.JuggedArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;

public class InputJuggedArrayCommand implements Command {
    @Override
    public void execute() {
        JuggedArrayCreator<Integer> arrayCreator = new JuggedArrayCreator<>();
        ConsoleView view = ApplicationController.getInstance().getView();

        JuggedArray<Integer> array = arrayCreator.createJuggedArray(view.readIntMatrix());
        ApplicationController.getInstance().setArray(array);
    }
}
