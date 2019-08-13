package com.pojo.multithreading.nov2017;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by sunitc on 11/17/17.
 */
public class ProducerConsumerWaitNotify {

    public static void main(String[] args) {
        ItemQueue lock = new ItemQueue();

        for (int i = 0; i <= 4; i++) {
            new Thread(new Consumer(lock), "Consumer " + i).start();
        }

        for (int i = 0; i <= 2; i++) {
            new Thread(new Producer(lock), "Producer " + i).start();
        }

    }

    public static class ItemQueue{
        public Queue<Integer> queue = new LinkedList<Integer>();
        public int MAXSIZE = 10;
    }

    public static class Producer implements Runnable {

        private ItemQueue lock;

        public Producer(ItemQueue queue) {
            this.lock = queue;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : started...");
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while(lock.queue.size() >= lock.MAXSIZE) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " : waiting...");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.queue.add(new Random(System.currentTimeMillis()).nextInt());
                    System.out.println(Thread.currentThread().getName() + " : adding item...");
                    System.out.println(lock.queue);
                    lock.notify();
                }
            }

        }
    }

    public static class Consumer implements Runnable {
        private ItemQueue lock;

        public Consumer(ItemQueue lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : started...");
            while(true) {
                synchronized (lock) {
                    while (lock.queue.isEmpty()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " : waiting...");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.queue.remove();
                    System.out.println(Thread.currentThread().getName() + " : removing item...");
                    System.out.println(lock.queue);
                    lock.notify();
                }
            }
        }
    }
}
