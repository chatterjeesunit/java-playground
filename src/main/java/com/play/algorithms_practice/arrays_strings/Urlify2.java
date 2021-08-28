package com.play.algorithms_practice.arrays_strings;

/*
 Slight variation of the Urlify problem
 Arrays & Strings 1.3

 In this we do not know true length of string, and have to replace all space chars
 Also the String does not have additional spaces to hold new string

 EXAMPLE
  Input: "Mr John Smith "
  Output: "Mr%20John%20Smith%20"


 Assumptions
   - None
 */
public class Urlify2 {


    /**
     * Solution #1
     * Time Complexity -
     *      - maxixum # of resizes = less than number of spaces. worst case = O(n), if string has all spaces only
     *      - one iteration of string has to be done
     *      ~ O(n)
     *   One more alternative could be to count the # of spaces up-front, and allocate an array of proper size to avoid multiple re-sizing effort
     *
     *  Space Complexity - O(n) - additional space required to hold the string chars
     *
     */
    public String urlify(String input) {
        int sizeOfOutputArray = input.length();
        char[] outputArray = new char[sizeOfOutputArray];

        int outputCharArrayIndex = 0;

        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            if(Character.isSpaceChar(currentCharacter)) {
                sizeOfOutputArray += 2;
                if(outputCharArrayIndex > outputArray.length - 3) {
                    outputArray = resizeArray(outputArray, sizeOfOutputArray);
                }
                outputArray[outputCharArrayIndex++] = '%';
                outputArray[outputCharArrayIndex++] = '2';
                outputArray[outputCharArrayIndex++] = '0';

            }else {
                if(outputCharArrayIndex >= outputArray.length) {
                    outputArray = resizeArray(outputArray, sizeOfOutputArray);
                }
                outputArray[outputCharArrayIndex++] = currentCharacter;
            }
        }

        return String.copyValueOf(outputArray);
    }


    private char[] resizeArray(char[] inputArray, final int newSize) {
        char[] outputArray = new char[newSize];
        for (int i = 0; i < inputArray.length; i++) {
            outputArray[i] = inputArray[i];
        }
        return outputArray;
    }

}
