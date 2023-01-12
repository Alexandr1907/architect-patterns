package com.otus.spaceBattle.exceptionHandler;

import com.otus.spaceBattle.command.Command;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

@RequiredArgsConstructor
public class MainExceptionHandler {

    @NonNull
    private final Map<
            Pair<Class<? extends Exception>, Class<? extends Command>>,
            CommandExceptionHandler
            > exceptionAndCommand2Handler;


    public void handle(Exception exception, Command executedCommand, Queue<Command> queue) {
        Class<? extends Exception> eClass = exception.getClass();
        Class<? extends Command> cClass = executedCommand.getClass();

        List<CommandExceptionHandler> existHandlers = Stream.of(
                        exceptionAndCommand2Handler.get(Pair.of(eClass, cClass)),
                        exceptionAndCommand2Handler.get(Pair.of(null, cClass)),
                        exceptionAndCommand2Handler.get(Pair.of(eClass, null))
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (existHandlers.isEmpty()) {
            exception.printStackTrace();
        } else {
            existHandlers.forEach(h -> h.handle(exception, executedCommand, queue));
        }
    }

}
