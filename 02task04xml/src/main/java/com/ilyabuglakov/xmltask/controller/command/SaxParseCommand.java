package com.ilyabuglakov.xmltask.controller.command;

import com.ilyabuglakov.xmltask.controller.ApplicationController;
import com.ilyabuglakov.xmltask.exception.ParserException;
import com.ilyabuglakov.xmltask.exception.XMLFileContentException;
import com.ilyabuglakov.xmltask.model.MessageName;
import com.ilyabuglakov.xmltask.model.gem.Gem;
import com.ilyabuglakov.xmltask.service.PathService;
import com.ilyabuglakov.xmltask.service.gem.builder.DOMBuilder;
import com.ilyabuglakov.xmltask.service.gem.builder.SAXBuilder;
import com.ilyabuglakov.xmltask.service.xml.XMLSchemaFactory;
import com.ilyabuglakov.xmltask.service.xml.parser.DomParser;
import com.ilyabuglakov.xmltask.view.ConsoleView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.util.Set;

public class SaxParseCommand implements Command{

    private static Logger logger = LogManager.getLogger(SaxParseCommand.class);

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


        try {
            SAXBuilder builder = new SAXBuilder();
            builder.setSchema(schema);
            Set<Gem> gems = builder.buildGemSet(inputPath);
            view.showEnumerated(gems);
        } catch (ParserConfigurationException | SAXException e) {
            logger.error(() -> "Bad SAX parser configuration", e);
            view.showMessage(MessageName.BAD_PARSER_CONFIGURATION);
        } catch (XMLFileContentException e) {
            logger.error(() -> "Error while parsing, wrong tag", e);
            view.showMessage(MessageName.INVALID_FILE);
        } catch (IOException e) {
            logger.error(() -> "Bad input file, or it wasn't found", e);
            view.showMessage(MessageName.INVALID_FILE);
        }

    }
}
