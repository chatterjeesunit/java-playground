package com.corejava.multithreading;

/**
 * Created by sunitc on 11/17/17.
 */
public class TestThreading {

    public static void main(String[] args) throws Exception {
        Node n = new Node();
        n.val = 10;

        Thread t = new Thread(new Runnable(){

            @Override
            public void run() {
                synchronized (n) {
                    System.out.println("locked");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    n.val = 20;
                    System.out.println(n.val);
                    System.out.println("de locked");
                }
            }
        });

        t.start();
        
        Thread.sleep(1000);
        n.val = 15;

        System.out.println(n.val);
    }
}


class Node {
    public int val;
}