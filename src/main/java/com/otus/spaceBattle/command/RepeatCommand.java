package com.otus.spaceBattle.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepeatCommand implements Command {
    @NonNull
    private final Command command;

    @Override
    public void execute() {
        command.execute();
    }
}
