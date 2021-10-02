package com.corejava.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadLocal {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        ThreadLocal<Integer> threadId = new ThreadLocal<Integer>();

        for (int i = 0; i < 10 ; i++) {
            service.execute(new TaskA(i, threadId));
        }

        System.out.println("All tasks scheduled...");
        service.shutdown();
    }
}



class TaskA implements Runnable {

    int i;
    private ThreadLocal<Integer> threadId;
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public TaskA(int i, ThreadLocal<Integer> threadId) {
        this.i = i;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() + " : Task - " + i + " : Started.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer currI = threadId.get();
        if(currI == null) {
            System.out.println("\t" + Thread.currentThread().getId() + " : Task - " + i + ", No integer in thread local... setting it to " + i);
            threadId.set(i);

        } else {
            System.out.println("\t" + Thread.currentThread().getId() + " : Task - " + i + ", Found Integer " + currI);
        }

        Integer currI2 = threadLocal.get();
        if(currI2 == null) {
            System.out.println("\t" + Thread.currentThread().getId() + " : Task - " + i + ", No integer in thread local... setting it to " + i);
            threadLocal.set(i);

        } else {
            System.out.println("\t" + Thread.currentThread().getId() + " : Task - " + i + ", Found Integer " + currI2);
        }



        System.out.println(Thread.currentThread().getId() + " : Task - " + i + " : Finished.");
    }
}