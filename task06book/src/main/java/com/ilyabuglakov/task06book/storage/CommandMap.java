package com.ilyabuglakov.task06book.storage;

import com.ilyabuglakov.task06book.bean.CommandName;
import com.ilyabuglakov.task06book.controller.comand.AddBookCommand;
import com.ilyabuglakov.task06book.controller.comand.Command;
import com.ilyabuglakov.task06book.controller.comand.EmptyCommand;
import com.ilyabuglakov.task06book.controller.comand.FindByTagCommand;
import com.ilyabuglakov.task06book.controller.comand.FlushToFileCommand;
import com.ilyabuglakov.task06book.controller.comand.ReadBookFileCommand;
import com.ilyabuglakov.task06book.controller.comand.RemoveBookCommand;
import com.ilyabuglakov.task06book.controller.comand.ShowBookRepositoryCommand;
import com.ilyabuglakov.task06book.controller.comand.SortCommand;

import java.util.EnumMap;

public class CommandMap {
    private static CommandMap instance = new CommandMap();
    private static EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    static {
        commands.put(CommandName.ADD_BOOK, new AddBookCommand());
        commands.put(CommandName.FLUSH_TO_FILE, new FlushToFileCommand());
        commands.put(CommandName.SHOW_BOOK_REPOSITORY, new ShowBookRepositoryCommand());
        commands.put(CommandName.READ_BOOKS_FILE, new ReadBookFileCommand());
        commands.put(CommandName.REMOVE_BOOK, new RemoveBookCommand());
        commands.put(CommandName.FIND_BY_TAG, new FindByTagCommand());
        commands.put(CommandName.SORT_BOOKS, new SortCommand());
        commands.put(CommandName.EXIT, new EmptyCommand());
//        commands.put(CommandName.SHOW_STRING, new ShowStringCommand());
//        commands.put(CommandName.INPUT_STRING, new InputStringCommand());
//        commands.put(CommandName.INPUT_STRING_CONSOLE, new InputStringConsoleCommand());
//        commands.put(CommandName.INPUT_STRING_FILE, new InputStringFileCommand());
//        commands.put(CommandName.REPLACE_POSITION, new ReplaceAllLettersInPositionCommand());
//        commands.put(CommandName.CORRECT_MISTAKES, new CorrectMistakesCommand());
//        commands.put(CommandName.REPLACE_WORDS, new ReplaceWordsCommand());
//        commands.put(CommandName.CLEAN_THE_TEXT, new CleanTheTextCommand());
//        commands.put(CommandName.DELETE_CONSONANT_WORDS, new DeleteConsonantWordsCommand());
//        commands.put(CommandName.CHOOSE_LOCALE, new ChooseLocaleCommand());
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
