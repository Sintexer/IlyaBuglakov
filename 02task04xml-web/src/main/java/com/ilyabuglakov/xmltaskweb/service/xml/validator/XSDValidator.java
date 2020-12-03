package com.ilyabuglakov.xmltaskweb.service.xml.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.IOException;

public class XSDValidator {

    private static Logger logger = LogManager.getLogger(XSDValidator.class);

    private Validator validator;

    public void setSchema(Schema schema) {
        validator = schema.newValidator();
    }

    public boolean isValidXml(StreamSource streamSource) {
        try {
            validator.validate(streamSource);
            return true;
        } catch (SAXException e) {
            logger.error(() -> "Validation SAX error", e);
        } catch (IOException e) {
            logger.error(() -> "Validation IO error", e);
        }
        return false;
    }

}
