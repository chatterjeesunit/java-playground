package com.corejava.multithreading.synchronization;

public class SynchronizationBlockCustomMonitor {

    public static void main(String[] args) {

        Object monitor = new Object();

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + ": " + "Start Execution");
            try {
                System.out.println(Thread.currentThread().getName() + ": " + "Sleeping for 5 sec");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (monitor) {
                System.out.println(Thread.currentThread().getName() + ": " + "Inside synchronized block");
            }

            System.out.println(Thread.currentThread().getName() + ": " + "Finish Execution");
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
