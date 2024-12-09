package com.corejava.multithreading.executors.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.corejava.multithreading.executors.scheduled.TimeUtil.currentTime;

public class ScheduleWithFixedDelay {
    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        System.out.println(currentTime()
                + ", Thread = " + Thread.currentThread().getName()
                + " : Scheduling task with initial delay of 1 minute, delay of 5 minute");


        executorService.scheduleWithFixedDelay(
                new TaskWithRandomSleep(104),
                1,
                5,
                TimeUnit.MINUTES);

        // below code to ensure executorService.shutdown is not called immediately
        Thread.sleep(22 * 60* 1000);

        executorService.shutdown();

    }
}



