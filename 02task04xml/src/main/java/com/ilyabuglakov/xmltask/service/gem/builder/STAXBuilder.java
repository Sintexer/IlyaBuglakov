package com.ilyabuglakov.xmltask.service.gem.builder;

import com.ilyabuglakov.xmltask.exception.XMLFileContentException;
import com.ilyabuglakov.xmltask.model.gem.Color;
import com.ilyabuglakov.xmltask.model.gem.Gem;
import com.ilyabuglakov.xmltask.model.gem.Mineral;
import com.ilyabuglakov.xmltask.model.gem.Origin;
import com.ilyabuglakov.xmltask.model.gem.Preciousness;
import com.ilyabuglakov.xmltask.model.gem.VisualParameters;
import com.ilyabuglakov.xmltask.model.gem.certificate.Certificate;
import com.ilyabuglakov.xmltask.model.gem.certificate.SpecialCertificate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class STAXBuilder {

    private static Logger logger = LogManager.getLogger(STAXBuilder.class);

    private XMLInputFactory xmlInputFactory;

    public STAXBuilder() {
        xmlInputFactory = XMLInputFactory.newInstance();
    }

    public Set<Gem> buildGemSet(String inputPath) throws FileNotFoundException, XMLStreamException {
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileInputStream(new File(inputPath)));
        Set<Gem> gems = new LinkedHashSet<>();
        while (reader.hasNext()){
            int type = reader.next();
            String tagName;
            if(type == XMLStreamConstants.START_ELEMENT){
                tagName = reader.getLocalName();
                if(tagName.equals("gem")){
                    gems.add(buildGem(reader));
                }
            }
        }
        return gems;
    }

    public Gem buildGem(XMLStreamReader reader) throws XMLStreamException {
        Gem current = new Gem();

        current.setMineral(Mineral.valueOf(reader.getAttributeValue(null, "mineral")));

        while (reader.hasNext()){
            int type = reader.next();
            if(type == XMLStreamConstants.START_ELEMENT){
                String currentTag = reader.getLocalName();
                if (currentTag.equals("name")) {
                    current.setName(getTagContent(reader));
                } else if (currentTag.equals("visualParameters")) {
                    current.setVisualParameters(buildVisualParameters(reader));
                } else if (currentTag.equals("preciousness")) {
                    current.setPreciousness(Preciousness.valueOf(getTagContent(reader)));
                } else if (currentTag.equals("origin")) {
                    current.setOrigin(Origin.valueOf(getTagContent(reader)));
                } else if (currentTag.equals("minedDate")) {
                    current.setMinedDate(LocalDate.parse(getTagContent(reader)));
                } else if (currentTag.equals("certificate") || currentTag.equals("specialCertificate")) {
                    current.setCertificate(buildCertificate(reader, currentTag));
                } else if (currentTag.equals("weight")) {
                    current.setWeight(Integer.parseInt(getTagContent(reader)));
                } else {
                    logger.error(() -> "Unknown tag: " + currentTag);
                    throw new XMLFileContentException("Unknown tag was found");
                }

            } else if(type == XMLStreamConstants.END_ELEMENT){
                if(reader.getLocalName().equals("gem"))
                    return current;
            }
        }
        throw new XMLStreamException("Unknown element in Gem");
    }

    public VisualParameters buildVisualParameters(XMLStreamReader reader) throws XMLStreamException {
        VisualParameters visualParameters = new VisualParameters();

        if(reader.getAttributeValue(null, "facesNumber")!=null){
            visualParameters.setFacesNumber(Integer.parseInt(reader.getAttributeValue(null, "facesNumber")));
        } else{
            visualParameters.setFacesNumber(0);
        }

        int type;
        while (reader.hasNext()){
            type = reader.next();
            if(type == XMLStreamConstants.START_ELEMENT){
                String currentTag = reader.getLocalName();
                if(currentTag.equals("color")){
                    visualParameters.setColor(Color.valueOf(getTagContent(reader)));
                } else if(currentTag.equals("transparency")){
                    visualParameters.setTransparency(Integer.parseInt(getTagContent(reader)));
                }
            }else if(type == XMLStreamConstants.END_ELEMENT){
                if(reader.getLocalName().equals("visualParameters"))
                    return visualParameters;
            }
        }
        throw new XMLStreamException("Unknown element in VisualParameters");
    }

    public Certificate buildCertificate(XMLStreamReader reader, String certificateType) throws XMLStreamException {
        Certificate certificate;
        if(certificateType.equals("specialCertificate")){
            certificate = new SpecialCertificate();
        } else {
            certificate = new Certificate();
        }

        int type;
        while (reader.hasNext()){
            type = reader.next();
            if(type == XMLStreamConstants.START_ELEMENT){
                String currentTag = reader.getLocalName();
                if(currentTag.equals("certificateId")){
                    certificate.setCertificateId(getTagContent(reader));
                } else if(currentTag.equals("receivingDate")){
                    certificate.setReceivingDate(LocalDate.parse(getTagContent(reader)));
                } else if(currentTag.equals("organization")){
                    certificate.setOrganisation(getTagContent(reader));
                } else if(currentTag.equals("specialReason")){
                    ((SpecialCertificate)certificate).setSpecialReason(getTagContent(reader));
                }
            }else if(type == XMLStreamConstants.END_ELEMENT){
                if(reader.getLocalName().equals(certificateType))
                    return certificate;
            }
        }
        throw new XMLStreamException("Unknown element in Certificate");
    }

    public String getTagContent(XMLStreamReader reader) throws XMLStreamException {
        if(reader.hasNext()) {
            reader.next();
            return reader.getText();
        } else
            throw new XMLStreamException("Error receiving tag content: there are no more tags");
    }
}
