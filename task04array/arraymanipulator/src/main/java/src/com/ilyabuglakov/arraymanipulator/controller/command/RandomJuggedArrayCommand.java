package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.juggedarray.JuggedArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.model.MessageId;

import java.util.Random;

public class RandomJuggedArrayCommand implements Command {
    @Override
    public void execute() {
        JuggedArrayCreator<Integer> arrayCreator = new JuggedArrayCreator<>();
        ConsoleView view = ApplicationController.getInstance().getView();
        view.showMessage(MessageId.INT_RANDOM_BOUND);
        int bound = view.readInt();
        view.showMessage(MessageId.INPUT_ARRAY_SIZE);
        int size = view.readInt(1);
        Integer[][] intArray = new Integer[size][];
        for (int i = 0; i < size; ++i) {
            view.showMessage(MessageId.INPUT_ARRAY_SIZE);
            int subSize = view.readInt(1);
            Integer[] intSubArray = new Integer[subSize];
            Random rand = new Random();
            for (int j = 0; j < subSize; ++j) {
                intSubArray[j] = rand.nextInt(bound);
            }
            intArray[i] = intSubArray;
        }
        JuggedArray<Integer> array = arrayCreator.createJuggedArray(intArray);
        ApplicationController.getInstance().setArray(array);
    }
}
