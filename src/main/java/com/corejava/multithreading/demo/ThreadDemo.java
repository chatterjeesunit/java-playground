package com.corejava.multithreading.demo;

import java.util.*;

/**
 * Created by sunitc on 5/13/18.
 */
public class ThreadDemo {

    public static Queue<Integer> queue = new LinkedList<>();
    public static int MAX_QUEUE_SIZE = 10;
    public static Object queueLock = new Object();


    public static void main(String[] args) throws InterruptedException {
       Producer producer = new Producer(queueLock, MAX_QUEUE_SIZE, queue);
       Consumer consumer = new Consumer(queueLock, MAX_QUEUE_SIZE, queue);

       //Producer thread has to put 20 objects into a queue size of 10.
       new Thread(producer, "Producer").start();

       //Consumer thread is an infinite loop, to remove items from queue.
       new Thread(consumer, "Consumer").start();
    }
}


class Producer implements Runnable{
    private Object queueLock;
    private int MAX_QUEUE_SIZE;
    private Queue<Integer> queue;

    public Producer(Object queueLock, int maxSize, Queue<Integer> queue) {
        this.queueLock = queueLock;
        this.MAX_QUEUE_SIZE = maxSize;
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : Started");
        //Produces 20 items in the queue
        for (int i = 0; i < 20; i++) {
            //Get the random value to put in the queue
            int value = new Random().nextInt(100);

            //Time to put value in the queue
            synchronized (queueLock) {
                //If queue is full, wait in loop
                //Use while instead of if condition to handle spurious wake up calls
                while(queue.size() == MAX_QUEUE_SIZE) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": Waiting");
                        queueLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                queue.add(value);
                queueLock.notifyAll();
                System.out.println(Thread.currentThread().getName() + ": Adding to queue, \t\t" + queue);
            }
        }

    }
}

class Consumer implements Runnable {
    private Object queueLock;
    private int MAX_QUEUE_SIZE;
    private Queue<Integer> queue;

    public Consumer(Object queueLock, int maxSize, Queue<Integer> queue) {
        this.queueLock = queueLock;
        this.MAX_QUEUE_SIZE = maxSize;
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : Started");
        while(true) {
            synchronized (queueLock) {
                while (queue.isEmpty()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": Waiting");
                        queueLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.remove();
                queueLock.notifyAll();
                System.out.println(Thread.currentThread().getName() + ": Removing from queue, \t\t" + queue);
            }
        }
    }
}