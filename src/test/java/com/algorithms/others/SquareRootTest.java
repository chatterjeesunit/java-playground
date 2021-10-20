package com.algorithms.others;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SquareRootTest {

    SquareRoot testCls = new SquareRoot();

    @Test
    void shouldReturnMinusOneForSqrtOf50(){
        assertThat(testCls.squareroot(50)).isEqualTo(-1);
    }


    @Test
    void shouldReturn25AsSqrtOf625(){
        assertThat(testCls.squareroot(625)).isEqualTo(25);
    }

    @Test
    void shouldReturnCorrectSqrtRootOfVeryBigNumber(){
        assertThat(testCls.squareroot(176013289)).isEqualTo(13267);
    }
}