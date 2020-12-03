package com.ilyabuglakov.xmltaskweb.controller.command;

import com.ilyabuglakov.xmltaskweb.exception.InvalidInpitXMLException;
import com.ilyabuglakov.xmltaskweb.exception.ParserException;
import com.ilyabuglakov.xmltaskweb.exception.SchemaException;
import com.ilyabuglakov.xmltaskweb.model.gem.Gem;
import com.ilyabuglakov.xmltaskweb.service.gem.builder.DOMBuilder;
import com.ilyabuglakov.xmltaskweb.service.xml.XMLSchemaFactory;
import com.ilyabuglakov.xmltaskweb.service.xml.parser.DomParser;
import com.ilyabuglakov.xmltaskweb.service.xml.validator.XSDValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

public class DOMParseCommand implements Command {

    private static Logger logger = LogManager.getLogger(DOMParseCommand.class);

    @Override
    public Set<Gem> execute(String xml) throws ParserException, InvalidInpitXMLException {
        String xsdPath = Thread.currentThread().getContextClassLoader().getResource("data").getPath() + "gems.xsd";
        Schema schema;

        try {
            schema = XMLSchemaFactory.getSchema(xsdPath);
        } catch (SAXException e) {
            logger.error(() -> "Invalid schema", e);
            throw new ParserException(e);
        }

        XSDValidator validator = new XSDValidator();
        validator.setSchema(schema);
        StreamSource source = new StreamSource(new ByteArrayInputStream(xml.getBytes()));
        if (!validator.isValidXml(source)) {
            logger.error(() -> "Invalid xml file");
            throw new InvalidInpitXMLException("Invalid xml file");
        }

        DOMBuilder builder;
        try {
            DomParser parser = new DomParser();
            parser.setSchema(schema);
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
            builder = new DOMBuilder(parser.getDocument(inputStream));
        } catch (ParserConfigurationException e) {
            logger.error(() -> "Bad DOM parser configuration", e);
            throw new ParserException(e);
        } catch (ParserException e) {
            logger.error(() -> "Error while parsing", e);
            throw e;
        }
        return builder.buildGemSet();
    }

}
