package com.corejava.multithreading.synchronization;

public class StaticMethodSynchronization {

    static class Test {

        public static synchronized void methodOne () {
            System.out.println(Thread.currentThread().getName() + ": " + "Inside Method One");
            try {
                System.out.println(Thread.currentThread().getName() + ": " + "Sleeping for 5 sec\n");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Test test1 = new Test();
        Test test2 = new Test();
        Thread t1 = new Thread(() -> test1.methodOne(), "T1");
        Thread t2 = new Thread(() -> test2.methodOne(), "T2");

        t1.start();
        t2.start();
    }

}
