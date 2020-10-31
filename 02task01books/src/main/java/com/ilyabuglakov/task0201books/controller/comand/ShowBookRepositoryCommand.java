package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.dal.repository.BookRepository;
import com.ilyabuglakov.task0201books.dal.repository.PublicationRepository;
import com.ilyabuglakov.task0201books.view.ConsoleView;
import org.apache.log4j.Logger;

public class ShowBookRepositoryCommand implements Command {
    @Override
    public void execute() {
        Logger.getLogger(this.getClass()).info(PublicationRepository.getInstance().getAll());
    }
}
