package com.corejava.multithreading.executors.scheduled;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTerminationDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("\n\n**********************************************************************");
        System.out.println("Running first demo of shutting down scheduleExecutor using a countdown latch");
        System.out.println("Task will run 10 times and then scheduler will shutdown");
        System.out.println("**********************************************************************\n");

        stopScheduledExecutorUsingCountDownLatch(10);


        System.out.println("\n\n**********************************************************************");
        System.out.println("Running second demo of shutting down scheduleExecutor using shutdown task");
        System.out.println("Task will run 1 minute and then scheduler will shutdown");
        System.out.println("**********************************************************************\n");

        stopScheduledExecutorUsingCancel();



    }

    static void stopScheduledExecutorUsingCountDownLatch(int numberOfTaskExecutions) throws InterruptedException {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        CountDownLatch countDownLatch = new CountDownLatch(numberOfTaskExecutions);

        Runnable task = () -> {
            System.out.println(TimeUtil.currentTime() + ": " + Thread.currentThread().getName() + " - Running task");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // countdown - to signal that this task completed.
            countDownLatch.countDown();

        };

        // run every 10 seconds
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);

        // wait for the 10 executions to finish
        countDownLatch.await();

        System.out.println("SHUTTING DOWN THE SCHEDULER SERVICE....");
        scheduledExecutorService.shutdown();
    }

    static void stopScheduledExecutorUsingCancel() throws InterruptedException {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        Runnable task = () -> {
            System.out.println(TimeUtil.currentTime() + ": " + Thread.currentThread().getName() + " - Running task");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };

        Runnable shutdownTask = () -> {
            System.out.println(TimeUtil.currentTime() + ": "
                    + Thread.currentThread().getName() + " - SHUTTING DOWN THE SCHEDULER SERVICE....");

            scheduledExecutorService.shutdown();
        };



        // run every 10 seconds
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);


        //schedule the shutdown after 1 minute
        scheduledExecutorService.schedule(shutdownTask, 60, TimeUnit.SECONDS);

    }
}
