package com.ilyabuglakov.xmltaskweb.service.xml.parser;

import com.ilyabuglakov.xmltaskweb.exception.XMLFileContentException;
import com.ilyabuglakov.xmltaskweb.model.gem.Color;
import com.ilyabuglakov.xmltaskweb.model.gem.Gem;
import com.ilyabuglakov.xmltaskweb.model.gem.Mineral;
import com.ilyabuglakov.xmltaskweb.model.gem.Origin;
import com.ilyabuglakov.xmltaskweb.model.gem.Preciousness;
import com.ilyabuglakov.xmltaskweb.model.gem.VisualParameters;
import com.ilyabuglakov.xmltaskweb.model.gem.certificate.Certificate;
import com.ilyabuglakov.xmltaskweb.model.gem.certificate.SpecialCertificate;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class SAXHandler extends DefaultHandler {

    public static Logger logger = LogManager.getLogger(SAXHandler.class);

    @Getter
    private Set<Gem> gems;
    private Gem current;
    private String currentTag;

    @Override
    public void startDocument() {
        gems = new LinkedHashSet<>();
        logger.info("SAX document started");
    }

    @Override
    public void endDocument() {
        logger.info("SAX document ended");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("gem".equals(localName)) {
            current = new Gem();
            current.setMineral(Mineral.valueOf(attributes.getValue("mineral")));
        } else if ("visualParameters".equals(localName)) {
            VisualParameters currentVisualParameters = new VisualParameters();
            currentVisualParameters.setFacesNumber(Integer.parseInt(attributes.getValue("facesNumber")));
            current.setVisualParameters(currentVisualParameters);
        } else if ("certificate".equals(localName)) {
            current.setCertificate(new Certificate());
        } else if ("specialCertificate".equals(localName)) {
            current.setCertificate(new SpecialCertificate());
        } else {
            currentTag = localName;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws XMLFileContentException {
        String s = new String(ch, start, length).trim();
        if (!currentTag.isEmpty()) {
            if (currentTag.equals("name")) {
                current.setName(s);
            } else if (currentTag.equals("color")) {
                current.getVisualParameters().setColor(Color.valueOf(s));
            } else if (currentTag.equals("transparency")) {
                current.getVisualParameters().setTransparency(Integer.parseInt(s));
            } else if (currentTag.equals("preciousness")) {
                current.setPreciousness(Preciousness.valueOf(s));
            } else if (currentTag.equals("origin")) {
                current.setOrigin(Origin.valueOf(s));
            } else if (currentTag.equals("minedDate")) {
                current.setMinedDate(LocalDate.parse(s));
            } else if (currentTag.equals("certificateId")) {
                current.getCertificate().setCertificateId(s);
            } else if (currentTag.equals("organization")) {
                current.getCertificate().setOrganisation(s);
            } else if (currentTag.equals("receivingDate")) {
                current.getCertificate().setReceivingDate(LocalDate.parse(s));
            } else if (currentTag.equals("specialReason")) {
                ((SpecialCertificate) current.getCertificate()).setSpecialReason(s);
            } else if (currentTag.equals("weight")) {
                current.setWeight(Integer.parseInt(s));
            } else {
                logger.error(() -> "Unknown tag: " + currentTag);
                throw new XMLFileContentException("Unknown tag was found");
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("gem".equals(localName))
            gems.add(current);
    }
}
