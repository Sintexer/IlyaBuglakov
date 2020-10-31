package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.CommandName;
import com.ilyabuglakov.task0201books.bean.MessageName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.controller.CommandController;
import com.ilyabuglakov.task0201books.controller.PathController;
import com.ilyabuglakov.task0201books.dal.repository.BookRepository;
import com.ilyabuglakov.task0201books.exception.ParseException;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.service.file.FilePublicationReader;
import com.ilyabuglakov.task0201books.view.ConsoleView;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class ReadBookFileCommand implements Command {

    private final String FILE_NAME = "input.txt";
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute() {
        //TODO
//        ApplicationController controller = ApplicationController.getInstance();
//        ConsoleView view = controller.getView();
//        String path = PathController.getInstance().getResourcePath(FILE_NAME);
//        BookRepository repository = BookRepository.getInstance();
//        List<Book> backup = repository.getBooks();
//        try (FilePublicationReader reader = new FilePublicationReader(path)) {
//            repository.clear();
////            while (reader.hasNext())
////                repository.add(reader.readBook());
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//            logger.error("ReadBook IOException");
//            view.showMessage(MessageName.CANT_OPEN_FILE);
//        } catch (ParseException e) {
//            logger.error(e.getMessage());
//            view.showMessage(MessageName.WRONG_INPUT_FILE_FORMAT);
//            view.showMessage(MessageName.INPUT_FILE_FORMAT);
//            repository.setBooks(backup);
//        } catch (DaoAddException e) {
//            logger.error(e.getMessage());
//            view.showMessage(MessageName.WRONG_INPUT_FILE_FORMAT);
//            view.showMessage(MessageName.BOOK_DUPLICATES_IN_FILE);
//            repository.setBooks(backup);
//        }
//        CommandController.getInstance().executeCommand(CommandName.SHOW_BOOK_REPOSITORY);
    }
}
