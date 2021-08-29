package com.play.algorithms_practice.trees;

import com.play.data_structures.tree.TreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class MirrorTreeTest {

    /**
     * Explanation: The tree is
     *       10               10
     *     /    \  (mirror) /    \
     *    20    30    =>   30    20
     *   /  \                   /   \
     *  40  60                 60   40

     */
    @Test
    void shouldCreateMirrorImageOfTree() {

        TreeNode<Integer> root  = new TreeNode<>(10);
        root.addLeft(20);
        root.addRight(30);
        root.getLeft().addLeft(40);
        root.getLeft().addRight(60);

        MirrorTree testCls = new MirrorTree();

        testCls.mirror(root);

        assertThat(root.getData()).isEqualTo(10);
        assertThat(root.getLeft().getData()).isEqualTo(30);
        assertThat(root.getRight().getData()).isEqualTo(20);

        assertNull(root.getLeft().getLeft());
        assertThat(root.getRight().getLeft().getData()).isEqualTo(60);
        assertThat(root.getRight().getRight().getData()).isEqualTo(40);


        assertNull(root.getRight().getLeft().getLeft());
        assertNull(root.getRight().getLeft().getRight());
        assertNull(root.getRight().getRight().getLeft());
        assertNull(root.getRight().getRight().getRight());
    }

}