package com.otus.spaceBattle.command;

import com.otus.spaceBattle.exception.CommandException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class TransactionCoordinatorCommand implements Command {
    @NonNull
    private final List<TransactionParticipant> queue;

    @Override
    public void execute() {
        boolean transactionReady = queue.stream()
                .allMatch(TransactionParticipant::isReady);
        if (!transactionReady) {
            throw new CommandException();
        }

        Collection<TransactionParticipant> executed = new ArrayList<>();

        for (TransactionParticipant transactionParticipant : queue) {
            try {
                transactionParticipant.execute();
                executed.add(transactionParticipant);
            } catch (Exception e) {
                executed.forEach(TransactionParticipant::rollback);
                transactionParticipant.rollback();
            }
        }
    }
}
