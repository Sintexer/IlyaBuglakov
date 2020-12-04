package com.ilyabuglakov.xmltask.service.xml.parser;

import com.ilyabuglakov.xmltask.exception.ParserException;
import com.ilyabuglakov.xmltask.service.xml.XMLSchemaFactory;
import com.ilyabuglakov.xmltask.storage.PathStorage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.*;

public class DomParserTest {

    DomParser parser;

    @BeforeTest
    public void initParser() throws SAXException, ParserConfigurationException {
        parser = new DomParser();
        parser.setSchema(XMLSchemaFactory.getSchema(PathStorage.getInstance().getXsdPath()));
    }

    private static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<gems xmlns=\"http://ilyabuglacov.com/gems\"\n" +
            "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "      xsi:schemaLocation=\"http://ilyabuglacov.com/gems gems.xsd\">\n" +
            "    <gem mineral=\"DIAMOND\">\n" +
            "        <name>Zalorel02</name>\n" +
            "        <visualParameters facesNumber=\"8\">\n" +
            "            <color>BLUE</color>\n" +
            "            <transparency>90</transparency>\n" +
            "        </visualParameters>\n" +
            "        <preciousness>PRECIOUS</preciousness>\n" +
            "        <origin>GERMANY</origin>\n" +
            "        <minedDate>1912-02-03</minedDate>\n" +
            "        <specialCertificate>\n" +
            "            <certificateId>ad626234erl</certificateId>\n" +
            "            <organization>DiamondGroup</organization>\n" +
            "            <receivingDate>1960-04-04</receivingDate>\n" +
            "            <specialReason>The diamond of Queen Elizabeth</specialReason>\n" +
            "        </specialCertificate>\n" +
            "        <weight>2</weight>\n" +
            "    </gem>" +
            "</gems>";


    @Test
    public void testGetDocument() throws ParserException {
        assertNotNull(parser.getDocument(new ByteArrayInputStream(xml.getBytes())));
    }
}