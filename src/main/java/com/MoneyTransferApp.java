package com;

import com.bank.Bank;
import com.bank.Transaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static com.bank.Bank.MAX_ACCOUNT;

public class MoneyTransferApp {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Bank bank = new Bank();

        IntStream.range(0, MAX_ACCOUNT)
                .forEach(account ->
                        service.submit(new Transaction(bank, account)));

        service.shutdown();
        try {
            service.awaitTermination(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.getLocalizedMessage();
            Thread.currentThread().interrupt();
        }
    }
}
