package com.play.algorithms_practice.arrays_strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PalindromePermutationTest {


    PalindromePermutation testCls = new PalindromePermutation();


    @ParameterizedTest(name = "shouldReturnTrueforPalindromeStrings: {0}")
    @ValueSource(strings = {
            "Taco Cat",
            "Taco Cato",
            "Taco Cato !",
            "Taco Cato #! !bb#"
    })
    void shouldReturnTrueforPalindromeStrings(String input) {
        boolean actualResult = testCls.checkPalindrome(input);
        assertThat(actualResult).isTrue();
    }

    @ParameterizedTest(name = "shouldReturnFalseForNonPalindromeStrings: {0}")
    @ValueSource(strings = {
            "Taco Ca",
            "Taco Cat !"
    })
    void shouldReturnFalseForNonPalindromeStrings(String input) {

        boolean actualResult = testCls.checkPalindrome(input);
        assertThat(actualResult).isFalse();
    }

}