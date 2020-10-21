package com.ilyabuglakov.task06book.controller.comand;

import com.ilyabuglakov.task06book.bean.MessageName;
import com.ilyabuglakov.task06book.controller.ApplicationController;
import com.ilyabuglakov.task06book.dal.repository.BookRepository;
import com.ilyabuglakov.task06book.model.book.BookBuilder;
import com.ilyabuglakov.task06book.view.ConsoleView;

import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AddBookCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.showMessage(MessageName.ENTER_BOOK);
        view.showMessage(MessageName.ENTER_BOOK_NAME);
        String bookName = view.getString();
        view.showMessage(MessageName.ENTER_NUMBER_OF_PAGES);
        int numberOfPages = view.getInt(1);
        view.showMessage(MessageName.ENTER_AUTHORS);
        Set<String> authors = new HashSet<>(Arrays.asList(view.getString().split(",")));
        view.showMessage(MessageName.ENTER_PUBLISHING_HOUSE);
        String publishingHouse = view.getString();
        view.showMessage(MessageName.ENTER_YEAR_OF_PUBLISHING);
        int yearOfPublishing = view.getInt(0, Integer.parseInt(Year.now().toString()));

        BookRepository.getInstance().add(new BookBuilder().setName(bookName)
                .setAuthors(authors)
                .setNumberOfPages(numberOfPages)
                .setPublishingHouse(publishingHouse)
                .setYearOfPublishing(Year.of(yearOfPublishing))
                .build());

    }
}
