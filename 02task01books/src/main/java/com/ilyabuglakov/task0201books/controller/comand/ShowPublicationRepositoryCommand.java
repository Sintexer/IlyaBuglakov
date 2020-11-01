package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.dal.repository.BookRepository;
import com.ilyabuglakov.task0201books.dal.repository.PublicationRepository;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.view.ConsoleView;
import org.apache.log4j.Logger;

import java.util.function.Function;
import java.util.stream.Collectors;

public class ShowPublicationRepositoryCommand implements Command {
    @Override
    public void execute() {
        Logger.getLogger(this.getClass()).info(PublicationRepository.getInstance().getAll().stream()
                .collect(Collectors.toMap(Publication::getId, Function.identity())));
    }
}
