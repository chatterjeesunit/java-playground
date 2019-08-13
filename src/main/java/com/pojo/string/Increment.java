package com.pojo.string;

/**
 * Created by sunitc on 6/1/17.
 */
public class Increment {

    public static void main(String[] args) {

        String[] testInput = {"01", "05", "01.01", "05.06", "03.04", "31.01.13"};

        for (String s : testInput) {
            String finalResult = generateNextIncrement(s);
            System.out.println(s + " => " + finalResult);
        }
    }

    private static String generateNextIncrement(String s) {
        int currentStrLength = s.length();
        String currentNumber = currentStrLength > 2 ? s.substring(currentStrLength - 2) : s;
        Integer val = Integer.parseInt(currentNumber);
        val = val + 1;
        String newNumber = String.format("%02d", val);
        return currentStrLength > 2 ? s.substring(0, currentStrLength-2) + newNumber : newNumber;
    }
}
