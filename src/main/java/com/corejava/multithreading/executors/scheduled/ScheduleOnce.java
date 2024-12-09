package com.corejava.multithreading.executors.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.corejava.multithreading.executors.scheduled.TimeUtil.currentTime;

public class ScheduleOnce {

    public static void main(String[] args) throws InterruptedException {


        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        System.out.println(currentTime()
                + ", Thread = " + Thread.currentThread().getName()
                + " : Scheduling task once with delay of 1 minute");

        executorService.schedule(
                new TaskWithRandomSleep(101), //runnable
                1, //initial delay
                TimeUnit.MINUTES);

        executorService.shutdown();

    }

}



