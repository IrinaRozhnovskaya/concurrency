package org.rwlock;

import lombok.AllArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class Reader extends Thread {
    private ReadWriteList<Integer> sharedList;

    @Override
    public void run() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int index = rand.nextInt(sharedList.size());
        Integer number = sharedList.get(index);
        System.out.println(getName() + " -> get: " + number);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e ) { e.printStackTrace(); }

    }
}
