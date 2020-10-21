package com.ilyabuglakov.task06book.controller;

import com.ilyabuglakov.task06book.storage.MessageMap;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MessageController {

    private static final String PROPERTY_PATH = "property.text";
    private MessageMap messageMap = new MessageMap();
    private Locale locale = new Locale("en_US");
    private ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_PATH, locale);

    /**
     * Returns String from resource bundle associated with Enum<?> id
     *
     * @param id - Enum<?> identification of string
     * @return String from resource bundle for current locale
     */
    public String getMessage(Enum<?> id) {
        return rb.getString(messageMap.getPropertyName(id));
    }

    /**
     * Returns List of Strings from resource bundle, each associated with Enum<?> id
     *
     * @param properties - Collection of Enum<?> identifications of strings
     * @return List<String> from resource bundle for current locale
     */
    public List<String> getMessages(Collection<? extends Enum<?>> properties) {
        return properties.stream()
                .map(this::getMessage)
                .collect(Collectors.toList());
    }

    /**
     * Sets current application locale
     *
     * @param locale new application locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        rb = ResourceBundle.getBundle(PROPERTY_PATH, locale);
    }

}