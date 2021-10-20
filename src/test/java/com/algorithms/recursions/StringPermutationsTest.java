package com.algorithms.recursions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringPermutationsTest {

    StringPermutations testCls = new StringPermutations();

    @Test
    void shouldReturnPermutationsForSingleCharString() {
        String s = "a";

        assertThat(testCls.permute(s))
                .containsExactlyInAnyOrder("a");
    }


    @Test
    void shouldReturnPermutationsForTwoCharString() {
        String s = "ab";

        assertThat(testCls.permute(s))
                .containsExactlyInAnyOrder("ab", "ba");
    }


    @Test
    void shouldReturnPermutationsForThreeCharString() {
        String s = "abc";

        assertThat(testCls.permute(s))
                .containsExactlyInAnyOrder("abc", "acb", "bac", "bca", "cab", "cba");
    }

}