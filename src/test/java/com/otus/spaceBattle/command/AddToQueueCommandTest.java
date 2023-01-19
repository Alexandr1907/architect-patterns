package com.otus.spaceBattle.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Queue;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddToQueueCommandTest {
    @Mock
    private Queue<Command> commandQueue;

    @Mock
    private Command command;
    private AddToQueueCommand addToQueueCommand;

    @BeforeEach
    void beforeEach() {
        addToQueueCommand = new AddToQueueCommand(commandQueue, command);
    }

    @Test
    void execute() {
        addToQueueCommand.execute();

        verify(commandQueue).add(command);
    }
}