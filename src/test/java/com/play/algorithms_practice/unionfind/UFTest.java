package com.play.algorithms_practice.unionfind;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UFTest {

    @Test
    void testShouldBeAbleToDoUnionAndFind() {

        UF uf = new UF(10);

        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);

        assertThat(uf.isConnected(0, 7)).isFalse();
        assertThat(uf.isConnected(8, 9)).isTrue();

        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);

        assertThat(uf.isConnected(0, 7)).isTrue();

    }
}