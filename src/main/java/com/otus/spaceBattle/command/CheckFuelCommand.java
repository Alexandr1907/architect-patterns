package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.FuelBurnable;
import com.otus.spaceBattle.exception.CommandException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckFuelCommand implements Command {

    @NonNull
    private final FuelBurnable fuelBurnable;

    @Override
    public void execute() {
        int fuelLevel = fuelBurnable.getFuelLevel();
        int consumeSpeed = fuelBurnable.getConsumeSpeed();

        if (fuelLevel < consumeSpeed) {
            throw new CommandException();
        }

    }
}
