package com.otus.spaceBattle.runner;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.exceptionHandler.MainExceptionHandler;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommandRunnerTest {
    @Mock
    private MainExceptionHandler mainExceptionHandler;

    @Test
    void run_shouldCallAllCommandsAndSendExceptionToExceptionHandler() {
        //given
        Command c1 = Mockito.mock(Command.class);
        Command c2 = Mockito.mock(Command.class);
        RuntimeException runtimeException1 = new RuntimeException();
        Mockito.doThrow(runtimeException1).when(c2).execute();
        Command c3 = Mockito.mock(Command.class);
        RuntimeException runtimeException2 = new RuntimeException();
        Mockito.doThrow(runtimeException2).when(c3).execute();
        Command c4 = Mockito.mock(Command.class);

        LinkedList<Command> commandQueue = new LinkedList<>(Arrays.asList(c1, c2, c3, c4));
        CommandRunner cr = new CommandRunner(commandQueue, mainExceptionHandler);

        //when
        cr.run();

        //then
        InOrder inOrder = Mockito.inOrder(c1, c2, c3, c4, mainExceptionHandler);
        inOrder.verify(c1).execute();
        inOrder.verify(c2).execute();
        inOrder.verify(mainExceptionHandler).handle(runtimeException1, c2, commandQueue);
        inOrder.verify(c3).execute();
        inOrder.verify(mainExceptionHandler).handle(runtimeException2, c3, commandQueue);
        inOrder.verify(c4).execute();
    }
}