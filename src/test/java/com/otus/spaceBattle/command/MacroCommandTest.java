package com.otus.spaceBattle.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class MacroCommandTest {
    @Mock
    private Command c1, c2, c3, c4;
    private MacroCommand macro;

    @BeforeEach
    void setUp() {
        macro = new MacroCommand(Arrays.asList(c1, c2, c3, c4));
    }

    @Test
    void execute_shouldCallAllCommandInOrder() {
        //when
        macro.execute();

        //then
        InOrder inOrder = Mockito.inOrder(c1, c2, c3, c4);
        inOrder.verify(c1).execute();
        inOrder.verify(c2).execute();
        inOrder.verify(c3).execute();
        inOrder.verify(c4).execute();
    }

    @Test
    void execute_shouldThrowExceptionFromInnerCommandAndStopCallCommand() {
        //given
        Mockito.doThrow(new RuntimeException()).when(c3).execute();
        //when
        Assertions.assertThrows(RuntimeException.class, () -> macro.execute());

        //then
        InOrder inOrder = Mockito.inOrder(c1, c2, c3, c4);
        inOrder.verify(c1).execute();
        inOrder.verify(c2).execute();
        inOrder.verify(c3).execute();
        inOrder.verify(c4, Mockito.never()).execute();
    }

    @Test
    void execute_shouldThrowNPEIfCommandNull() {
        //given
        macro = new MacroCommand(Arrays.asList(c1, c2, null, c4));

        //when
        Assertions.assertThrows(NullPointerException.class, () -> macro.execute());

        //then
        InOrder inOrder = Mockito.inOrder(c1, c2, c4);
        inOrder.verify(c1).execute();
        inOrder.verify(c2).execute();
        inOrder.verify(c4, Mockito.never()).execute();
    }
}