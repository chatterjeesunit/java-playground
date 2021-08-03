package com.play.ds_algo.practice.arrays_strings;

/*
 Modification of Arrays & Strings 1.6

Implement a method to perform basic string compression using the counts of repeated characters.
For example, the string aabcccccaaa would become a2blc5a3.
If the "compressed" string would not become smaller than the original string, your method should return
the original string. You can assume the string has only uppercase and lowercase letters (a - z).

Examples
    aabcccccaaa     -> a2b1c5a3
    abcdefghhe        -> abcdefghhe
    abbbbbbbcde        -> a1b7c1d1e1
    aabb   ->  aabb
    abbbbbbbbbbbbbbcccccccccccccccccde ->   a1b14c17d1e1

 Assumptions
   - You can assume the string has only uppercase and lowercase letters (a - z).
   - Compression is case sensitive
   - Character can be repeated more than 9 times. e.g a17 is also a valid compressed value.
 */
public class StringCompression {


    /**
     * Loop through the string
     * compare the current character with next character
     *  - if same -> keep incrementing indexes and keep a count of repeated chars
     *  - if different or reached end of string - then append the char and count into a new compressed array,
     *      if array size does not exceeds length of string
     *
     *  - if compression done successfully and length of compressed string less than length of original string
     *    - return compressed string
     *    - else return original string
     *
     * Time Complexity - O(n) - loops through entire string once.
     * Space Complexity - O(n) - needs extra array to store data
     *
     */
    public String solve(String input) {

        final int strLength = input.length();

        if(strLength <= 2) {
            return input;
        }

        // The reason being the compressed array can never be bigger in size than original string
        char[] compressedArray = new char[strLength];

        boolean compressionFailed = false;
        int currentCompressionCount = 1;
        int compressedArrayIndex = 0;
        int curr = 0;
        int next = curr + 1;



        while(next <= strLength) {
            boolean endOfString = next == strLength;
            boolean saveCompression = false;

            if(!endOfString) {
                char currentChar = input.charAt(curr);
                char nextChar = input.charAt(next);

                if(nextChar == currentChar) {
                    currentCompressionCount += 1;
                } else {
                    saveCompression = true;
                }
            }

            if(endOfString || saveCompression) {
                char[] countsOfChar = convertIntegerToCharArray(currentCompressionCount);

                int totalCharsToWrite = countsOfChar.length + 1;

                //if writing this compression exceeds the max length of string, then break the process
                if(compressedArrayIndex + totalCharsToWrite >= strLength) {
                    compressionFailed = true;
                    break;
                }

                //write the compression
                char currentChar = input.charAt(curr);
                compressedArrayIndex = appendToCompressedArray(compressedArray, compressedArrayIndex, countsOfChar, currentChar);


                //reset
                currentCompressionCount = 1;
            }

            //move the indexes
            curr++;
            next++;
        }

        if(!compressionFailed && compressedArrayIndex < strLength) {
            return String.copyValueOf(compressedArray, 0, compressedArrayIndex);
        }

        return input;
    }

    private int appendToCompressedArray(char[] compressedArray, int compressedArrayIndex, char[] countsOfChar, char currentChar) {
        compressedArray[compressedArrayIndex++] = currentChar;
        for (int i = 0; i < countsOfChar.length; i++) {
            compressedArray[compressedArrayIndex++] = countsOfChar[i];
        }
        return compressedArrayIndex;
    }


    private char[] convertIntegerToCharArray(int number) {
        return Integer.toString(number).toCharArray();
    }

}
