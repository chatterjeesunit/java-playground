package com.algorithms.arrays_strings;


/**
 *
 * https://practice.geeksforgeeks.org/contest/interview-series-samsung/problems/#
 *
 *
 * Given a String `str`, find the minimum characters to be added to the front of the string to make it a palindrome.
 *
 * Example 1
 * Input: "ABCD"
 * Output: 3
 * Explanation: The resultant string after adding 3 characters is DCBABCD.
 *
 * Example 2
 * Input: "ABA"
 * Output: 0
 * Explanation: The given string is already a palindrome.
 *
 * Example 3
 * Input: "ABBD"
 * Output: 3
 * Explanation: The resultant string after adding 3 characters is DBBABBD.
 *
 *
 * Example 4
 * Input: "ABBAD"
 * Output: 1
 * Explanation: The resultant string after adding 3 characters is DABBAD.
 */
public class AddMinimumCharsForPalindrome {

    public int solve(String str) {
        char[] charArr = str.toCharArray();
        final int length = charArr.length;
        boolean isPalindrome = isPalindrome(charArr, length - 1);
        if(!isPalindrome) {
            for(int i = length - 2; i >= 0 ; i--) {
                if(isPalindrome(charArr, i)) {
                    return length - i - 1;
                }
            }
        }
        return 0;
    }


    private static boolean isPalindrome(char[] charArr, int endIndex) {
        for(int i=0, j=endIndex; i<=endIndex && j >= 0 && i < j; i++, j--) {
            if(charArr[i] != charArr[j]) {
                return false;
            }
        }
        return true;
    }
}
