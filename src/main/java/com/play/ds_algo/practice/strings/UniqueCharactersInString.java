package com.play.ds_algo.practice.strings;

/*
 From - Cracking the Coding Interview - Gayle Laakman McDowell
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


}
