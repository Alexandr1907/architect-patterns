package com.otus.spaceBattle.command;

import com.otus.spaceBattle.excepton.CommandException;
import com.otus.spaceBattle.excepton.RepeatCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RepeatAfterExceptionCommandTest {

    @Mock
    private Command innerCommand;

    private RepeatAfterExceptionCommand afterExceptionCommand;

    @BeforeEach
    public void beforeEach() {
        afterExceptionCommand = new RepeatAfterExceptionCommand(innerCommand);
    }

    @Test
    void execute_run_inner_command() {
        afterExceptionCommand.execute();
        verify(innerCommand).execute();
    }

    @Test
    void execute_catchCommonCommandExceptionAndThrowRepeatExc() {
        doThrow(new CommandException()).when(innerCommand).execute();

        Assertions.assertThrows(RepeatCommandException.class, () -> afterExceptionCommand.execute());
    }
}