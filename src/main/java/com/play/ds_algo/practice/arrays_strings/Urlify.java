package com.play.ds_algo.practice.arrays_strings;

/*
 Arrays & Strings 1.3

 Write a method to replace all spaces in a string with '%20'.
 You may assume that the string has sufficient space at the end to hold the additional characters,
 and that you are given the "true" length of the string.
 (Note: If implementing in Java,please use a character array so that you can perform this operation in place.)
 EXAMPLE
  Input: "Mr John Smith ", 13
  Output: "Mr%20John%20Smith"


 Assumptions
   - None
 */
public class Urlify {


    /**
     * Start reading from end.
     * Skip spaces at end
     * keep replacing characters to end of string, and for space - add three chracters at end %,2,0
     *
     * Time Complexity - O(n) - one pass of entire string required.
     * Space Complexity - O(n) - additional space required for char array.
     *
     */
    public String urlify(String input, int trueLengthOfString) {

        if(trueLengthOfString == 0) {
            return "";
        }
        char[] outputArray = input.toCharArray();

        int charIndex = input.length() - 1;

        for (int i = input.length()-1; i >= 0; i--) {
            char currentCharacter = outputArray[i];
            if(Character.isSpaceChar(currentCharacter)) {
                if(i >= trueLengthOfString) {
                    continue;
                }
                outputArray[charIndex--] = '0';
                outputArray[charIndex--] = '2';
                outputArray[charIndex--] = '%';
            }else {
                outputArray[charIndex--] = currentCharacter;
            }
        }

        return String.copyValueOf(outputArray);
    }



}
