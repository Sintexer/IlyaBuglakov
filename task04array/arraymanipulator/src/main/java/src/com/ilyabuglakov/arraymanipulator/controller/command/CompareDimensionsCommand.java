package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.juggedarray.JuggedArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.service.juggedarray.JuggedArrayService;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;

public class CompareDimensionsCommand implements Command {
    @Override
    public void execute() {
        JuggedArrayCreator<Integer> arrayCreator = new JuggedArrayCreator<>();
        JuggedArrayService<Integer> service = new JuggedArrayService<>();
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        JuggedArray<Integer> array = arrayCreator.createJuggedArray(view.readIntMatrix());
        controller.getView().show(service.sameSize(array, (JuggedArray<Integer>) controller.getArray()));
    }
}