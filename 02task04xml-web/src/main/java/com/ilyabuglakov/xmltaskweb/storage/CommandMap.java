package com.ilyabuglakov.xmltaskweb.storage;

import com.ilyabuglakov.xmltaskweb.controller.command.Command;
import com.ilyabuglakov.xmltaskweb.controller.command.DOMParseCommand;
import com.ilyabuglakov.xmltaskweb.controller.command.SAXParseCommand;
import com.ilyabuglakov.xmltaskweb.controller.command.STAXParseCommand;
import com.ilyabuglakov.xmltaskweb.model.ParserType;

import java.util.EnumMap;

public class CommandMap {
    private static CommandMap instance = new CommandMap();
    private static EnumMap<ParserType, Command> commands = new EnumMap<>(ParserType.class);

    static {
        commands.put(ParserType.DOM, new DOMParseCommand());
        commands.put(ParserType.SAX, new SAXParseCommand());
        commands.put(ParserType.STAX, new STAXParseCommand());
    }

    private CommandMap() {
    }

    /**
     * Returns instance of singleton class
     *
     * @return CommandMap instance
     */
    public static CommandMap getInstance() {
        return instance;
    }

    /**
     * Returns Command entity associated with CommandName
     *
     * @param parserType enum name of parser
     * @return Command entity, associated with enum name
     */
    public Command getCommand(ParserType parserType) {
        return commands.get(parserType);
    }
}
