package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.controller.CommandName;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

public class InputArrayCommand implements Command {
    @Override
    public void execute() {
        ArrayCreator<Integer> arrayCreator = new ArrayCreator<>();
        ConsoleView view = ApplicationController.getInstance().getView();
        view.showMessage(MessageId.INPUT_ARRAY_SIZE);
        int size = view.readInt(1);
        Integer[] intArray = new Integer[size];
        view.showMessage(MessageId.INPUT_ARRAY);
        for(int i = 0; i<size;++i){
            intArray[i] = view.readInt();
        }
        Array<Integer> array = arrayCreator.createArray(intArray);
        ApplicationController.getInstance().setArray(array);
    }
}
