package com.ilyabuglakov.xmltask.service.gem.builder;

import com.ilyabuglakov.xmltask.model.gem.Color;
import com.ilyabuglakov.xmltask.model.gem.Gem;
import com.ilyabuglakov.xmltask.model.gem.Mineral;
import com.ilyabuglakov.xmltask.model.gem.Origin;
import com.ilyabuglakov.xmltask.model.gem.Preciousness;
import com.ilyabuglakov.xmltask.model.gem.VisualParameters;
import com.ilyabuglakov.xmltask.model.gem.certificate.Certificate;
import com.ilyabuglakov.xmltask.model.gem.certificate.SpecialCertificate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class DomBuilder {

    private Document document;

    public DomBuilder(Document document) {
        this.document = document;
    }

    public Set<Gem> buildGemSet(){
        Set<Gem> gemSet = new LinkedHashSet<>();
        NodeList gemNodes = document.getDocumentElement().getElementsByTagName("gem");
        for(int i =0;i<gemNodes.getLength();++i){
            Element element = (Element)gemNodes.item(i);
            gemSet.add(buildGem(element));
        }
        return gemSet;
    }

    public Gem buildGem(Element element){
        Gem gem = new Gem();
        gem.setName(buildName(element));
        gem.setVisualParameters(buildVisualParameters(element));
        gem.setMineral(buildMineral(element));
        gem.setPreciousness(buildPreciousness(element));
        gem.setOrigin(buildOrigin(element));
        gem.setMinedDate(buildMinedDate(element));
        gem.setCertificate(buildCertificate(element));
        gem.setWeight(buildWeight(element));
        return gem;
    }

    public String buildName(Element element){
        return getChildContent(element, "name");
    }

    public VisualParameters buildVisualParameters(Element element){
        VisualParameters visualParameters = new VisualParameters();
        element = getSingleChild(element, "visualParameters");
        visualParameters.setFacesNumber(Integer.parseInt(element.getAttribute("facesNumber")));
        visualParameters.setTransparency(Integer.parseInt(getChildContent(element, "transparency")));
        visualParameters.setColor(Color.valueOf(getChildContent(element, "color")));
        return visualParameters;
    }

    public Preciousness buildPreciousness(Element element){
        return Preciousness.valueOf(getChildContent(element, "preciousness"));
    }

    public Mineral buildMineral(Element element){
        return Mineral.valueOf(element.getAttribute("mineral"));
    }

    public Origin buildOrigin(Element element){
        return Origin.valueOf(getChildContent(element, "origin"));
    }

    public LocalDate buildMinedDate(Element element){
        return LocalDate.parse(getChildContent(element, "minedDate"));
    }

    public Certificate buildCertificate(Element element){
        Certificate certificate;
        if(Objects.nonNull(getSingleChild(element, "specialCertificate"))){
            element = getSingleChild(element, "specialCertificate");
            SpecialCertificate specialCertificate = new SpecialCertificate();
            specialCertificate.setSpecialReason(getChildContent(element, "specialReason"));
            certificate = specialCertificate;
        } else{
            element = getSingleChild(element, "certificate");
            certificate = new Certificate();
        }
        certificate.setCertificateId(getChildContent(element, "certificateId"));
        certificate.setOrganisation(getChildContent(element, "organization"));
        certificate.setReceivingDate(LocalDate.parse(getChildContent(element, "receivingDate")));
        return certificate;
    }

    public int buildWeight(Element element){
        return Integer.parseInt(getChildContent(element, "weight"));
    }


    private Element getSingleChild(Element element, String childName){
        NodeList nodeList = element.getElementsByTagName(childName);
        return  (Element)nodeList.item(0);
    }

    private String getChildContent(Element element, String childName){
        element = getSingleChild(element, childName);
        return element.getTextContent().trim();
    }
}
