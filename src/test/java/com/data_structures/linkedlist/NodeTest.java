package com.data_structures.linkedlist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    @Test
    void shouldBeAbleToSortNodesInAscendingOrder() {
        List<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node(5));
        nodes.add(new Node(-5));
        nodes.add(new Node(0));
        nodes.add(new Node(3));

        Collections.sort(nodes);

        Assertions.assertThat(nodes).extracting("data")
                .containsExactly(-5, 0, 3, 5);
    }

    @Test
    void shouldBeAbleToSortNodesInDescendingOrder() {
        List<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node(5));
        nodes.add(new Node(-5));
        nodes.add(new Node(0));
        nodes.add(new Node(3));

        Collections.sort(nodes, Collections.reverseOrder());

        Assertions.assertThat(nodes).extracting("data")
                .containsExactly(5, 3, 0, -5);
    }
}
