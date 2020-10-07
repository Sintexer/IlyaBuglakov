package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.service.JuggedArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

public class InputJuggedArrayCommand implements Command {
    @Override
    public void execute() {
        JuggedArrayCreator<Integer> arrayCreator = new JuggedArrayCreator<>();
        ConsoleView view = ApplicationController.getInstance().getView();
        view.showMessage(MessageId.INPUT_ARRAY_SIZE);
        int size = view.readInt(1);
        Integer[][] intArray = new Integer[size][];
        view.showMessage(MessageId.INPUT_ARRAY);
        for(int i = 0; i<size;++i) {
            view.showMessage(MessageId.INPUT_ARRAY_SIZE);
            int subSize = view.readInt(1);
            Integer[] subArray = new Integer[subSize];
            for (int j = 0; j < subSize; ++j) {
                subArray[i] = view.readInt();
            }
            intArray[i] = subArray;
        }
        JuggedArray<Integer> array = arrayCreator.createJuggedArray(intArray);
        ApplicationController.getInstance().setArray(array);
    }
}
