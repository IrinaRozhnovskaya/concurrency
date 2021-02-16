package com.bank;

import lombok.AllArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

import static com.bank.Bank.*;

@AllArgsConstructor
public class Transaction implements Runnable {

    private final Bank bank;
    private final int sourceAccount;

    @Override
    public void run() {
        while (true) {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            int destinationAccount = rand.nextInt(MAX_ACCOUNT);
            if (destinationAccount == sourceAccount) return;
            int amount = rand.nextInt(MAX_AMOUNT);
            if (amount == 0) return;
            bank.transfer(sourceAccount, destinationAccount, amount);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.getLocalizedMessage();
                Thread.currentThread().interrupt();
            }
        }
    }
}