package com.ilyabuglakov.raise.storage;

import com.ilyabuglakov.raise.service.property.ApplicationProperties;
import com.ilyabuglakov.raise.service.property.PropertyParser;
import com.ilyabuglakov.raise.service.property.exception.PropertyCantInitException;
import com.ilyabuglakov.raise.service.property.exception.PropertyFileException;
import lombok.Getter;

@Getter
public class PropertiesStorage {

    private PropertyParser pages;
    private PropertyParser links;
    private PropertyParser form;

    private static class InstanceHolder {
        public static PropertiesStorage INSTANCE = new PropertiesStorage();
    }

    public static PropertiesStorage getInstance() {
        return PropertiesStorage.InstanceHolder.INSTANCE;
    }

    private PropertiesStorage() {
        pages = initProperties("pages.properties");
        pages.setPrefix("/template");
        links = initProperties("links.properties");
        form = initProperties("form.properties");
    }

    public static String getPagePath(String property) {
        return ApplicationProperties.getProperty("app.page.root") + getInstance().pages.getProperty(property);
    }

    private PropertyParser initProperties(String filename){
        try {
            return new PropertyParser("links.properties");
        } catch (PropertyFileException e) {
            throw new PropertyCantInitException(e);
        }
    }

}
