package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.CommandName;
import com.ilyabuglakov.task0201books.bean.MessageName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.controller.CommandController;
import com.ilyabuglakov.task0201books.controller.PathController;
import com.ilyabuglakov.task0201books.dal.repository.PublicationRepository;
import com.ilyabuglakov.task0201books.exception.DaoAddException;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.service.file.FilePublicationReader;
import com.ilyabuglakov.task0201books.view.ConsoleView;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

/**
 * This command read's repositories content from input file.
 * If file is empty or all entries are wrong,repository will be empty.
 */
public class ReadPublicationFileCommand implements Command {

    private final String FILE_NAME = "input.txt";
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        String path = PathController.getInstance().getResourcePath(FILE_NAME);
        PublicationRepository repository = PublicationRepository.getInstance();
        boolean duplicatesMessageShown = false;
        try (FilePublicationReader reader = new FilePublicationReader(path)) {
            repository.clear();
            while (reader.hasNext()) {
                Optional<? extends Publication> publication = reader.next();
                if (publication.isPresent())
                    try {
                        repository.add(publication.get());
                    } catch (DaoAddException e) {
                        if (!duplicatesMessageShown) {
                            view.showMessage(MessageName.BOOK_DUPLICATES_IN_FILE);
                            duplicatesMessageShown = true;
                        }
                    }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.error("ReadBook IOException");
            view.showMessage(MessageName.CANT_OPEN_FILE);
        }
        CommandController.getInstance().executeCommand(CommandName.SHOW_REPOSITORY);
    }
}
