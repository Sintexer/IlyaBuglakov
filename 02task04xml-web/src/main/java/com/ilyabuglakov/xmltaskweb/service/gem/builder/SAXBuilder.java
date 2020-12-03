package com.ilyabuglakov.xmltaskweb.service.gem.builder;

import com.ilyabuglakov.xmltaskweb.model.gem.Gem;
import com.ilyabuglakov.xmltaskweb.service.xml.parser.SAXHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.io.InputStream;
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

    public Set<Gem> buildGemSet(InputStream inputStream) throws IOException, SAXException {
        parser.parse(inputStream, saxHandler);
        return saxHandler.getGems();
    }
}
