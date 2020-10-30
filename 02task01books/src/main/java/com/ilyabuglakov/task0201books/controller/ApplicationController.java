package com.ilyabuglakov.task0201books.controller;

import com.ilyabuglakov.task0201books.bean.CommandName;
import com.ilyabuglakov.task0201books.bean.MessageName;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.model.book.BookBuilder;
import com.ilyabuglakov.task0201books.service.file.FileBookWriter;
import com.ilyabuglakov.task0201books.view.ConsoleView;

import java.io.IOException;
import java.time.Year;
import java.util.Arrays;
import java.util.Collection;
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

    public String getInputName() {
        view.showMessage(MessageName.ENTER_BOOK_NAME);
        return view.getString();
    }

    public int getInputNumberOfPages() {
        view.showMessage(MessageName.ENTER_NUMBER_OF_PAGES);
        return view.getInt(1);
    }

    public Set<String> getInputAuthors() {
        view.showMessage(MessageName.ENTER_AUTHORS);
        return new HashSet<>(Arrays.asList(view.getString().split(" ")));
    }

    public String getInputPublishingHouse() {
        view.showMessage(MessageName.ENTER_PUBLISHING_HOUSE);
        return view.getString();
    }

    public Year getInputYearOfPublishing() {
        view.showMessage(MessageName.ENTER_YEAR_OF_PUBLISHING);
        return Year.of(view.getInt(0, Integer.parseInt(Year.now().toString())));
    }

    public void writeToFile(Collection<Book> books, String path) {
        try (FileBookWriter writer = new FileBookWriter(path)) {
            for (Book book : books)
                writer.writeBook(book);
        } catch (IOException e) {
            view.showMessage(MessageName.FILE_INIT_ERROR);
        }
    }

}
