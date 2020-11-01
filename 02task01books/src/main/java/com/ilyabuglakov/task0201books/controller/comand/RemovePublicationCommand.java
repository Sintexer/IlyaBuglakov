package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.MessageName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.dal.repository.PublicationRepository;
import com.ilyabuglakov.task0201books.exception.DaoRemoveException;
import com.ilyabuglakov.task0201books.view.ConsoleView;
import org.apache.log4j.Logger;

/**
 * This command removes publication from repository by specified id.
 */
public class RemovePublicationCommand implements Command {
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.showMessage(MessageName.ENTER_PUBLICATION_ID);
        int choice = view.getInt(0);
        try {
            if (!PublicationRepository.getInstance().remove(choice))
                view.showMessage(MessageName.THERE_ARE_NO_SUCH_ID);
        } catch (DaoRemoveException e) {
            logger.error(e.getMessage());
            view.showMessage(MessageName.BOOK_IS_MISSING);
        }
    }
}
