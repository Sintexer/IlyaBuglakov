package src.com.ilyabuglakov.arraymanipulator.controller;

import src.com.ilyabuglakov.arraymanipulator.repository.Array;
import src.com.ilyabuglakov.arraymanipulator.repository.ArrayInterface;
import src.com.ilyabuglakov.arraymanipulator.view.console.ConsoleView;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

public class ApplicationController {
    private static ApplicationController controller;
    private ConsoleView view;
    private ArrayInterface<Integer> array = null;
    private CommandController commandController = CommandController.getInstance();

    private ApplicationController(){
        view = new ConsoleView();
    }

    public static ApplicationController getInstance(){
        if(controller == null)
            controller = new ApplicationController();
        return controller;
    }

    public void showView(){
        CommandName choice;
        do{
            view.showMenu();
            choice = view.getCommand();
            if(array == null &&  choice!=CommandName.EXIT && choice!=CommandName.FILL_ARRAY)
                view.showMessage(MessageId.NO_ARRAY);
            else
                commandController.getCommand(choice).execute();
        }while (choice!=CommandName.EXIT);
    }

    public ConsoleView getView(){
        return view;
    }

    public ArrayInterface<Integer> getArray(){
        return array;
    }

    public void setArray(ArrayInterface<Integer> array){
        this.array = array;
    }

}
