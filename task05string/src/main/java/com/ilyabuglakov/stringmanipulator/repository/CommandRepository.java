package com.ilyabuglakov.stringmanipulator.repository;

import com.ilyabuglakov.stringmanipulator.beans.CommandName;
import com.ilyabuglakov.stringmanipulator.command.ChooseLocaleCommand;
import com.ilyabuglakov.stringmanipulator.command.CleanTheTextCommand;
import com.ilyabuglakov.stringmanipulator.command.Command;
import com.ilyabuglakov.stringmanipulator.command.CorrectMistakesCommand;
import com.ilyabuglakov.stringmanipulator.command.DeleteConsonantWordsCommand;
import com.ilyabuglakov.stringmanipulator.command.EmptyCommand;
import com.ilyabuglakov.stringmanipulator.command.InputStringCommand;
import com.ilyabuglakov.stringmanipulator.command.InputStringConsoleCommand;
import com.ilyabuglakov.stringmanipulator.command.InputStringFileCommand;
import com.ilyabuglakov.stringmanipulator.command.ReplaceAllLettersInPositionCommand;
import com.ilyabuglakov.stringmanipulator.command.ReplaceWordsCommand;
import com.ilyabuglakov.stringmanipulator.command.ShowStringCommand;

import java.util.EnumMap;

/**
 * This repository connects CommandName with specific Command entity and stores them.
 * Implemented as singleton.
 */
public class CommandRepository {
    private static CommandRepository instance = new CommandRepository();
    private static EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    static {
        commands.put(CommandName.EXIT, new EmptyCommand());
        commands.put(CommandName.SHOW_STRING, new ShowStringCommand());
        commands.put(CommandName.INPUT_STRING, new InputStringCommand());
        commands.put(CommandName.INPUT_STRING_CONSOLE, new InputStringConsoleCommand());
        commands.put(CommandName.INPUT_STRING_FILE, new InputStringFileCommand());
        commands.put(CommandName.REPLACE_POSITION, new ReplaceAllLettersInPositionCommand());
        commands.put(CommandName.CORRECT_MISTAKES, new CorrectMistakesCommand());
        commands.put(CommandName.REPLACE_WORDS, new ReplaceWordsCommand());
        commands.put(CommandName.CLEAN_THE_TEXT, new CleanTheTextCommand());
        commands.put(CommandName.DELETE_CONSONANT_WORDS, new DeleteConsonantWordsCommand());
        commands.put(CommandName.CHOOSE_LOCALE, new ChooseLocaleCommand());
    }

    private CommandRepository() {
    }

    /**
     * Returns instance of singleton class
     * @return CommandRepository instance
     */
    public static CommandRepository getInstance() {
        return instance;
    }

    /**
     * Returns Command entity associated with CommandName
     * @param commandName enum name of command
     * @return Command entity, associated with enum name
     */
    public Command getCommand(CommandName commandName) {
        return commands.get(commandName);
    }

}
