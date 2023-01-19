package com.otus.spaceBattle.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Queue;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class LongMove implements Command {
    @NonNull
    private final Move move;
    private final int iteration;
    @NonNull
    private final Queue<Command> commandQueue;

    @Override
    public void execute() {
        Stream.generate(() -> new AddToQueueCommand(commandQueue, move))    //todo get from IoC container, write tests
                .limit(iteration)
                .forEach(AddToQueueCommand::execute);
    }
}
