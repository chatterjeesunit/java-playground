package com.pojo.corejava;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by sunitc on 10/25/17.
 */
public class ArraysRefOrCopyByValue {

    private static final int n = 3;
    public static void main(String[] args) {

//        Integer[] arr = new Integer[n];
//
//        for(int i=0; i < n; i ++) {
//            arr[i] = i+1;
//        }
//        printIntegerArray(arr, 0);
//        System.out.println("------------------------");
//        modifyIntegerArray(arr, 0);
//        System.out.println("------------------------");
//        printIntegerArray(arr, 0);
//
//
//        System.out.println("*****************************************");

        int[] arr1 = new int[n];
        for(int i=0; i < n; i ++) {
            arr1[i] = i+1;
        }
        printIntArray(arr1, 0);
        System.out.println("------------------------");
        modifyIntArray(arr1, 0);
//        modifyIntArraySum(arr1, 0);
        System.out.println("------------------------");
        printIntArray(arr1, 0);
    }

    private static void modifyIntegerArray(Integer[] arr, int counter) {
        if(counter == n) return;
        arr[counter] = (int)Math.pow(arr[counter], 2);
        printIntegerArray(arr, counter + 1);
        modifyIntegerArray(arr, counter + 1);
        printIntegerArray(arr, counter + 1);
    }

    private static void modifyIntArray(int[] arr, int counter) {
        if(counter == n) return;
        arr[counter] = (int)Math.pow(arr[counter], 2);
        printIntArray(arr, counter + 1);
        modifyIntArray(arr, counter + 1);
//        modifyIntArraySum(arr, counter + 1);
        printIntArray(arr, counter + 1);
    }

    private static void modifyIntArraySum(int[] arr, int counter) {
        if(counter == n) return;
        arr[counter] = arr[counter] * 2;
        printIntArray(arr, counter + 1);
        modifyIntArray(arr, counter + 1);
        printIntArray(arr, counter + 1);
    }

    private static void printIntegerArray(Integer[] arr, int tabCounter) {
        String tabs = "";
        for (int i = 1; i <= tabCounter ; i++) {
            tabs = tabs + "\t";
        }
        System.out.println(tabs + Arrays.stream(arr).map(i -> i.toString()).collect(Collectors.joining(",","[","]")));
    }

    private static void printIntArray(int[] arr, int tabCounter) {
        StringBuffer buffer = new StringBuffer("");

        for (int i = 1; i <= tabCounter ; i++) {
            buffer.append("\t");
        }

        buffer.append("[");
        for (int i = 0; i <n ; i++) {
            buffer.append(arr[i]).append(",");
        }
        buffer.setCharAt(buffer.length()-1, ']');
        System.out.println(buffer.toString());
    }

}
