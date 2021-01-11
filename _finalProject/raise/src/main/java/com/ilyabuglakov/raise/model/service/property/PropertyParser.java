package com.ilyabuglakov.raise.model.service.property;

import com.ilyabuglakov.raise.model.service.path.PathService;
import com.ilyabuglakov.raise.model.service.property.exception.PropertyFileException;
import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyParser {

    private Properties properties;
    @Getter
    @Setter
    private String prefix = "";

    public PropertyParser(String propertyFileName) throws PropertyFileException {
        try (InputStream inputStream =
                     new FileInputStream(PathService.getInstance().getResourcePath(propertyFileName))) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new PropertyFileException(e);
        }
    }

    public String getProperty(String propertyName) {
        return prefix + properties.getProperty(propertyName);
    }
}
