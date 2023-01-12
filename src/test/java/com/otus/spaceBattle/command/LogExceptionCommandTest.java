package com.otus.spaceBattle.command;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.otus.spaceBattle.TestData;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogExceptionCommandTest {


    @Test
    void execute() {
        Logger logger = (Logger) LoggerFactory.getLogger(LogExceptionCommand.class);

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        logger.addAppender(listAppender);
        LogExceptionCommand command = new LogExceptionCommand(new RuntimeException(TestData.ERROR_MSG));
        command.execute();
        List<ILoggingEvent> logsList = listAppender.list;

        assertEquals(logsList.size(), 1);
        assertEquals(logsList.get(0).getLevel(), Level.ERROR);
        assertEquals(logsList.get(0).getMessage(), TestData.ERROR_MSG);

        logger.detachAndStopAllAppenders();
    }
}