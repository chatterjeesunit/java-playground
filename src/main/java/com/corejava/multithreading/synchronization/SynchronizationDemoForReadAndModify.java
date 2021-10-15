package com.corejava.multithreading.synchronization;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

public class SynchronizationDemoForReadAndModify {

    // Counter class
    @Getter
    @AllArgsConstructor
    static class Counter {
        private int value;

        public void increment() {
            System.out.println(Thread.currentThread().getName() + ": " + "Inside Increment Method");

            //sleep for some random time
            try {
                Thread.sleep(Math.abs(new Random().nextInt(50)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                System.out.println("\n"+Thread.currentThread().getName() + ": " + "Inside synchronized block");
                value = value + 1;
            }

            System.out.println(Thread.currentThread().getName() + ": " + "Outside synchronized block");
        }
    }


    public static void main(String[] args) {

        final int numThreads = 3;

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
