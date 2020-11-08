package com.ilyabuglakov.matrix.storage;

import com.ilyabuglakov.matrix.bean.CommandName;
import com.ilyabuglakov.matrix.controller.command.ArbitrationThreadCommand;
import com.ilyabuglakov.matrix.controller.command.ChangeLocaleCommand;
import com.ilyabuglakov.matrix.controller.command.Command;
import com.ilyabuglakov.matrix.controller.command.EmptyCommand;
import com.ilyabuglakov.matrix.controller.command.FullLockFillCommand;
import com.ilyabuglakov.matrix.controller.command.PreparedThreadCommand;
import com.ilyabuglakov.matrix.controller.command.ReadMatrixCommand;
import com.ilyabuglakov.matrix.controller.command.ShowMatrixCommand;
import com.ilyabuglakov.matrix.controller.command.StateLockCommand;
import com.ilyabuglakov.matrix.controller.command.WriteMatrixToFileCommand;

import java.util.EnumMap;

public class CommandMap {
    private static CommandMap instance = new CommandMap();
    private static EnumMap<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    static {
        commands.put(CommandName.READ_MATRIX, new ReadMatrixCommand());
        commands.put(CommandName.SHOW_MATRIX, new ShowMatrixCommand());
        commands.put(CommandName.FULL_LOCK, new FullLockFillCommand());
        commands.put(CommandName.STATE_LOCK_THREAD, new StateLockCommand());
        commands.put(CommandName.PREPARED_THREAD, new PreparedThreadCommand());
        commands.put(CommandName.ARBITRATION_THREAD, new ArbitrationThreadCommand());
        commands.put(CommandName.WRITE_TO_FILE, new WriteMatrixToFileCommand());
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
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
