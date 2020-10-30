package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.MessageName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.dal.repository.BookRepository;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.view.ConsoleView;
import org.apache.log4j.Logger;


public class AddBookCommand implements Command {
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        try {
            BookRepository.getInstance().add(controller.getInputBook());
        } catch (DaoAddException e) {
            logger.error(e.getMessage());
            logger.error("Exception when trying to add book");
            view.showMessage(MessageName.BOOK_ALREADY_EXIST);
        }
    }
}
