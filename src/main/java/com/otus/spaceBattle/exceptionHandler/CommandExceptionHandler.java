package com.otus.spaceBattle.exceptionHandler;

import com.otus.spaceBattle.command.Command;

import java.util.Queue;

public interface CommandExceptionHandler {

    void handle(Exception exception, Command command, Queue<Command> commandQueue);

}
