package com.ilyabuglakov.xmltask.service.xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XMLSchemaFactory {

    private static SchemaFactory schemaFactory =
            javax.xml.validation.SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    public static Schema getSchema(String path) throws SAXException {
        return schemaFactory.newSchema(new File(path));
    }

}
