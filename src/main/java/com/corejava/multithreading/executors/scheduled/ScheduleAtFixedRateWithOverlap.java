package com.corejava.multithreading.executors.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.corejava.multithreading.executors.scheduled.TimeUtil.currentTime;

public class ScheduleAtFixedRateWithOverlap {
    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        System.out.println(currentTime()
                + ", Thread = " + Thread.currentThread().getName()
                + " : Scheduling task with delay of 1 minute, and interval of 2 minutes");

        executorService.scheduleAtFixedRate(
                new TaskWithRandomSleep(103),
                1,
                2,
                TimeUnit.MINUTES);


        // below code to ensure executorService.shutdown is not called immediately
        Thread.sleep(15 * 60 * 1000);

        System.out.println(currentTime()
                + ", Thread = " + Thread.currentThread().getName()
                + " : Shutting down the executor service.");

        executorService.shutdown();
    }
}
