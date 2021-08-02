package com.play.ds_algo.coursera.algorithms_1.week2a.stack;

import java.util.Arrays;

/**
 * Created by sunitc on 5/28/17.
 */
public class Week2aTest {

    private static String[] strTest = {"A", "B", ".", "C", "D", "E", "F", "G",
            "H", "I", ".", "J", ".", ".", ".", "K", "L", ".", ".", "M", ".", ".", ".", "."};

    public static void main(String[] args) {

        Stack<String> as = new AStack<String>(5);
        Stack<String> ls = new LStack<String>();

        processData(ls, as);
    }

    private static void printStack(Stack<String> stack) {
        StringBuffer stringBuffer = new StringBuffer("");
        stack.forEach(s -> stringBuffer.append(s + ", "));
        String result = stringBuffer.toString();
        System.out.println("\t[ " + result.substring(0, Math.max(0,result.length()-2)) + " ]");

    }

    private static void processData(Stack<String> s1, Stack<String> s2) {
        printStack(s1);
        printStack(s2);
        Arrays.stream(strTest).forEach(s -> {
            if(s!=null && ".".equals(s)) {
                String pop1 = s1.pop();
                System.out.println("Pop = " + pop1);

                String pop2 = s2.pop();
                System.out.println("Pop = " + pop2);

                printStack(s1);
                printStack(s2);
            }
            else {
                System.out.println("Push = " + s);
                s1.push(s);
                printStack(s1);
                s2.push(s);
                printStack(s2);
            }
        });
    }
}
