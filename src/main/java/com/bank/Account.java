package com.bank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

    private int balance;

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) { balance += amount; }
}
