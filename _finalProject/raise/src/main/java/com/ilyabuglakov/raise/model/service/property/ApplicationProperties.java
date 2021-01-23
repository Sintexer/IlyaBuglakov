package com.ilyabuglakov.raise.model.service.property;

import com.ilyabuglakov.raise.model.service.property.exception.PropertyFileException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApplicationProperties {

    private PropertyParser propertyParser;

    private static class InstanceHolder {
        public static ApplicationProperties INSTANCE;

        static {
            try {
                INSTANCE = new ApplicationProperties();
            } catch (PropertyFileException e) {
                log.info("Application works without initialisation from property file");
            }
        }
    }

    private ApplicationProperties() throws PropertyFileException {
        propertyParser = new PropertyParser("application.properties");
    }

    public static ApplicationProperties getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static String getProperty(String propertyName) {
        return getInstance().propertyParser.getProperty(propertyName);
    }

}
