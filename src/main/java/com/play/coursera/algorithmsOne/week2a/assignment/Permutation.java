package com.play.coursera.algorithmsOne.week2a.assignment;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

/**
 * Created by sunitc on 5/29/17.
 */
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rQueue = new RandomizedQueue<String>();

        String[] s = StdIn.readAllStrings();
        for (int i = 0; i < s.length; i++) {
            rQueue.enqueue(s[i]);
        }


        Iterator<String> iterator = rQueue.iterator();
        while (iterator.hasNext() && k > 0) {
            System.out.println(iterator.next());
            k--;
        }
    }
}
