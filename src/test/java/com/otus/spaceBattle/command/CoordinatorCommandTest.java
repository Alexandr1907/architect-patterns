package com.otus.spaceBattle.command;

import com.otus.spaceBattle.exception.CommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoordinatorCommandTest {
    private Queue<TransactionParticipant> queue;
    private TransactionCoordinatorCommand command;

    private TransactionParticipant participantMove;
    private TransactionParticipant participantBernFuel;

    @BeforeEach
    void beforeEach() {
        participantMove = Mockito.mock(TransactionParticipant.class);
        participantBernFuel = Mockito.mock(TransactionParticipant.class);
        command = new TransactionCoordinatorCommand(List.of(participantMove, participantBernFuel));
    }

    @Test
    void execute_successfulExecute() {
        when(participantMove.isReady()).thenReturn(true);
        when(participantBernFuel.isReady()).thenReturn(true);

        command.execute();

        verify(participantMove).execute();
        verify(participantBernFuel).execute();
    }

    @Test
    void execute_notReadyExecute() {
        when(participantMove.isReady()).thenReturn(true);
        when(participantBernFuel.isReady()).thenReturn(false);

        assertThrows(CommandException.class, () -> command.execute());

        verify(participantMove, never()).execute();
        verify(participantBernFuel, never()).execute();
    }

    @Test
    void execute_rollBackExecute() {
        when(participantMove.isReady()).thenReturn(true);
        when(participantBernFuel.isReady()).thenReturn(true);

        doThrow(new RuntimeException()).when(participantBernFuel).execute();

        command.execute();

        verify(participantMove).execute();
        verify(participantMove).rollback();
        verify(participantBernFuel).rollback();
    }
}