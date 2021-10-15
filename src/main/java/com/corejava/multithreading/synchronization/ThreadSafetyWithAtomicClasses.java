package com.corejava.multithreading.synchronization;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafetyWithAtomicClasses {

    public static void main(String[] args) {

        AtomicInteger counter = new AtomicInteger(0);

        Runnable runnable = () -> counter.getAndIncrement();

        Thread[] threads = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Value of counter at the end is : " + counter.get());
    }
}
