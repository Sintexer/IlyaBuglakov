package com.ilyabuglakov.xmltaskweb.controller;


import com.ilyabuglakov.xmltaskweb.controller.command.Command;
import com.ilyabuglakov.xmltaskweb.exception.InvalidInpitXMLException;
import com.ilyabuglakov.xmltaskweb.exception.ParserException;
import com.ilyabuglakov.xmltaskweb.exception.SchemaException;
import com.ilyabuglakov.xmltaskweb.model.ParserType;
import com.ilyabuglakov.xmltaskweb.storage.CommandMap;

public class CommandController {
    private static CommandController instance = new CommandController();
    private CommandMap repository = CommandMap.getInstance();

    private CommandController() {
    }

    /**
     * @return instance of singleton class
     */
    public static CommandController getInstance() {
        return instance;
    }

    /**
     * @param parserType - Enum name of parser
     * @return command as Command class entity
     */
    public Command getCommand(ParserType parserType) {
        return repository.getCommand(parserType);
    }
}
