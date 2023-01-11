package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.FuelBurnable;
import com.otus.spaceBattle.exception.CommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.otus.spaceBattle.TestData.*;

@ExtendWith(MockitoExtension.class)
class BurnFuelCommandTest {
    @Mock
    private FuelBurnable fuelBurnable;

    private BurnFuelCommand consume;

    @BeforeEach
    void setUp() {
        consume = new BurnFuelCommand(fuelBurnable);
    }

    @Test
    void execute_shouldSetNewFuelLevel() {
        //given
        Mockito.when(fuelBurnable.getFuelLevel()).thenReturn(NORMAL_FUEL_LEVEL);
        Mockito.when(fuelBurnable.getConsumeSpeed()).thenReturn(CONSUME_FUEL);

        //when
        consume.execute();

        //then
        Mockito.verify(fuelBurnable).setFuelLevel(RESULT_FUEL_LEVEL);
    }

    @Test
    void execute_shouldThrowExceptionIfFuelNotEnough() {
        //given
        Mockito.when(fuelBurnable.getFuelLevel()).thenReturn(LOW_FUEL_LEVEL);
        Mockito.when(fuelBurnable.getConsumeSpeed()).thenReturn(CONSUME_FUEL);

        //when-then
        Assertions.assertThrows(CommandException.class, () -> consume.execute());
    }

    @Test
    void execute_shouldThrowExceptionIfFuelLevelNotAvailable() {
        //given
        Mockito.when(fuelBurnable.getFuelLevel()).thenThrow(RuntimeException.class);

        //when-then
        Assertions.assertThrows(RuntimeException.class, () -> consume.execute());
    }

    @Test
    void execute_shouldThrowExceptionIfConsumeSpeedNotAvailable() {
        //given
        Mockito.when(fuelBurnable.getFuelLevel()).thenReturn(NORMAL_FUEL_LEVEL);
        Mockito.when(fuelBurnable.getConsumeSpeed()).thenThrow(RuntimeException.class);

        //when-then
        Assertions.assertThrows(RuntimeException.class, () -> consume.execute());
    }

    @Test
    void execute_shouldThrowExceptionIfWetFuelLevelNotAvailable() {
        //given
        Mockito.when(fuelBurnable.getFuelLevel()).thenReturn(NORMAL_FUEL_LEVEL);
        Mockito.when(fuelBurnable.getConsumeSpeed()).thenReturn(CONSUME_FUEL);
        Mockito.doThrow(new RuntimeException()).when(fuelBurnable).setFuelLevel(Mockito.anyInt());

        //when-then
        Assertions.assertThrows(RuntimeException.class, () -> consume.execute());
    }
}