package com.corejava.multithreading.racecondition;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

public class RaceConditionDemoForReadAndModify {

    // Counter class
    @Getter
    @AllArgsConstructor
    static class Counter {
        private int value;

        public void increment() {
            //sleep for some random time
            try {
                Thread.sleep(Math.abs(new Random().nextInt(50)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value = value + 1;
        }
    }


    public static void main(String[] args) {

        final int numThreads = 50;

        //Shared Resource
        final Counter counter = new Counter(0);

        Runnable runnable = () -> counter.increment();

        Thread[] threads = new Thread[numThreads];
        // Start threads to increment the counter concurrently
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        //This is to ensure that the main thread does not exits after starting these 10 threads
        //Main Thread will wait for all these threads to complete
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //Ideally the value should be 50, as counter is incremented by 50 threads.
        //But due to race condition, it wil be different in each run.
        System.out.println("Value of counter = " + counter.getValue());

    }
}
