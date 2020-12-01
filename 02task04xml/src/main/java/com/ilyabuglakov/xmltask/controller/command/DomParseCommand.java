package com.ilyabuglakov.xmltask.controller.command;

import com.ilyabuglakov.xmltask.controller.ApplicationController;
import com.ilyabuglakov.xmltask.exception.ParserException;
import com.ilyabuglakov.xmltask.model.MessageName;
import com.ilyabuglakov.xmltask.model.gem.Gem;
import com.ilyabuglakov.xmltask.service.PathService;
import com.ilyabuglakov.xmltask.service.gem.builder.DomBuilder;
import com.ilyabuglakov.xmltask.service.xml.XMLSchemaFactory;
import com.ilyabuglakov.xmltask.service.xml.parser.DomParser;
import com.ilyabuglakov.xmltask.view.ConsoleView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.util.Set;

public class DomParseCommand implements Command {

    private static Logger logger = LogManager.getLogger(DomParseCommand.class);

    //TODO вынести путь в другое мессто
    private String inputFileName = "gems.xml";
    private String xsdFileName = "gems.xsd";

    @Override
    public void execute() {
        ConsoleView view = ApplicationController.getInstance().getView();
        String xsdPath = PathService.getInstance().getResourcePath(xsdFileName);
        String inputPath = PathService.getInstance().getResourcePath(inputFileName);
        Schema schema;

        try {
            schema = XMLSchemaFactory.getSchema(xsdPath);
        } catch (SAXException e) {
            view.showMessage(MessageName.INVALID_SCHEMA);
            logger.error( () -> "Invalid schema", e);
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

        DomBuilder builder;
        try {
            DomParser parser = new DomParser();
            parser.setSchema(schema);
            builder = new DomBuilder(parser.getDocument(inputPath));
        } catch (ParserConfigurationException e) {
            logger.error(() -> "Bad DOM parser configuration", e);
            view.showMessage(MessageName.BAD_PARSER_CONFIGURATION);
            return;
        } catch (ParserException e) {
            logger.error(() -> "Error while parsing", e);
            view.showMessage(MessageName.INVALID_FILE);
            return;
        }
        Set<Gem> gems = builder.buildGemSet();
        view.showEnumerated(gems);
    }
}
