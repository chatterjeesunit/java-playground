package com.play.algorithms_practice.arrays_strings;

/*
 Modification of Arrays & Strings 1.5

 Given two strings, find number of characters that are different
 for different chars in first string, increment the count of different characters
 for different chars in second string, decrement the count of different characters

 EXAMPLE
    pale, ple ->  true
    pales, pale -> true
    pale, bale -> true
    pale, bake -> false
    pale, ble -> false
    cde, aacde -> false
    cde, cdefg -> false
    cde, dec -> false

 Assumptions
   - None
 */
public class OneAway {


    /**
     * First check difference in length - if more than 1 then it is obviously false
     *
     * loop through both strings together - maintaining separate indexes for both strings.
     *  - if char at index is same -> increment both indexes
     *  - if char at index is different
     *      -> keep a track that difference found, and whenever second difference is found then return false
     *      -> If both strings length are same -> increment both indexes
     *      -> else increment index for longer string
     *
     * Time Complexity -
     *    - if string length are more than 1 char different, then complexity is O(1)
     *    - else we need traversal of longer string (which will be 1 char longer than shorter string)
     *    ~ O(n) -> where n, is the length of shorter string.
     * Space Complexity - O(1) - no additional space requirements.
     *
     */
    public boolean solve(String input1, String input2) {

        int lengthOfStr1 = input1.length();
        int lengthOfStr2 = input2.length();

        if(Math.abs(lengthOfStr1 - lengthOfStr2) > 1) {
            return false;
        }

        int index1 = 0;
        int index2 = 0;
        boolean singleDifferenceFound = false;
        while( index1 < lengthOfStr1 && index2 < lengthOfStr2) {
            char firstStrChar = input1.charAt(index1);
            char secondStrChar = input2.charAt(index2);

            if(firstStrChar == secondStrChar) {
                index1++;
                index2++;
            }else {
                if(singleDifferenceFound) {
                    return false;
                }
                singleDifferenceFound = true;

                if(lengthOfStr1 > lengthOfStr2) {
                    index1++;
                }else if(lengthOfStr1 < lengthOfStr2) {
                    index2++;
                }else {
                    index1++;
                    index2++;
                }
            }
        }

        return true;
    }



}
