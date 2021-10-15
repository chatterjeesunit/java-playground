package com.corejava.multithreading.creation;

import java.util.List;

public class ThreadRunVsStart {

    public static void main(String[] args) {

        Thread.currentThread().setName("Main Thread");

        Runnable runnable = () -> System.out.println("Executing inside Thread : " + Thread.currentThread().getName());

        Thread thread = new Thread(runnable, "T1");

        System.out.println("Thread RUN");
        thread.run();

        System.out.println("Thread START");
        thread.start();
    }
}