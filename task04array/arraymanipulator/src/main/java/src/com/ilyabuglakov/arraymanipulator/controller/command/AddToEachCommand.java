package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.JuggedArrayService;
import src.com.ilyabuglakov.arraymanipulator.view.message.MessageId;

public class AddToEachCommand implements Command {
    @Override
    public void execute() {
        JuggedArrayService<Integer> service = new JuggedArrayService<>();
        ApplicationController controller = ApplicationController.getInstance();
        controller.getView().showMessage(MessageId.INPUT_INT);
        int modifier = controller.getView().readInt();
        controller.setArray(service.modifyValues((JuggedArray<Integer>) controller.getArray(), modifier, Integer::sum));
    }
}
