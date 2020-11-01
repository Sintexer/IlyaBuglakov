package com.ilyabuglakov.task0201books.storage;

import com.ilyabuglakov.task0201books.bean.CommandName;
import com.ilyabuglakov.task0201books.controller.comand.Command;
import com.ilyabuglakov.task0201books.controller.comand.EmptyCommand;
import com.ilyabuglakov.task0201books.controller.comand.FindByTagCommand;
import com.ilyabuglakov.task0201books.controller.comand.FlushToFileCommand;
import com.ilyabuglakov.task0201books.controller.comand.ReadPublicationFileCommand;
import com.ilyabuglakov.task0201books.controller.comand.RemovePublicationCommand;
import com.ilyabuglakov.task0201books.controller.comand.ShowPublicationRepositoryCommand;
import com.ilyabuglakov.task0201books.controller.comand.SortCommand;

import java.util.EnumMap;

public class CommandMap {
    private static CommandMap instance = new CommandMap();
    private static EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    static {
        commands.put(CommandName.FLUSH_TO_FILE, new FlushToFileCommand());
        commands.put(CommandName.SHOW_REPOSITORY, new ShowPublicationRepositoryCommand());
        commands.put(CommandName.READ_PUBLICATIONS_FILE, new ReadPublicationFileCommand());
        commands.put(CommandName.REMOVE_PUBLICATION, new RemovePublicationCommand());
        commands.put(CommandName.FIND_BY_TAG, new FindByTagCommand());
        commands.put(CommandName.SORT_BOOKS, new SortCommand());
        commands.put(CommandName.EXIT, new EmptyCommand());
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
     * @param commandName enum name of command
     * @return Command entity, associated with enum name
     */
    public Command getCommand(CommandName commandName) {
        return commands.get(commandName);
    }
}
