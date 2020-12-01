package com.ilyabuglakov.xmltask.service.xml.parser;

import com.ilyabuglakov.xmltask.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.IOException;

public class DomParser {
    private static Logger logger = LogManager.getLogger(DomParser.class);

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;

    public DomParser() throws ParserConfigurationException {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(false);
    }

    public void setSchema(Schema schema) throws ParserConfigurationException {
        documentBuilderFactory.setSchema(schema);
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
    }

    public Document getDocument(String path) throws ParserException {
        try {
            Document document = documentBuilder.parse(new File(path));
            document.getDocumentElement().normalize();
            return document;
        } catch (SAXException e) {
            logger.error(() -> "Parser SAX error", e);
            throw new ParserException(e);
        } catch (IOException e) {
            logger.error(() -> "Parser IO error", e);
            throw new ParserException(e);
        }

    }
}
