package com.corejava.multithreading.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Showcase difference between executors and executorService
 */
public class SingleThreadExecutorDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //execute a single task in different thread
        executorService.submit(new MyDummyTask(100));


        //Execute a sequence of tasks, but in sequence one by one
        for (int i = 0; i < 5; i++) {
            executorService.submit(new MyDummyTask(i));
        }

        executorService.shutdown();


    }

}