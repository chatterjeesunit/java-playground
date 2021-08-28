package com.play.algorithms_practice.string;

import java.util.Scanner;

/**
 * Geek has found a new job for himself, he is now a "password validator", he has to
 * validate thousands of passwords in a day. A password is a string of characters
 * which is valid only when it contains atleast one numerical character, atleast one
 * lowercase english alphabet character and atleast one uppercase english alphabet
 * character.
 * Geek is too smart to do this job manually, so he decided to make a program to
 * automate the process. Help him write a program which when given a string S (the
 * password) prints "YES" if it is a valid password or "NO" otherwise.
 * Input Format:
 * 1. The first line of the input contains a single integer T denoting the number of test
 * cases.
 * 2. The first line of each test case contains a single string S.
 * Output Format:
 * For each test case, print "YES" or "NO" in a new line.
 *
 *
 * Constraints:
 * 1 ≤ T ≤ 10
 * 1 ≤ |S| ≤ 4*10
 * Sum of |S| over all test cases does not exceed 4*10 .
 * Expected Time Complexity: O(|S|)
 * Expected Auxiliary Space: O(1)
 *
 * Sample Input:
 * 2
 * Geek
 * GfG1
 * Sample Output:
 * NO
 * YES
 */
public class ValidPassword {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int numInputs = in.nextInt();
        for (int i = 0; i < numInputs; i++) {
            System.out.println(isValidPassword(in.next())? "YES" : "NO");
        }
        in.close();
    }

    private static boolean isValidPassword(String password) {
        if(password.length() < 3) {
            return false;
        }
        boolean digitFound = false;
        boolean upperCaseLetterFound = false;
        boolean lowerCaseLetterFound = false;

        boolean isValid = digitFound && upperCaseLetterFound && lowerCaseLetterFound;
        for(int i = 0; i < password.length() && !isValid; i++) {
            char currentChar = password.charAt(i);

            digitFound = digitFound || Character.isDigit(currentChar);
            upperCaseLetterFound = upperCaseLetterFound || Character.isUpperCase(currentChar);
            lowerCaseLetterFound = lowerCaseLetterFound || Character.isLowerCase(currentChar);
            isValid = digitFound && upperCaseLetterFound && lowerCaseLetterFound;
        }
        return isValid;
    }
}
