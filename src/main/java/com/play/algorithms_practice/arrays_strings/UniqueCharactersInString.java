package com.play.algorithms_practice.arrays_strings;

import java.util.Arrays;

/*
 Arrays & Strings 1.1

 Implement an algorithm to determine if a string has all unique characters.
 What if you cannot use additional data structures?


 Assumptions
   - Case Sensitive
   - ASCII charaters only
 */
public class UniqueCharactersInString {


    /**
     * Solution #1
     * Time Complexity - O(1) - since no matter the size of string, it will check upto max 128 char before
     *  the character repeats and method returns non-unique
     * Space Complexity - O(1) - since array will never contain more than 128 ascii char keys,
     * no matter the size of string
     *
     * can also be implemented via hashmap
     */
    public boolean checkIfStringHasUniqueChars(String input) {
        boolean[] charArray = new boolean[128];

        boolean isUnique = true;

        for (int i = 0; i < input.length(); i++) {
            int characterAscii = input.charAt(i);
            if(charArray[characterAscii]) {
                //Character found more than once.
                //not unique
                isUnique = false;
                break;
            }
            else {
                charArray[characterAscii] = true;
            }
        }

        return isUnique;
    }


    /**
     * Second way to solve this
     *  - sort the string
     *  - traverse the string and break if any consecutive same characters found - which means non unique
     *
     *  Time Complexity - O(n log n) for sorting, and O(n) for traversal ==> O(n log n)
     *  Space complexity - O(n) , as will need extra space to store string as array
     * @param input
     * @return
     */
    public boolean checkIfStringHasUniqueCharsUsingSort(String input) {

        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);

        int prevChar = -1;
        boolean isUnique = true;
        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] == prevChar) {
                isUnique = false;
                break;
            }
            prevChar = charArray[i];
        }
        return isUnique;

    }


}
