package com.algorithms.arrays_strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringCompressionTest {


    StringCompression testCls = new StringCompression();

    @Test
    void shouldCompressString() {
        String input = "aabcccccaaa";
        String expectedOutput = "a2b1c5a3";

        String actualOutput = testCls.solve(input);
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }


    @Test
    void shouldCompressStringEvenIfOnlyOneCharIsRepeated() {
        String input = "abbbbbbbcde";
        String expectedOutput = "a1b7c1d1e1";

        String actualOutput = testCls.solve(input);
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }


    @Test
    void shouldCompressionStringEvenIfRepeationsIsMoreThan9() {
        String input = "abbbbbbbbbbbbbbcccccccccccccccccde";
        String expectedOutput = "a1b14c17d1e1";

        String actualOutput = testCls.solve(input);
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }


    @ParameterizedTest(name = "should return original strings when input = {0}")
    @ValueSource(strings = {
            "abcdefghhe",
            "a",
            "aab",
            "abbb"
    })
    void shouldReturnOriginalStrings(String input) {
        String actualOutput = testCls.solve(input);
        assertThat(actualOutput).isEqualTo(input);
    }

}