package com.otus.spaceBattle.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MoveWithBurnFuelTest {
    @Mock
    private Move move;
    @Mock
    private BurnFuelCommand burnFuelCommand;
    private MoveWithBurnFuel moveWithBurnFuel;

    @BeforeEach
    void beforeEach() {
        moveWithBurnFuel = new MoveWithBurnFuel(move, burnFuelCommand);
    }

    @Test
    void commands_execute() {
        moveWithBurnFuel.execute();

        verify(move).execute();
        verify(burnFuelCommand).execute();
    }
}