package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.dal.repository.BookRepository;
import com.ilyabuglakov.task0201books.view.ConsoleView;

public class ShowBookRepositoryCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.show(BookRepository.getInstance().getBooks());
    }
}
