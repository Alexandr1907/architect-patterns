package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.Movable;
import com.otus.spaceBattle.dto.Coords;

public class Move implements Command {
  private final Movable movable;

  public Move(Movable movable) {
    this.movable = movable;
  }

  public void execute() {
    Coords position = movable.getPosition();
    Coords velocity = movable.getVelocity();
    if (velocity == null || position == null) {
      throw new RuntimeException();
    }

    movable.setPosition(Coords.append(position, velocity));
  }
}
