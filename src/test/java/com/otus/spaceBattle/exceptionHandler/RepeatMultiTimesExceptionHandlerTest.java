package com.otus.spaceBattle.exceptionHandler;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.runner.CommandRunner;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

public class RepeatMultiTimesExceptionHandlerTest {

    @Test
    void handle_shouldCall3TypesOfHandlers() {
        //given
        NullPointerException exception = Mockito.spy(NullPointerException.class);
        Command cmd1 = Mockito.mock(Command.class);
        Command cmd2 = Mockito.mock(Command.class);
        Command cmd3 = Mockito.mock(Command.class);

        CommandExceptionHandler h1 = Mockito.mock(CommandExceptionHandler.class);
        CommandExceptionHandler h2 = Mockito.mock(CommandExceptionHandler.class);
        CommandExceptionHandler h3 = Mockito.mock(CommandExceptionHandler.class);
        CommandExceptionHandler h4 = Mockito.mock(CommandExceptionHandler.class);

        LinkedList<Command> queue = new LinkedList<>();

        Map<Pair<Class<? extends Exception>, Class<? extends Command>>, CommandExceptionHandler> map =
                new HashMap<>();
        map.put(Pair.of(exception.getClass(), cmd1.getClass()), h1);
        map.put(Pair.of(null, cmd2.getClass()), h2);
        map.put(Pair.of(exception.getClass(), null), h3);
        map.put(Pair.of(IndexOutOfBoundsException.class, cmd3.getClass()), h4);

        MainExceptionHandler mainHandler = new RepeatMultiTimesExceptionHandler(map, 2);

        //when
        mainHandler.handle(exception, cmd1, queue);
        mainHandler.handle(exception, cmd2, queue);
        mainHandler.handle(exception, cmd3, queue);

        mainHandler.handle(exception, cmd1, queue);
        mainHandler.handle(exception, cmd2, queue);
        mainHandler.handle(exception, cmd3, queue);

        mainHandler.handle(exception, cmd1, queue);
        Mockito.verify(h1).handle(exception, cmd1, queue);

        mainHandler.handle(exception, cmd2, queue);
        Mockito.verify(h2).handle(exception, cmd2, queue);

        mainHandler.handle(exception, cmd3, queue);
        Mockito.verify(h3).handle(exception, cmd3, queue);

        //then
        Mockito.verify(h4, Mockito.never()).handle(any(), any(), any());
        Mockito.verify(exception, Mockito.never()).printStackTrace();
    }
}
