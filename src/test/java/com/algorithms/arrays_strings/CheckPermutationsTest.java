package com.algorithms.arrays_strings;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CheckPermutationsTest {

    CheckPermutations testCls = new CheckPermutations();

    @Nested
    class TestUsingMethodOneWithoutExtraSpace {
        @Test
        void shouldReturnTrueIfStringsArePermutationsOfEachOther() {
            String one = "aba";
            String two = "aab";

            boolean actualResult = testCls.checkPermutations(one, two);
            assertThat(actualResult).isTrue();
        }

        @Test
        void shouldReturnTrueIfStringsWithSpecialCharsArePermutationsOfEachOther() {
            String one = "af245$6*ss12#!@";
            String two = "@fa$425s*s#2!16";

            boolean actualResult = testCls.checkPermutations(one, two);
            assertThat(actualResult).isTrue();
        }


        @Test
        void shouldReturnFalseIfStringsAreNotPermutations() {
            String one = "abb";
            String two = "Bab";

            boolean actualResult = testCls.checkPermutations(one, two);
            assertThat(actualResult).isFalse();
        }

    }

    @Nested
    class TestUsingMethodOneWithExtraSpace {
        @Test
        void shouldReturnTrueIfStringsArePermutationsOfEachOther() {
            String one = "aba";
            String two = "aab";

            boolean actualResult = testCls.checkPermutationsUsingExtraSpace(one, two);
            assertThat(actualResult).isTrue();
        }

        @Test
        void shouldReturnTrueIfStringsWithSpecialCharsArePermutationsOfEachOther() {
            String one = "af245$6*ss12#!@";
            String two = "@fa$425s*s#2!16";

            boolean actualResult = testCls.checkPermutationsUsingExtraSpace(one, two);
            assertThat(actualResult).isTrue();
        }


        @Test
        void shouldReturnFalseIfStringsAreNotPermutations() {
            String one = "abb";
            String two = "Bab";

            boolean actualResult = testCls.checkPermutationsUsingExtraSpace(one, two);
            assertThat(actualResult).isFalse();
        }

    }


}