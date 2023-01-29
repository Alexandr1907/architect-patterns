package com.otus.spaceBattle.exceptionHandler;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.command.LogExceptionCommand;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

@Slf4j
public class RepeatMultiTimesExceptionHandler extends MainExceptionHandler{

    Map<Command, Integer> commandExceptionHandleTimes;
    Integer shouldCatchTimes;

    Logger logger;


    RepeatMultiTimesExceptionHandler(Map<
            Pair<Class<? extends Exception>, Class<? extends Command>>,
            CommandExceptionHandler
            > handlerMap, Integer repeatTimes) {
        super(handlerMap);

        shouldCatchTimes = repeatTimes;
        commandExceptionHandleTimes = new HashMap<>();

        System.setProperty("logback.configurationFile", "logbackLogExceptionComandConfig.xml");
        logger = (Logger) LoggerFactory.getLogger(LogExceptionCommand.class);
    }


    public void handle(Exception exception, Command command, Queue<Command> commandQueue) {

        Integer repeatedTimes = commandExceptionHandleTimes.get(command);
        repeatedTimes = repeatedTimes == null ? 0 : repeatedTimes;

        commandExceptionHandleTimes.put(command, repeatedTimes + 1);

        if (repeatedTimes < shouldCatchTimes) {
            commandQueue.add(command);
        } else {
            logger.error(format("Команду %s не удалось выолнить %s раз(а)", command, shouldCatchTimes));
            super.handle(exception, command, commandQueue);
        }
    }
}
