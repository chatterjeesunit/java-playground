package com.play.ds_algo.practice.arrays_strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringRotationTest {

    StringRotation testCls = new StringRotation();


    @Test
    void shouldReturnTrueIfOneStringIsRotationOfOther() {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";

        boolean result = testCls.solve(s1, s2);

        assertThat(result).isTrue();
    }

}