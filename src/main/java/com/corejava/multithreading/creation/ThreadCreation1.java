package com.corejava.multithreading.creation;

/**
 * Created by sunitc on 11/17/17.
 */
public class ThreadCreation1 {

    public static class TA extends Thread {
        public TA(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println(currentThread().getName());
        }
    }

    public static class TR implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new TA("TA"+i).start();
            new Thread(new TR(), "TR" + i).start();
        }
    }
}
