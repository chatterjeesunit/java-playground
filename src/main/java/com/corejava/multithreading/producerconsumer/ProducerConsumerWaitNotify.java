package com.corejava.multithreading.producerconsumer;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by sunitc on 11/17/17.
 */
public class ProducerConsumerWaitNotify {

    public static void main(String[] args) {
        ItemQueue itemQueue = new ItemQueue();

        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(itemQueue), "Consumer " + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(itemQueue), "Producer " + i).start();
        }
    }

    static class ItemQueue{
        public Queue<Integer> queue = new LinkedList<Integer>();
        private int MAXSIZE = 5;
        public boolean isFull() {
            return queue.size() >= MAXSIZE;
        }
        public boolean isEmpty() {
            return queue.isEmpty();
        }
        public void add(int value) {
            queue.add(value);
        }
        public int remove() {
            return queue.remove();
        }
        @Override
        public String toString() {
            return queue.toString();
        }
    }

    @AllArgsConstructor
    static class Producer implements Runnable {
        private ItemQueue itemQueue;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : Started");

            //Get the random value to put in the queue
            int value = new Random().nextInt(100);

            //Time to put value in the queue
            synchronized (itemQueue) {
                //If queue is full, wait in loop
                //Use while instead of if condition to handle spurious wake up calls
                while(itemQueue.isFull()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": Waiting");
                        itemQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                itemQueue.add(value);
                itemQueue.notifyAll();
                System.out.println(Thread.currentThread().getName() + ": " + itemQueue);
            }
        }
    }

    @AllArgsConstructor
    static class Consumer implements Runnable {
        private ItemQueue itemQueue;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : Started");

            //Time to put value in the queue
            synchronized (itemQueue) {
                //If queue is empty, wait in loop
                //Use while instead of if condition to handle spurious wake up calls
                while(itemQueue.isEmpty()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": Waiting");
                        itemQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                itemQueue.remove();
                itemQueue.notifyAll();
                System.out.println(Thread.currentThread().getName() + ": " + itemQueue);
            }
        }
    }
}
