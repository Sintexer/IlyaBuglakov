package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.CommandName;
import com.ilyabuglakov.task0201books.bean.MessageName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.controller.CommandController;
import com.ilyabuglakov.task0201books.controller.PathController;
import com.ilyabuglakov.task0201books.dal.repository.PublicationRepository;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.service.file.FilePublicationWriter;
import com.ilyabuglakov.task0201books.view.ConsoleView;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * This command writes repositories content to output file
 */
public class FlushToFileCommand implements Command {

    private final String fileName = "publicationRepositoryContent.txt";
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        String path = PathController.getInstance().getResourcePath(fileName);
        try (FilePublicationWriter writer = new FilePublicationWriter(path)) {
            for (Publication publication : PublicationRepository.getInstance().getAll())
                writer.write(publication);
        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.error("FlushToFile Exception");
            view.showMessage(MessageName.FILE_INIT_ERROR);
        }
        CommandController.getInstance().executeCommand(CommandName.SHOW_REPOSITORY);
    }
}
