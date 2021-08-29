package com.play.algorithms_practice.arrays_strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AddMinimumCharsForPalindromeTest {

    @ParameterizedTest(name = "{0} should become palindrome after adding {1} characters at start")
    @CsvSource( value = {
            "ABCD,3",
            "ABBD,3",
            "ABA,0",
            "ABBAD,1"}
    )
    void testPalindromeCreation(String str, int expectedResult) {

        AddMinimumCharsForPalindrome testClass = new AddMinimumCharsForPalindrome();

        assertThat(testClass.solve(str)).isEqualTo(expectedResult);
    }

}