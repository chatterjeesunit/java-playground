package com.corejava.multithreading.synchronization;

public class MultiMethodSynchronization {

    static class Test {

        public synchronized void methodOne () {
            System.out.println(Thread.currentThread().getName() + ": " + "Inside Method One");
            methodTwo();
        }

        public synchronized void methodTwo () {
            System.out.println(Thread.currentThread().getName() + ": " + "Inside Method Two");
            try {
                System.out.println(Thread.currentThread().getName() + ": " + "Sleeping for 5 sec\n");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Test test = new Test();
        Thread t1 = new Thread(() -> test.methodOne(), "T1");
        Thread t2 = new Thread(() -> test.methodOne(), "T2");
        Thread t3 = new Thread(() -> test.methodTwo(), "T3");

        t1.start();
        t2.start();
        t3.start();
    }

}
