package src.com.ilyabuglakov.arraymanipulator.controller.command;

import src.com.ilyabuglakov.arraymanipulator.controller.ApplicationController;
import src.com.ilyabuglakov.arraymanipulator.controller.Command;
import src.com.ilyabuglakov.arraymanipulator.repository.JuggedArray;
import src.com.ilyabuglakov.arraymanipulator.service.juggedarray.MatrixTransposer;
import src.com.ilyabuglakov.arraymanipulator.service.validator.JuggedArrayValidator;
import src.com.ilyabuglakov.arraymanipulator.model.MessageId;

public class TransposeCommand implements Command {
    @Override
    public void execute() {
        MatrixTransposer<Integer> transposer = new MatrixTransposer<>();
        JuggedArrayValidator<Integer> validator = new JuggedArrayValidator<>();
        ApplicationController controller = ApplicationController.getInstance();
        if(!validator.isRectangular((JuggedArray<Integer>) controller.getArray())) {
            controller.getView().showMessage(MessageId.CANT_TRANSPOSE);
            return;
        }
        controller.setArray(transposer.transpose((JuggedArray<Integer>)controller.getArray()));
    }
}
