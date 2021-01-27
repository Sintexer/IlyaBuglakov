package com.ilyabuglakov.raise.storage;

import com.ilyabuglakov.raise.model.service.property.PropertyParser;
import com.ilyabuglakov.raise.model.service.property.exception.PropertyCantInitException;
import com.ilyabuglakov.raise.model.service.property.exception.PropertyFileException;
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
        pages = initProperties("pages.properties");
        pages.setPrefix("/template");
        links = initProperties("links.properties");
    }

    private PropertyParser initProperties(String filename) {
        try {
            return new PropertyParser(filename);
        } catch (PropertyFileException e) {
            throw new PropertyCantInitException(e);
        }
    }

}
