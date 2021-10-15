package com.corejava.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadSleep {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            service.execute(new Task(i));
        }

        service.shutdown();

    }

    static class Task implements Runnable {
        int id;

        public Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {

            System.out.println("Starting Task " + id + " : " + Thread.currentThread().getId());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Ending Task " + id + " : " + Thread.currentThread().getId());

        }
    }
}


