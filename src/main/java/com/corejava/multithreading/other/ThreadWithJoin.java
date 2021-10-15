package com.corejava.multithreading.other;

public class ThreadWithJoin {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + ": " + "starting");

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + ": " + "running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + "completed");
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

        System.out.println(Thread.currentThread().getName() + ": " + "completed");
    }
}
