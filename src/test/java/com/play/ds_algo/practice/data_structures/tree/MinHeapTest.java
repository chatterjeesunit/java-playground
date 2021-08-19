package com.play.ds_algo.practice.data_structures.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MinHeapTest {

    @Test
    void addElementsToMinHeap() {

        MinHeap<Integer> heap = new MinHeap<>(50);
        heap.add(25);
        assertThat(heap.getMin()).isEqualTo(25);

        heap.add(30);
        heap.add(42);
        heap.add(35);
        heap.add(90);
        assertThat(heap.getMin()).isEqualTo(25);

        heap.add(24);
        assertThat(heap.getMin()).isEqualTo(24);

        heap.add(14);
        assertThat(heap.getMin()).isEqualTo(14);

        heap.add(9);
        assertThat(heap.getMin()).isEqualTo(9);

    }


    @Test
    void removeMinElementFromHeap() {

        MinHeap<Integer> heap = new MinHeap<>(50);
        heap.add(25);
        heap.add(30);
        heap.add(42);
        heap.add(35);
        heap.add(90);
        heap.add(24);
        heap.add(14);
        heap.add(9);
        assertThat(heap.getLength()).isEqualTo(8);

        Integer minElement = heap.removeMin();
        assertThat(heap.getLength()).isEqualTo(7);
        assertThat(minElement).isEqualTo(9);

        minElement = heap.removeMin();
        assertThat(heap.getLength()).isEqualTo(6);
        assertThat(minElement).isEqualTo(14);


        minElement = heap.removeMin();
        assertThat(heap.getLength()).isEqualTo(5);
        assertThat(minElement).isEqualTo(24);

        minElement = heap.removeMin();
        assertThat(heap.getLength()).isEqualTo(4);
        assertThat(minElement).isEqualTo(25);

        minElement = heap.removeMin();
        assertThat(heap.getLength()).isEqualTo(3);
        assertThat(minElement).isEqualTo(30);

    }

}