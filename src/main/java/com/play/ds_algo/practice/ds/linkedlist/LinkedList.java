package com.play.ds_algo.practice.ds.linkedlist;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LinkedList<T> {

    Node<T> head;
    Node<T> tail;

    public LinkedList(T data) {
        Node<T> node = new Node<>(data);
        head = node;
        tail = node;
    }

    public LinkedList(List<T> data) {

        Node prevNode = null;
        for (int i = 0; i < data.size(); i++) {
            Node<T> currNode = new Node<>(data.get(i));
            tail = currNode;
            if(prevNode == null) {
                head = currNode;
                prevNode = currNode;
            } else {
                prevNode.next = currNode;
                prevNode = currNode;
            }

        }
    }

    @Override
    public String toString() {
        if(head == null) return null;

        Node<T> node = head;
        StringBuilder builder = new StringBuilder(head.getData().toString());

        while(node.getNext() != null) {
            node = node.getNext();
            builder.append("\t->\t");
            builder.append(node.getData().toString());
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(!(obj instanceof LinkedList)) return false;

        LinkedList<T> other = (LinkedList<T>) obj;

        Node<T> n1 = this.getHead();
        Node<T> n2 = other.getHead();
        while (n1 != null && n2 != null) {
            if(! n1.getData().equals(n2.getData())) {
                return false;
            }
            n1 = n1.getNext();
            n2 = n2.getNext();
        }

        if(n1 != null || n2 != null) {
            return false;
        }

        return true;
    }
}
