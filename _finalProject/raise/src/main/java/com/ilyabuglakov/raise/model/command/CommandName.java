package com.ilyabuglakov.raise.model.command;

import com.ilyabuglakov.raise.model.command.impl.ToIndexCommand;
import com.ilyabuglakov.raise.service.property.PropertyParser;
import com.ilyabuglakov.raise.service.property.exception.PropertyCantInitException;
import com.ilyabuglakov.raise.service.property.exception.PropertyFileException;
import lombok.Getter;

public enum CommandName{

    INDEX(new ToIndexCommand());

    public static PropertyParser linksProperties;

    @Getter
    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    static {
        try {
            linksProperties = new PropertyParser("links.properties");
        } catch (PropertyFileException e) {
            throw new PropertyCantInitException("Can't init links.properties");
        }
    }
}
