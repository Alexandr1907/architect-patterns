package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.RotationVelocityChangeable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RotationVelocityChange {

    private final @NonNull RotationVelocityChangeable rotationVelocityChangeable;

    public void execute() {
        int angularVelocity = rotationVelocityChangeable.getAngularVelocity();
        int angularVelocityChange = rotationVelocityChangeable.getAngularVelocityChange();
        rotationVelocityChangeable.setAngularVelocity(angularVelocity + angularVelocityChange);
    }
}
