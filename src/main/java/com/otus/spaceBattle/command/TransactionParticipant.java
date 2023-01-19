package com.otus.spaceBattle.command;

public interface TransactionParticipant extends Command {
    void rollback();

    boolean isReady();
}
