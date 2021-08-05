package com.play.ds_algo.practice.ds.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Node<T> {
    private T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}