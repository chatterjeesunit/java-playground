package com.corejava.multithreading.executors.nonscheduled;

import lombok.AllArgsConstructor;

/**
 * Runnable class implementation that will be used in the other executors related programs
 */
@AllArgsConstructor
public class MyDummyTask implements Runnable {
    private int taskId;

    @Override
    public void run() {
        System.out.println("TaskId: " + taskId + ", Thread "
                + Thread.currentThread().getName() + ": Started");

        //Thread sleep to simulate some long task like an I/O
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\t\tTaskId: " + taskId + ", Thread "
                + Thread.currentThread().getName() + ": Completed");

    }

    @Override
    public String toString() {
        return "Task #" + taskId;
    }
}

