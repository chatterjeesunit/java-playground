package com.corejava.multithreading.executors.scheduled;

import lombok.AllArgsConstructor;

import java.util.Random;

import static com.corejava.multithreading.executors.scheduled.TimeUtil.currentTime;

@AllArgsConstructor
public class TaskWithRandomSleep implements Runnable {
    private int taskId;

    @Override
    public void run() {
        long sleepTimeInSeconds = new Random(System.currentTimeMillis()).nextLong(1, 6) * 30;

        System.out.println(currentTime()
                + ", Thread = " + Thread.currentThread().getName()
                + ", Task = " + this
                + ", ExecutionTime = " + sleepTimeInSeconds + " seconds"
                + ", Status = Started");

        //Thread sleep to simulate some long task - Random sleep time between 0.5 - 2.5min - task taking variable time
        try {
            Thread.sleep(sleepTimeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(currentTime()
                + ", Thread = " + Thread.currentThread().getName()
                + ", Task = " + this
                + ", Status = Completed");

    }

    @Override
    public String toString() {
        return "Task #" + taskId;
    }

}
