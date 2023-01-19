package com.otus.spaceBattle.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MoveWithBurnFuel implements Command {
    @NonNull
    private final Move move;
    @NonNull
    private final BurnFuelCommand burnFuelCommand;

    @Override
    public void execute() {
        move.execute();
        burnFuelCommand.execute();
    }
}
