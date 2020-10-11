package com.ilyabuglakov.stringmanipulator.controller;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.repository.MessageRepository;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageController {

    private static final String PROPERTY_PATH = "property.text";
    private MessageRepository messageRepository = new MessageRepository();
    private Locale locale = new Locale("en_US");
    private ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_PATH, locale);

    public MessageController(){

    }

    public String getMessage(Enum<?> id){
        return rb.getString(messageRepository.getPropertyName(id));
    }

    public void setLocale(Locale locale){
        this.locale = locale;
        rb = ResourceBundle.getBundle(PROPERTY_PATH, locale);
    }

}
