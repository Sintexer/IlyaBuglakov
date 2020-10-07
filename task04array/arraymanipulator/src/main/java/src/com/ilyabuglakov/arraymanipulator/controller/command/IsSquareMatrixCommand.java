package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.JuggedArrayService;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;

public class IsSquareMatrixCommand implements Command {
    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        JuggedArray<Integer> array = (JuggedArray<Integer>) ApplicationController.getInstance().getArray();
        JuggedArrayService<Integer> service = new JuggedArrayService<>();
        view.show(service.isSquareMatrix(array));
    }
}
