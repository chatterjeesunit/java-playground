package com.play.ds_algo.practice.unionfind;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QFTest {


    @Test
    void testShouldBeAbleToDoUnionAndFind() {

        QF qf = new QF(10);

        qf.union(4, 3);
        qf.union(3, 8);
        qf.union(6, 5);
        qf.union(9, 4);
        qf.union(2, 1);

        assertThat(qf.isConnected(0, 7)).isFalse();
        assertThat(qf.isConnected(8, 9)).isTrue();

        qf.union(5, 0);
        qf.union(7, 2);
        qf.union(6, 1);
        qf.union(1, 0);

        assertThat(qf.isConnected(0, 7)).isTrue();

    }

}