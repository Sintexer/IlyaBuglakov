package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.service.ArrayCreator;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

import java.util.Random;

public class RandomArrayCommand implements Command {
    @Override
    public void execute() {
        ArrayCreator<Integer> arrayCreator = new ArrayCreator<>();
        ConsoleView view = ApplicationController.getInstance().getView();
        view.showMessage(MessageId.INPUT_ARRAY_SIZE);
        int size = view.readInt(1);
        view.showMessage(MessageId.INT_RANDOM_BOUND);
        int bound = view.readInt();
        Integer[] intArray = new Integer[size];
        Random rand = new Random();
        for(int i = 0; i<size;++i){
            intArray[i] = rand.nextInt(bound);
        }
        Array<Integer> array = arrayCreator.createArray(intArray);
        ApplicationController.getInstance().setArray(array);
    }
}
