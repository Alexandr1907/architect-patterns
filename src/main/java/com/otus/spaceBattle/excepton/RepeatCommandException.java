package com.otus.spaceBattle.excepton;

public class RepeatCommandException extends CommandException {
    public RepeatCommandException(String message) {
        super(message);
    }

    public RepeatCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatCommandException(Throwable cause) {
        super(cause);
    }
}
