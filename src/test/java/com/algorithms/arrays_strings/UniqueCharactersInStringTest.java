package com.algorithms.arrays_strings;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UniqueCharactersInStringTest {

    UniqueCharactersInString testCls = new UniqueCharactersInString();

    @Nested
    class TestUsingMethodOne {
        @ParameterizedTest(name = "shouldReturnTrueForNonUniqueString: {0}")
        @ValueSource(strings = {
                "abc",
                "defaAF",
                "#4%25asESgl87^1-@"
        })
        void shouldReturnTrueForNonUniqueStrings(String input) {
            assertThat(testCls.checkIfStringHasUniqueChars(input)).isTrue();
        }


        @ParameterizedTest(name = "shouldReturnTrueForNonUniqueString: {0}")
        @ValueSource(strings = {
                "abca",
                "defaAFf",
                "#4%25asESgl87^1-@#"
        })
        void shouldReturnFalseForNonUniqueStrings(String input) {
            assertThat(testCls.checkIfStringHasUniqueChars(input)).isFalse();
        }

    }


    @Nested
    class TestUsingMethodTwo {
        @ParameterizedTest(name = "shouldReturnTrueForNonUniqueString: {0}")
        @ValueSource(strings = {
                "abc",
                "defaAF",
                "#4%25asESgl87^1-@"
        })
        void shouldReturnTrueForNonUniqueStrings(String input) {
            assertThat(testCls.checkIfStringHasUniqueCharsUsingSort(input)).isTrue();
        }


        @ParameterizedTest(name = "shouldReturnTrueForNonUniqueString: {0}")
        @ValueSource(strings = {
                "abca",
                "defaAFf",
                "#4%25asESgl87^1-@#"
        })
        void shouldReturnFalseForNonUniqueStrings(String input) {
            assertThat(testCls.checkIfStringHasUniqueCharsUsingSort(input)).isFalse();
        }

    }



}