package com.ilyabuglakov.task06book.controller.comand;

import com.ilyabuglakov.task06book.bean.CommandName;
import com.ilyabuglakov.task06book.bean.MessageName;
import com.ilyabuglakov.task06book.controller.ApplicationController;
import com.ilyabuglakov.task06book.controller.CommandController;
import com.ilyabuglakov.task06book.controller.PathController;
import com.ilyabuglakov.task06book.dal.repository.BookRepository;
import com.ilyabuglakov.task06book.model.book.Book;
import com.ilyabuglakov.task06book.service.file.FileBookWriter;
import com.ilyabuglakov.task06book.view.ConsoleView;

import java.io.IOException;

public class FlushToFileCommand implements Command {

    private final String fileName = "bookRepositoryContent.txt";

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        String path = PathController.getInstance().getResourcePath(fileName);
        try (FileBookWriter writer = new FileBookWriter(path)) {
            for (Book book : BookRepository.getInstance().getBooks())
                writer.writeBook(book);
        } catch (IOException e) {
            view.showMessage(MessageName.FILE_INIT_ERROR);
        }
        CommandController.getInstance().executeCommand(CommandName.SHOW_BOOK_REPOSITORY);
    }
}
