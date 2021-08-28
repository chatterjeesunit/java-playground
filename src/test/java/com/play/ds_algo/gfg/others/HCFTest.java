package com.play.ds_algo.gfg.others;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class HCFTest {

    HCF testClass = new HCF();

    @ParameterizedTest(name="Calculate HCF With Recursion: HCF of {0} and {1} is {2}")
    @CsvSource(value = {
            "3,5,1",
            "4,12,4",
            "6,15,3",
            "45,60,15",
            "850,680,170",
            "987655, 2325, 5"
    })
    public void shouldCalculateHCFOfNumber(long n, long m, long expectedResult) {
        assertThat(testClass.solveRecursively(n, m)).isEqualTo(expectedResult);
    }


    @ParameterizedTest(name="Calculate HCF Without Recursion: HCF of {0} and {1} is {2}")
    @CsvSource(value = {
            "3,5,1",
            "4,12,4",
            "6,15,3",
            "45,60,15",
            "850,680,170",
            "987655, 2325, 5"
    })
    public void shouldCalculateHCFOfNumberWithoutRecursion(long n, long m, long expectedResult) {
        assertThat(testClass.solveWithoutRecursion(n, m)).isEqualTo(expectedResult);
    }

}