package com.corejava.multithreading.executors;

import java.util.Random;
import java.util.concurrent.*;

public class FixedThreadPoolWithBoundedQueueExecutorDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(50);

        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                10,
                0,
                TimeUnit.MILLISECONDS,
                queue);

        Random random = new Random(System.currentTimeMillis());
        for (int i = 1; i <= 200; i++) {
            try {
                Thread.sleep(random.nextLong(20, 50));

                executorService.submit(new MyDummyTask(i));

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (RejectedExecutionException e) {
                System.err.println("Error submitting task #" + i);
                throw e;
            }
        }

        executorService.shutdown();

    }

}
