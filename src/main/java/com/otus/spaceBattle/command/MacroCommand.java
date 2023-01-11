package com.otus.spaceBattle.command;

import lombok.NonNull;

import java.util.Collections;
import java.util.List;

public class MacroCommand implements Command {

    final List<Command> commands;

    public MacroCommand(@NonNull List<Command> commands) {
        this.commands = Collections.unmodifiableList(commands);
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
}
