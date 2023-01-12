package com.otus.spaceBattle.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LogExceptionCommand implements Command {
    private final Exception exceptionFromCommand;

    @Override
    public void execute() {
        log.error(exceptionFromCommand.getMessage());
    }
}
