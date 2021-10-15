package com.corejava.multithreading.other;

import java.util.Random;

public class ThreadLocalDemo {

    public static void main(String[] args) {

        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();

        //Set thread local to 100 in the main thread
        integerThreadLocal.set(100);
        System.out.println(Thread.currentThread().getName() + ": Value = " + integerThreadLocal.get());

        Runnable runnable = () -> {
            //update thread local to some random integer
            int newValue = Math.abs(new Random().nextInt(100000));
            System.out.println(Thread.currentThread().getName() + ": " + "updating thread local value to " + newValue);
            integerThreadLocal.set(newValue);

            System.out.println(Thread.currentThread().getName() + ": Value = " + integerThreadLocal.get());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ": Value = " + integerThreadLocal.get());
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ": Value = " + integerThreadLocal.get());
    }
}



