package com.algorithms.arrays_strings;

import java.util.Arrays;
import java.util.HashMap;

/*
 Arrays & Strings 1.2

 Given two strings,write a method to decide if one is a permutation of the other.

 Assumptions
   - Case Sensitive. e.g "ab" is not a permutation of "bA"
   - ASCII charaters only
 */
public class CheckPermutations {

    /*
    Sort both strings
    Then do comparison of arrays

    TimeComplexity = two times sort ~ O(NlogN), comparison ~ O(N)
    which means = O(NlogN)

    Space Complexity = O(N) - requires as much space as input size.
     */
    public boolean checkPermutations(String input1, String input2) {

        if(input1 == null || input2 == null) return false;

        if(input1.length() != input2.length()) return false;

        char[] charArray1 = input1.toCharArray();
        Arrays.sort(charArray1);

        char[] charArray2 = input2.toCharArray();
        Arrays.sort(charArray2);

        boolean isPermutationOfEachOther = true;
        for (int i = 0; i < charArray1.length; i++) {
            if(charArray1[i] != charArray2[i]) {
                isPermutationOfEachOther = false;
                break;
            }
        }

        return isPermutationOfEachOther;
    }


    /*
    Traverse through first string, and put all characters count in a hash map (incrementing count each time same character is encountered)
    Traverse through second string, and decrement count of characters if already present in map

    In end if hashmap is empty at the end, it means both strings has exact same set of characters.

    Time Complexity: O(n)
    Space Complexity: Extra space for hashmap - O(n)
     */
    public boolean checkPermutationsUsingExtraSpace(String input1, String input2) {

        if(input1 == null || input2 == null) return false;

        if(input1.length() != input2.length()) return false;

        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < input1.length(); i++) {
            charMap.compute(input1.charAt(i), (k, v) -> v == null? 1 : v + 1);
        }

        for (int i = 0; i < input2.length(); i++) {
            char currentCharacter = input2.charAt(i);
            int currVal = charMap.getOrDefault(currentCharacter, 0);
            if(currVal == 0) {
                break; //not found
            } else if( currVal == 1) {
                charMap.remove(currentCharacter);
            } else {
                charMap.put(currentCharacter, currVal - 1);
            }
        }

        return charMap.isEmpty();
    }

}
