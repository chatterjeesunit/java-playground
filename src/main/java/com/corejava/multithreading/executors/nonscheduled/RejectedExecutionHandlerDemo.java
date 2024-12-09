package com.corejava.multithreading.executors.nonscheduled;

import java.util.Random;
import java.util.concurrent.*;

public class RejectedExecutionHandlerDemo {

    public static void main(String[] args) {
        System.out.println("**************************************************");
        System.out.println("Running with DiscardPolicy");
        System.out.println("**************************************************");
        runCustomThreadPoolExecutor(new ThreadPoolExecutor.DiscardPolicy());


        System.out.println("**************************************************");
        System.out.println("Running with Custom DiscardPolicy that logs discarded tasks");
        System.out.println("**************************************************");
        RejectedExecutionHandler customDiscardPolicy = new ThreadPoolExecutor.DiscardPolicy() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                super.rejectedExecution(r, e);
                System.out.println("Discarding Runnable task - " + r);
            }
        };
        runCustomThreadPoolExecutor(customDiscardPolicy);

        System.out.println("**************************************************");
        System.out.println("Running with DiscardOldestPolicy");
        System.out.println("**************************************************");
        runCustomThreadPoolExecutor(new ThreadPoolExecutor.DiscardOldestPolicy());

        System.out.println("**************************************************");
        System.out.println("Running with Custom DiscardOldestPolicy that logs discarded and executed tasks");
        System.out.println("**************************************************");
        RejectedExecutionHandler customDiscardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                //get the head of the queue that is going to be discarded
                Runnable elementToDiscard = e.getQueue().element();
                super.rejectedExecution(r, e);
                System.out.println("Discarding oldest task - " + elementToDiscard + ", submitting task " + r);
            }
        };
        runCustomThreadPoolExecutor(customDiscardOldestPolicy);


        System.out.println("**************************************************");
        System.out.println("Running with CallerRunsPolicy");
        System.out.println("**************************************************");
        runCustomThreadPoolExecutor(new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("**************************************************");
        System.out.println("Running with AbortPolicy");
        System.out.println("**************************************************");
        runCustomThreadPoolExecutor(new ThreadPoolExecutor.AbortPolicy());

    }


    static void runCustomThreadPoolExecutor(RejectedExecutionHandler rejectedExecutionHandler) {

        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(5);

        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                2,
                0,
                TimeUnit.MILLISECONDS,
                queue,
               rejectedExecutionHandler);

        Random random = new Random(System.currentTimeMillis());
        for (int i = 1; i <= 50; i++) {
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
