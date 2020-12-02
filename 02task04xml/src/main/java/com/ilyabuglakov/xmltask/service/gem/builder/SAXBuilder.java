package com.ilyabuglakov.xmltask.service.gem.builder;

import com.ilyabuglakov.xmltask.model.gem.Gem;
import com.ilyabuglakov.xmltask.service.xml.parser.SAXHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

public class SAXBuilder {
    private SAXHandler saxHandler;
    private SAXParserFactory parserFactory;
    private SAXParser parser;

    public SAXBuilder() {
        saxHandler = new SAXHandler();
        parserFactory = SAXParserFactory.newInstance();
        parserFactory.setNamespaceAware(true);
    }

    public void setSchema(Schema schema) throws ParserConfigurationException, SAXException {
        parserFactory.setSchema(schema);
        parser = parserFactory.newSAXParser();
    }

    public Set<Gem> buildGemSet(String path) throws IOException, SAXException {
        parser.parse(new FileInputStream(new File(path)), saxHandler);
        return saxHandler.getGems();
    }
}
