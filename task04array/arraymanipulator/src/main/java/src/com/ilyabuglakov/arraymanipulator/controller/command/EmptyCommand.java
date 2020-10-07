package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.Command;

public class EmptyCommand implements Command {

    @Override
    public void execute() {

    }

    @Override
    public boolean canBeAppliedWhenArrayIsNull() {
        return true;
    }
}
