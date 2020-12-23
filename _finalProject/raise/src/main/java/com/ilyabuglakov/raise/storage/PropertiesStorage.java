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

    private static class InstanceHolder {
        public static PropertiesStorage INSTANCE = new PropertiesStorage();
    }

    public static PropertiesStorage getInstance() {
        return PropertiesStorage.InstanceHolder.INSTANCE;
    }

    private PropertiesStorage() {
        try {
            pages = new PropertyParser("pages.properties");
            pages.setPrefix("/template");
        } catch (PropertyFileException e) {
            throw new PropertyCantInitException(e);
        }
        try {
            links = new PropertyParser("links.properties");
        } catch (PropertyFileException e) {
            throw new PropertyCantInitException(e);
        }
    }

    public static String getPagePath(String property) {
        return ApplicationProperties.getProperty("app.page.root") + getInstance().pages.getProperty(property);
    }

}
