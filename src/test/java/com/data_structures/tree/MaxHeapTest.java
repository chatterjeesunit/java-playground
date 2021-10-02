package com.data_structures.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaxHeapTest {

    @Test
    void addElementsToMaxHeap() {

        MaxHeap<Integer> heap = new MaxHeap<>(50);
        heap.add(25);
        assertThat(heap.getMax()).isEqualTo(25);

        heap.add(30);
        heap.add(42);
        heap.add(35);
        heap.add(90);
        assertThat(heap.getMax()).isEqualTo(90);

        heap.add(100);
        assertThat(heap.getMax()).isEqualTo(100);

        heap.add(104);
        assertThat(heap.getMax()).isEqualTo(104);

        heap.add(109);
        assertThat(heap.getMax()).isEqualTo(109);

    }


    @Test
    void removeMaxElementFromHeap() {

        MaxHeap<Integer> heap = new MaxHeap<>(50);
        heap.add(25);
        heap.add(30);
        heap.add(42);
        heap.add(35);
        heap.add(90);
        heap.add(100);
        heap.add(104);
        heap.add(109);

        assertThat(heap.getMax()).isEqualTo(109);
        assertThat(heap.getLength()).isEqualTo(8);

        Integer maxElement = heap.removeMax();
        assertThat(heap.getLength()).isEqualTo(7);
        assertThat(maxElement).isEqualTo(109);

        maxElement = heap.removeMax();
        assertThat(heap.getLength()).isEqualTo(6);
        assertThat(maxElement).isEqualTo(104);


        maxElement = heap.removeMax();
        assertThat(heap.getLength()).isEqualTo(5);
        assertThat(maxElement).isEqualTo(100);

        maxElement = heap.removeMax();
        assertThat(heap.getLength()).isEqualTo(4);
        assertThat(maxElement).isEqualTo(90);

        maxElement = heap.removeMax();
        assertThat(heap.getLength()).isEqualTo(3);
        assertThat(maxElement).isEqualTo(42);

    }

}