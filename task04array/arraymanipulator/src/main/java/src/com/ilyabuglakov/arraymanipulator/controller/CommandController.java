package src.com.ilyabuglakov.arraymanipulator.controller;

import src.com.ilyabuglakov.arraymanipulator.controller.command.EmptyCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FillArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FindMaxCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.FindMinCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.IndexOfCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.InputArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.RandomArrayCommand;
import src.com.ilyabuglakov.arraymanipulator.controller.command.ShowArrayCommand;

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
    }

    public static CommandController getInstance(){
        return controller;
    }

    public Command getCommand(CommandName commandName){
        return commands.get(commandName);
    }
}
