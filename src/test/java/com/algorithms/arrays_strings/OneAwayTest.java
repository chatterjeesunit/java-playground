package com.algorithms.arrays_strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class OneAwayTest {

    OneAway testCls = new OneAway();

    @ParameterizedTest(name = "shouldReturnTrueForStringsThatAreOneEditAway: String1 = {0}, String2 = {1}")
    @CsvSource(value = {
            "pale,ple",
            "pales,pale",
            "pale,pales",
            "pale,bale"
    })
    void shouldReturnTrueForStringsThatAreOneEditAway(String str1, String str2) {

        boolean actualResult = testCls.solve(str1, str2);

        assertThat(actualResult).isTrue();
    }


    @ParameterizedTest(name = "shouldReturnFalseForStringsThatMoreThanAreOneEditAway: String1 = {0}, String2 = {1}")
    @CsvSource(value = {
            "pale,bake",
            "pale,ble",
            "ble,pale",
            "cde,aacde",
            "cde,cdefg",
            "cde,dec"
    })
    void shouldReturnFalseForStringsThatMoreThanAreOneEditAway(String str1, String str2) {

        boolean actualResult = testCls.solve(str1, str2);

        assertThat(actualResult).isFalse();
    }
}