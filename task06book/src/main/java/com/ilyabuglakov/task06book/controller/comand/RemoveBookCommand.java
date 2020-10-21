package com.ilyabuglakov.task06book.controller.comand;

import com.ilyabuglakov.task06book.bean.MessageName;
import com.ilyabuglakov.task06book.controller.ApplicationController;
import com.ilyabuglakov.task06book.dal.repository.BookRepository;
import com.ilyabuglakov.task06book.exception.DaoRemoveException;
import com.ilyabuglakov.task06book.view.ConsoleView;
import org.apache.log4j.Logger;


public class RemoveBookCommand implements Command {
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        try {
            BookRepository.getInstance().remove(controller.getInputBook());
        } catch (DaoRemoveException e) {
            logger.error(e.getMessage());
            view.showMessage(MessageName.BOOK_IS_MISSING);
        }
    }
}
