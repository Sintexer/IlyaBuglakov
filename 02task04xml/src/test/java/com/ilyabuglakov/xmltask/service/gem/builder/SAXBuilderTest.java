package com.ilyabuglakov.xmltask.service.gem.builder;

import com.ilyabuglakov.xmltask.exception.ParserException;
import com.ilyabuglakov.xmltask.model.gem.Color;
import com.ilyabuglakov.xmltask.model.gem.Gem;
import com.ilyabuglakov.xmltask.model.gem.Mineral;
import com.ilyabuglakov.xmltask.model.gem.Origin;
import com.ilyabuglakov.xmltask.model.gem.Preciousness;
import com.ilyabuglakov.xmltask.model.gem.VisualParameters;
import com.ilyabuglakov.xmltask.model.gem.certificate.Certificate;
import com.ilyabuglakov.xmltask.service.xml.XMLSchemaFactory;
import com.ilyabuglakov.xmltask.storage.PathStorage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;

import static org.testng.Assert.assertTrue;

public class SAXBuilderTest {

    SAXBuilder builder;

    private static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<gems xmlns=\"http://ilyabuglacov.com/gems\"\n" +
            "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "      xsi:schemaLocation=\"http://ilyabuglacov.com/gems gems.xsd\">\n" +
            "    <gem mineral=\"DIAMOND\">\n" +
            "        <name>gem21</name>\n" +
            "        <visualParameters facesNumber=\"12\">\n" +
            "            <color>RED</color>\n" +
            "            <transparency>23</transparency>\n" +
            "        </visualParameters>\n" +
            "        <preciousness>SEMIPRECIOUS</preciousness>\n" +
            "        <origin>BELARUS</origin>\n" +
            "        <minedDate>2020-02-02</minedDate>\n" +
            "        <certificate>\n" +
            "            <certificateId>as323232asa</certificateId>\n" +
            "            <organization>Name</organization>\n" +
            "            <receivingDate>2020-02-02</receivingDate>\n" +
            "        </certificate>\n" +
            "        <weight>12</weight>\n" +
            "    </gem>" +
            "</gems>";

    @BeforeTest
    public void configure() throws SAXException, ParserConfigurationException {
        Schema schema = XMLSchemaFactory.getSchema(PathStorage.getInstance().getXsdPath());
        builder = new SAXBuilder();
        builder.setSchema(schema);
    }

    @Test
    public void buildTest() throws IOException, SAXException {

        Gem gem = new Gem("gem21", new VisualParameters(Color.RED, 23, 12), Mineral.DIAMOND,
                Preciousness.SEMIPRECIOUS, Origin.BELARUS, LocalDate.parse("2020-02-02"),
                new Certificate("as323232asa", "Name", LocalDate.parse("2020-02-02")), 12);
        assertTrue(builder.buildGemSet(new ByteArrayInputStream(xml.getBytes())).contains(gem));

    }
}