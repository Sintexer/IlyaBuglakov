package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayType;

public class SwitchToJuggedArrayCommand implements Command {
    @Override
    public void execute() {
        ApplicationController.getInstance().getView().setCurrentType(ArrayType.JUGGED_ARRAY);
    }
}
