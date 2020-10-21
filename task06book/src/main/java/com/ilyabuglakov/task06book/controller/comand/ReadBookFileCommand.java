package com.ilyabuglakov.task06book.controller.comand;

import com.ilyabuglakov.task06book.bean.CommandName;
import com.ilyabuglakov.task06book.bean.MessageName;
import com.ilyabuglakov.task06book.controller.ApplicationController;
import com.ilyabuglakov.task06book.controller.CommandController;
import com.ilyabuglakov.task06book.controller.PathController;
import com.ilyabuglakov.task06book.dal.repository.BookRepository;
import com.ilyabuglakov.task06book.exception.BookParseException;
import com.ilyabuglakov.task06book.exception.DaoAddException;
import com.ilyabuglakov.task06book.model.book.Book;
import com.ilyabuglakov.task06book.service.file.FileBookReader;
import com.ilyabuglakov.task06book.view.ConsoleView;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class ReadBookFileCommand implements Command {

    private final String FILE_NAME = "input.txt";
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        String path = PathController.getInstance().getResourcePath(FILE_NAME);
        BookRepository repository = BookRepository.getInstance();
        List<Book> backup = repository.getBooks();
        try (FileBookReader reader = new FileBookReader(path)) {
            repository.clear();
            while (reader.hasNext())
                repository.add(reader.readBook());
        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.error("ReadBook IOException");
            view.showMessage(MessageName.CANT_OPEN_FILE);
        } catch (BookParseException e) {
            logger.error(e.getMessage());
            view.showMessage(MessageName.WRONG_INPUT_FILE_FORMAT);
            view.showMessage(MessageName.INPUT_FILE_FORMAT);
            repository.setBooks(backup);
        } catch (DaoAddException e) {
            logger.error(e.getMessage());
            view.showMessage(MessageName.WRONG_INPUT_FILE_FORMAT);
            view.showMessage(MessageName.BOOK_DUPLICATES_IN_FILE);
            repository.setBooks(backup);
        }
        CommandController.getInstance().executeCommand(CommandName.SHOW_BOOK_REPOSITORY);
    }
}
