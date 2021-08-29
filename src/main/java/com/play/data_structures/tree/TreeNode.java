package com.play.data_structures.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class TreeNode<T> {

    T data;
    @Setter
    TreeNode left, right;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode addLeft(T data){
        TreeNode newNode = new TreeNode(data);
        this.setLeft(newNode);
        return newNode;
    }


    public TreeNode addRight(T data){
        TreeNode newNode = new TreeNode(data);
        this.setRight(newNode);
        return newNode;
    }


}
