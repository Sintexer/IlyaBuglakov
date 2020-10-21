package com.ilyabuglakov.task06book.controller.comand;

import com.ilyabuglakov.task06book.controller.ApplicationController;
import com.ilyabuglakov.task06book.dal.repository.BookRepository;
import com.ilyabuglakov.task06book.view.ConsoleView;

public class ShowBookRepositoryCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.show(BookRepository.getInstance().getBooks());
    }
}
