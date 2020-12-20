package com.ilyabuglakov.raise.service.property;

import com.ilyabuglakov.raise.service.property.exception.PropertyFileException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApplicationProperties extends PropertyParser {

    private static class InstanceHolder{
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
        super("application.properties");
    }

    public static ApplicationProperties getInstance(){
        return InstanceHolder.INSTANCE;
    }

}
