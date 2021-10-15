package com.corejava.multithreading.creation;

import lombok.SneakyThrows;

import java.util.List;

public class ThreadCreation2 {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + ": " + "Start");
            //Sleep for some time to simulate some task being done
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + "Completed");
        };

        List<Thread> threads = List.of(
                new MyDummyThread("MyDummyThread"),
                new Thread(new MyRunnable(), "MyRunnableThread"),
                new Thread(runnable)
        );

        System.out.println(Thread.currentThread().getName() + ": " + "Starting threads" );
        threads.forEach(thread -> thread.start());





        System.out.println(Thread.currentThread().getName() + ": " + "Exiting main method");
    }

    static class MyDummyThread extends Thread {
        public MyDummyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": " + "Start");
            //Sleep for some time to simulate some task being done
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + "Completed");
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": " + "Start");
            //Sleep for some time to simulate some task being done
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + "Completed");
        }
    }
}
