package com.ilyabuglakov.raise.command;

import com.ilyabuglakov.raise.command.impl.IndexCommand;
import lombok.Getter;

public enum CommandName {

    INDEX(new IndexCommand());

    @Getter
    private Command command;

    CommandName(Command command) {
        this.command = command;
    }
}
