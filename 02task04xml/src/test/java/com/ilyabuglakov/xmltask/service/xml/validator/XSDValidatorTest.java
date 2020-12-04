package com.ilyabuglakov.xmltask.service.xml.validator;


import com.ilyabuglakov.xmltask.service.xml.XMLSchemaFactory;
import com.ilyabuglakov.xmltask.storage.PathStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;

import static org.testng.Assert.*;

@Test
public class XSDValidatorTest {

    private static Logger logger = LogManager.getLogger(XSDValidator.class);

    private XSDValidator validator;

    @BeforeTest
    private void setSchema(){
        validator = new XSDValidator();
        String xsdPath = PathStorage.getInstance().getXsdPath();
        try {
            validator.setSchema(XMLSchemaFactory.getSchema(xsdPath));
        } catch (SAXException e) {
            logger.error(e);
        }

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
    public void validationTest(){
        assertTrue(validator.isValidXml(new StreamSource(new ByteArrayInputStream(xml.getBytes()))));
    }

    @Test
    public void failedValidationTest(){
        String  localXml = "BUG" + xml;
        assertFalse(validator.isValidXml(new StreamSource(new ByteArrayInputStream(localXml.getBytes()))));
    }

    @Test
    public void failedElementValidationTest(){
        String  localXml = xml.replace("BLUE", "PURPLE");
        assertFalse(validator.isValidXml(new StreamSource(new ByteArrayInputStream(localXml.getBytes()))));
    }

}
