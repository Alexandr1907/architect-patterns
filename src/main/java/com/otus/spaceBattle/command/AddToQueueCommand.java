package com.otus.spaceBattle.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Queue;

@RequiredArgsConstructor
public class AddToQueueCommand implements Command {
    @NonNull
    private final Queue<Command> commandQueue;

    @NonNull
    private final Command command;

    @Override
    public void execute() {
        commandQueue.add(command);
    }
}
