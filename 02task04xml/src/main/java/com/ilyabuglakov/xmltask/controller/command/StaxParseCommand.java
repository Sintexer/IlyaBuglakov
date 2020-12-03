package com.ilyabuglakov.xmltask.controller.command;

import com.ilyabuglakov.xmltask.controller.ApplicationController;
import com.ilyabuglakov.xmltask.exception.XMLFileContentException;
import com.ilyabuglakov.xmltask.model.MessageName;
import com.ilyabuglakov.xmltask.model.gem.Gem;
import com.ilyabuglakov.xmltask.service.PathService;
import com.ilyabuglakov.xmltask.service.gem.builder.STAXBuilder;
import com.ilyabuglakov.xmltask.service.xml.XMLSchemaFactory;
import com.ilyabuglakov.xmltask.storage.PathStorage;
import com.ilyabuglakov.xmltask.view.ConsoleView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class StaxParseCommand implements Command {
    private static Logger logger = LogManager.getLogger(StaxParseCommand.class);

    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        String xsdPath = PathStorage.getInstance().getXsdPath();
        String inputPath = PathStorage.getInstance().getInputPath();
        Schema schema;

        try {
            schema = XMLSchemaFactory.getSchema(xsdPath);
        } catch (SAXException e) {
            view.showMessage(MessageName.INVALID_SCHEMA);
            logger.error(() -> "Invalid schema", e);
            return;
        }

        ValidateFileCommand validateCommand = new ValidateFileCommand(schema, inputPath);
        validateCommand.execute();
        if (!validateCommand.isValid()) {
            view.showMessage(validateCommand.getMessage());
            view.show(inputPath);
            view.show(xsdPath);
            logger.info(() -> "Failed to validate\n" + inputPath + "\n" + xsdPath);
        }

        try {
            STAXBuilder builder = new STAXBuilder();
            InputStream inputStream = new FileInputStream(new File(inputPath));
            Set<Gem> gems = builder.buildGemSet(inputStream);
            view.showEnumerated(gems);
        } catch (XMLFileContentException e) {
            logger.error(() -> "Error while parsing, wrong tag", e);
            view.showMessage(MessageName.INVALID_FILE);
        } catch (IOException e) {
            logger.error(() -> "Bad input file, or it wasn't found", e);
            view.showMessage(MessageName.INVALID_FILE);
        } catch (XMLStreamException e) {
            logger.error(() -> "Bad input file content", e);
            view.showMessage(MessageName.INVALID_FILE);
        }

    }
}
