package src.com.ilyabuglakov.arraymanipulator.controller;

import src.com.ilyabuglakov.arraymanipulator.controller.command.BinarySearchCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.EmptyCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FillJuggedArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FindFibNumbersCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FindSimpleNumbersCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FillArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FindMaxCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FindMinCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FindThreeDigitsUniqueCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.IndexOfCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.InputArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.InputJuggedArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.RandomArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.RandomJuggedArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.ShowArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.SortArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.SwitchToArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.SwitchToJuggedArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.SwitchTypeCommand;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandController {
    private static CommandController controller = new CommandController();
    private Map<CommandName, Command> commands = new LinkedHashMap<>();

    private CommandController(){
        commands.put(CommandName.EXIT, new EmptyCommand());
        commands.put(CommandName.SHOW_ARRAY, new ShowArrayCommand());
        commands.put(CommandName.FILL_ARRAY, new FillArrayCommand());
        commands.put(CommandName.INPUT_ARRAY, new InputArrayCommand());
        commands.put(CommandName.RANDOM_ARRAY, new RandomArrayCommand());
        commands.put(CommandName.INDEX_OF, new IndexOfCommand());
        commands.put(CommandName.FIND_MAX, new FindMaxCommand());
        commands.put(CommandName.FIND_MIN, new FindMinCommand());
        commands.put(CommandName.SWITCH_TYPE, new SwitchTypeCommand());
        commands.put(CommandName.SWITCH_TO_ARRAY, new SwitchToArrayCommand());
        commands.put(CommandName.SORT_ARRAY, new SortArrayCommand());
        commands.put(CommandName.BINARY_SEARCH, new BinarySearchCommand());
        commands.put(CommandName.FIND_SIMPLE_NUMBERS, new FindSimpleNumbersCommand());
        commands.put(CommandName.FIND_FIB_NUMBERS, new FindFibNumbersCommand());
        commands.put(CommandName.FIND_THREE_DIGITS_UNIQUE, new FindThreeDigitsUniqueCommand());
        commands.put(CommandName.SWITCH_TO_JUGGED_ARRAY, new SwitchToJuggedArrayCommand());
        commands.put(CommandName.FILL_JUGGED_ARRAY, new FillJuggedArrayCommand());
        commands.put(CommandName.INPUT_JUGGED_ARRAY, new InputJuggedArrayCommand());
        commands.put(CommandName.RANDOM_JUGGED_ARRAY, new RandomJuggedArrayCommand());

    }

    public static CommandController getInstance(){
        return controller;
    }

    public Command getCommand(CommandName commandName){
        return commands.get(commandName);
    }
}
