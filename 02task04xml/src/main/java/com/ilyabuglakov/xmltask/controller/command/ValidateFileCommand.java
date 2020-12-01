package com.ilyabuglakov.xmltask.controller.command;

import com.ilyabuglakov.xmltask.model.MessageName;
import com.ilyabuglakov.xmltask.service.xml.validator.XSDValidator;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ValidateFileCommand implements Command {

    private static Logger logger = LogManager.getLogger(ValidateFileCommand.class);

    @Getter
    private boolean isValid = false;
    @Getter
    private MessageName message;
    @Setter
    private Schema schema;
    @Setter
    private String inputPath;

    public ValidateFileCommand(Schema schema, String inputPath) {
        this.schema = schema;
        this.inputPath = inputPath;
    }

    @Override
    public void execute() {
        isValid = false;
        XSDValidator validator = new XSDValidator();
        validator.setSchema(schema);
        try{
            StreamSource source = new StreamSource(new FileInputStream(new File(inputPath)));
            if(!validator.isValidXml(source)){
                message = MessageName.INVALID_FILE;
                logger.error( () -> "Invalid xml file " + inputPath);
                return;
            }
        } catch (FileNotFoundException e) {
            message = MessageName.FILE_NOT_FOUND;
            logger.error( () -> "File not found", e);
            return;
        }

        isValid = true;
    }
}
