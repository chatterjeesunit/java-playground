package com.play.algorithms_practice.arrays_strings;

import java.util.HashMap;

/*
 Arrays & Strings 1.4

 Given a string, write a function to check if it is a permutation of a palindrome.
 A palindrome is a word or phrase that is the same forwards and backwards.

 A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
    EXAMPLE
    Input: Tact Coa
    Output: True (permutations: "taco cat", "atco eta", etc.)


 Assumptions
   - None
 */
public class PalindromePermutation {


    /**
     * find count of each character in lower case (except space)
     * find all characters with odd count
     * If at max there is only 1 char with odd count then it is a palindrome permutation
     *
     * Time Complexity - O(n) - one pass of entire string required.
     * Space Complexity - O(1) - map size will be at max the num of characters supported, no matter the size of string.
     *
     */
    public boolean checkPalindrome(String input) {

        HashMap<Character, Integer> charCounts = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char currentCharAsciiVal = Character.toLowerCase(input.charAt(i));
            if(Character.isSpaceChar(currentCharAsciiVal)) {
                continue;
            }

            charCounts.compute(currentCharAsciiVal, (k,v) -> v == null ? 1 : v+1);
        }

        System.out.println(charCounts);

        long numOfOddCharacterCounts = charCounts.values().stream().filter(count -> isOdd(count)).count();


        return numOfOddCharacterCounts <= 1;
    }


    private boolean isOdd(int number){
        return number % 2 != 0;
    }


}
