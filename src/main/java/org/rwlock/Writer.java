package org.rwlock;

import lombok.AllArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class Writer extends Thread {

    private ReadWriteList<Integer> sharedList;

    @Override
    public void run() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int number = rand.nextInt(100);
        sharedList.add(number);

        try {
            Thread.sleep(100);
            System.out.println(getName() + " -> put: " + number);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

