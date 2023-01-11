package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.FuelBurnable;
import com.otus.spaceBattle.exception.CommandException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BurnFuelCommand implements Command {

    @NonNull
    private final FuelBurnable fuelBurnable;

    @Override
    public void execute() {
        int nevValue = fuelBurnable.getFuelLevel() - fuelBurnable.getConsumeSpeed();
        if (nevValue < 0) {
            throw new CommandException();
        }
        fuelBurnable.setFuelLevel(nevValue);
    }
}
