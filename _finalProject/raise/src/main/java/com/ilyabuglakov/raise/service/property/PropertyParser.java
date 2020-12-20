package com.ilyabuglakov.raise.service.property;

import com.ilyabuglakov.raise.service.path.PathService;
import com.ilyabuglakov.raise.service.property.exception.PropertyFileException;
import lombok.AllArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyParser {

    private Properties properties;

    public PropertyParser(String propertyFileName) throws PropertyFileException {
        try(InputStream inputStream =
                    new FileInputStream(PathService.getInstance().getResourcePath(propertyFileName))) {
        properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new PropertyFileException(e);
        }
    }

    public String getProperty(String propertyName){
        return properties.getProperty(propertyName);
    }
}
