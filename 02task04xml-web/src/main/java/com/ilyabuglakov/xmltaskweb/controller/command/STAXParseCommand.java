package com.ilyabuglakov.xmltaskweb.controller.command;

import com.ilyabuglakov.xmltaskweb.exception.InvalidInpitXMLException;
import com.ilyabuglakov.xmltaskweb.exception.ParserException;
import com.ilyabuglakov.xmltaskweb.exception.XMLFileContentException;
import com.ilyabuglakov.xmltaskweb.model.gem.Gem;
import com.ilyabuglakov.xmltaskweb.service.gem.builder.STAXBuilder;
import com.ilyabuglakov.xmltaskweb.service.xml.XMLSchemaFactory;
import com.ilyabuglakov.xmltaskweb.service.xml.validator.XSDValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

public class STAXParseCommand implements Command {
    private static Logger logger = LogManager.getLogger(STAXParseCommand.class);

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

        try {
            STAXBuilder builder = new STAXBuilder();
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
            return builder.buildGemSet(inputStream);
        } catch (XMLFileContentException e) {
            logger.error(() -> "Error while parsing, wrong tag", e);
            throw new InvalidInpitXMLException(e);
        } catch (XMLStreamException e) {
            logger.error(() -> "Bad input file content", e);
            throw new InvalidInpitXMLException(e);
        }

    }
}
