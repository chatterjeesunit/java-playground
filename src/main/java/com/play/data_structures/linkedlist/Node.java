package com.play.data_structures.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public int compareTo(Node<T> other) {
        return this.getData().compareTo(other.getData());
    }
}