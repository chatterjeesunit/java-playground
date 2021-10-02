package com.algorithms.arrays_strings;

/**
 * Modification of Arrays & Strings 1.9
 *
 * Assume you have a method isSubstring, which checks if one word is a substring of another.
 * Given two strings, sl and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring
 * (e.g.,"waterbottle" is a rotation of"erbottlewat").
 *
 *
 */
public class StringRotation {


    /**
     * Since we have to check using substring, so concat one of the strings back to back
     * Then check if other string is substring of this concatenated string
     *
     * Time Complexity - will be complexity of isSubstring
     * Space Complexity - O(n)
     * 
     */
    public boolean solve(String s1, String s2) {

        if(s1.length() != s2.length()) {
            return false;
        }

        return isSubstring(s1, s2.concat(s2));
    }


    private boolean isSubstring(String s1, String s2) {
        return s2.indexOf(s1) != -1;
    }
}
