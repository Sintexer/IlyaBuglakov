package com.ilyabuglakov.task06book.controller;

import com.ilyabuglakov.task06book.bean.CommandName;
import com.ilyabuglakov.task06book.bean.MessageName;
import com.ilyabuglakov.task06book.model.book.Book;
import com.ilyabuglakov.task06book.model.book.BookBuilder;
import com.ilyabuglakov.task06book.view.ConsoleView;

import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ApplicationController {

    private static ApplicationController instance = new ApplicationController();

    private CommandController commandController = CommandController.getInstance();
    private ConsoleView view = ConsoleView.getInstance();
    private String applicationString = "";


    private ApplicationController() {
    }

    /**
     * @return instance of singleton class ApplicationController
     */
    public static ApplicationController getInstance() {
        return instance;
    }

    /**
     * Runs the main menu of application
     */
    public void start() {
        CommandName choice;
        do {
            choice = view.getCommand();
            commandController.executeCommand(choice);
        } while (!choice.equals(CommandName.EXIT));
    }

    /**
     * @return ConsoleView - view of application
     */
    public ConsoleView getView() {
        return view;
    }

    /**
     * @return content String
     */
    public String getApplicationString() {
        return applicationString;
    }

    /**
     * @param applicationString new content of the application
     */
    public void setApplicationString(String applicationString) {
        this.applicationString = applicationString;
    }

    /**
     * Creates a book via view dialog with user
     *
     * @return inputted book
     */
    public Book getInputBook() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        view.showMessage(MessageName.ENTER_BOOK);
        view.showMessage(MessageName.ENTER_BOOK_NAME);
        String bookName = view.getString();
        view.showMessage(MessageName.ENTER_NUMBER_OF_PAGES);
        int numberOfPages = view.getInt(1);
        view.showMessage(MessageName.ENTER_AUTHORS);
        Set<String> authors = new HashSet<>(Arrays.asList(view.getString().split(" ")));
        view.showMessage(MessageName.ENTER_PUBLISHING_HOUSE);
        String publishingHouse = view.getString();
        view.showMessage(MessageName.ENTER_YEAR_OF_PUBLISHING);
        int yearOfPublishing = view.getInt(0, Integer.parseInt(Year.now().toString()));

        return new BookBuilder().setName(bookName)
                .setAuthors(authors)
                .setNumberOfPages(numberOfPages)
                .setPublishingHouse(publishingHouse)
                .setYearOfPublishing(Year.of(yearOfPublishing))
                .build();
    }

}
