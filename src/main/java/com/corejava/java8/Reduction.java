package com.corejava.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunitc on 5/30/17.
 */
public class Reduction {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        list.add("Sunit");
        list.add("Rohan");
        list.add("Mukul");
        list.add("Avanti");
        list.add("Pranali");
        list.add("Anushri");


        String result1 = list.stream().reduce("", (s1, s2) -> {
                            if(s1.length() == 0) return s1.concat(s2);
                            return s1.concat(",").concat(s2);
                        });
        System.out.println(result1);

        int result2 = list.stream().reduce(0, (integer, str) -> integer + str.length(), (int1, int2) -> int1 + int2 );

        System.out.println(result2);


    }
}
