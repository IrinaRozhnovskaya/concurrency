package com.bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;

public class Bank {

    public static final int MAX_ACCOUNT = 10;
    public static final int MAX_AMOUNT = 10;
    public static final int INITIAL_BALANCE = 100;

    private Account[] accounts = new Account[MAX_ACCOUNT];
    private Lock bankLock;
    private Condition availableFound;

    public Bank() {
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(INITIAL_BALANCE);
        }
        bankLock = new ReentrantLock();
        availableFound = bankLock.newCondition();
    }

    public void transfer(int source, int destination, int amount) {
        bankLock.lock();

        try {
            while (accounts[source].getBalance() < amount) {
                availableFound.await();
            }
                accounts[source].withdraw(amount);
                accounts[destination].deposit(amount);

                System.out.printf(format("%s transferred %d from account %s to account %s. Total balance: %d%n",
                        Thread.currentThread().getName(), amount, source, destination, getTotalBalance()));

                availableFound.signalAll();

        } catch (InterruptedException e) {
            e.getLocalizedMessage();
            Thread.currentThread().interrupt();
        } finally {
            bankLock.unlock();
        }
    }

    private int getTotalBalance() {
        bankLock.lock();

        try {
            int total = 0;
            for (Account account : accounts) {
                total += account.getBalance();
            }
            return total;
        } finally {
            bankLock.unlock();
        }
    }
}
