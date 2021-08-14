package com.play.ds_algo.practice.data_structures.tree;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BSTNodeTest {

    @Nested
    class TreeCreation {
        @Test
        void shouldBeAbleToCreateRootNodeOfTree() {
            BSTNode<Integer> rootNode = new BSTNode<>(5);

            assertThat(rootNode.getData()).isEqualTo(5);
            assertThat(rootNode.getLeftChild()).isNull();
            assertThat(rootNode.getRightChild()).isNull();
        }

        /**
         * List of elements = [5, 10, 15, 12, 4, 9, 1]
         *
         * Expected Tree
         *                  5
         *                /   \
         *              4     10
         *            /      /  \
         *           1      9   15
         *                     /
         *                    12
         */
        @Test
        void shouldBeAbleToCreateTreeUsingListOfElements() {
            BSTNode<Integer> rootNode = new BSTNode<>(List.of(5, 10, 15, 12, 4, 9, 1));

            //root level
            assertThat(rootNode.getData()).isEqualTo(5);

            //level 1
            BSTNode<Integer> node4 = rootNode.getLeftChild();
            BSTNode<Integer> node10 = rootNode.getRightChild();
            assertThat(node4.getData()).isEqualTo(4);
            assertThat(node10.getData()).isEqualTo(10);


            //level 2
            BSTNode<Integer> node1 = node4.getLeftChild();
            BSTNode<Integer> node9 = node10.getLeftChild();
            BSTNode<Integer> node15 = node10.getRightChild();
            assertThat(node1.getData()).isEqualTo(1);
            assertThat(node4.getRightChild()).isNull();
            assertThat(node9.getData()).isEqualTo(9);
            assertThat(node15.getData()).isEqualTo(15);


            //level 3
            BSTNode<Integer> node12 = node15.getLeftChild();
            assertThat(node1.getLeftChild()).isNull();
            assertThat(node1.getRightChild()).isNull();
            assertThat(node9.getLeftChild()).isNull();
            assertThat(node9.getRightChild()).isNull();
            assertThat(node15.getRightChild()).isNull();
            assertThat(node12.getData()).isEqualTo(12);

            //level 4
            assertThat(node12.getLeftChild()).isNull();
            assertThat(node12.getRightChild()).isNull();
        }
    }



    @Nested
    class AddNodeToTree {

        /**
         * Add to tree in order - 5, 10, 15, 12, 4, 9, 1
         *
         * Expected Tree
         *                  5
         *                /   \
         *              4     10
         *            /      /  \
         *           1      9   15
         *                     /
         *                    12
         */
        @Test
        void shouldBeAbleToAddToBinarySearchTree() {
            BSTNode<Integer> rootNode = new BSTNode<>(5);
            rootNode.addNode(10);
            rootNode.addNode(15);
            rootNode.addNode(12);
            rootNode.addNode(4);
            rootNode.addNode(9);
            rootNode.addNode(1);

            //root level
            assertThat(rootNode.getData()).isEqualTo(5);

            //level 1
            BSTNode<Integer> node4 = rootNode.getLeftChild();
            BSTNode<Integer> node10 = rootNode.getRightChild();
            assertThat(node4.getData()).isEqualTo(4);
            assertThat(node10.getData()).isEqualTo(10);


            //level 2
            BSTNode<Integer> node1 = node4.getLeftChild();
            BSTNode<Integer> node9 = node10.getLeftChild();
            BSTNode<Integer> node15 = node10.getRightChild();
            assertThat(node1.getData()).isEqualTo(1);
            assertThat(node4.getRightChild()).isNull();
            assertThat(node9.getData()).isEqualTo(9);
            assertThat(node15.getData()).isEqualTo(15);


            //level 3
            BSTNode<Integer> node12 = node15.getLeftChild();
            assertThat(node1.getLeftChild()).isNull();
            assertThat(node1.getRightChild()).isNull();
            assertThat(node9.getLeftChild()).isNull();
            assertThat(node9.getRightChild()).isNull();
            assertThat(node15.getRightChild()).isNull();
            assertThat(node12.getData()).isEqualTo(12);

            //level 4
            assertThat(node12.getLeftChild()).isNull();
            assertThat(node12.getRightChild()).isNull();
        }
    }



    @Nested
    class DeleteNode {

        /**
         * Original Tree
         *                  5
         *                /   \
         *              4     10
         *            /      /  \
         *           1      9   15
         *                     /
         *                    12
         *
         * Delete Node 1
         *
         * Expected Tree
         *                  5
         *                /   \
         *              4     10
         *                   /  \
         *                  9   15
         *                     /
         *                    12
         */
        @Test
        void shouldBeAbleToDeleteSmallestNodeOfLeftSubTree() {
            BSTNode<Integer> tree = new BSTNode<>(List.of(5, 10, 15, 12, 4, 9, 1));
            BSTNode<Integer> expectedTree = new BSTNode<>(List.of(5, 10, 15, 12, 4, 9));

            BSTNode<Integer> treeAfterDeletion = tree.deleteNode(1);
            assertThat(treeAfterDeletion).isEqualTo(expectedTree);
        }


        /**
         * Original Tree
         *                  5
         *                /   \
         *              4     10
         *            /      /  \
         *           1      9   15
         *                     /
         *                    12
         *
         * Delete Node 9
         *
         * Expected Tree
         *                  5
         *                /   \
         *              4     10
         *            /         \
         *           1          15
         *                     /
         *                    12
         */
        @Test
        void shouldBeAbleToDeleteSmallestNodeOfRightSubtree() {
            BSTNode<Integer> tree = new BSTNode<>(List.of(5, 10, 15, 12, 4, 9, 1));
            BSTNode<Integer> expectedTree = new BSTNode<>(List.of(5, 10, 15, 12, 4, 1));

            BSTNode<Integer> treeAfterDeletion = tree.deleteNode(9);
            assertThat(treeAfterDeletion).isEqualTo(expectedTree);
        }

        /**
         * Original Tree
         *                  5
         *                /   \
         *              4     10
         *            /      /  \
         *           1      9   15
         *                     /
         *                    12
         *
         * Delete Node 15
         *
         * Expected Tree
         *                  5
         *                /   \
         *              4     10
         *            /      /  \
         *           1      9   12
         *
         *
         */
        @Test
        void shouldBeAbleToDeleteLargestNodeOnRightSubTree() {
            BSTNode<Integer> tree = new BSTNode<>(List.of(5, 10, 15, 12, 4, 9, 1));
            BSTNode<Integer> expectedTree = new BSTNode<>(List.of(5, 10, 12, 4, 9, 1));

            BSTNode<Integer> treeAfterDeletion = tree.deleteNode(15);
            assertThat(treeAfterDeletion).isEqualTo(expectedTree);
        }


        /**
         * Original Tree
         *                      25
         *                /            \
         *             20               35
         *            /  \             /   \
         *          10    24         30     45
         *        /   \             /        \
         *      9     16          27          57
         *     /      /             \
         *    5      12              28
         *            \
         *            15
         *
         * Delete Node 10
         *
         * Expected Tree
         *                      25
         *                /            \
         *             20               35
         *            /  \             /   \
         *          12    24         30     45
         *        /   \             /        \
         *      9     16          27          57
         *     /      /             \
         *    5      15              28
         *
         */
        @Test
        void shouldBeAbleToDeleteAMiddleNodeInLeftSubTree() {
            BSTNode<Integer> tree = new BSTNode<>(List.of(25, 20, 35, 10, 24, 30, 45, 9, 16, 27, 57, 5, 12, 28, 15));
            BSTNode<Integer> expectedTree = new BSTNode<>(List.of(25, 20, 35, 12, 24, 30, 45, 9, 16, 27, 57, 5, 15, 28));

            BSTNode<Integer> treeAfterDeletion = tree.deleteNode(10);
            assertThat(treeAfterDeletion).isEqualTo(expectedTree);
        }


        /**
         * Original Tree
         *                      25
         *                /            \
         *             20               35
         *            /  \             /   \
         *          10    24         30     45
         *        /   \             /        \
         *      9     16          27          57
         *     /      /             \
         *    5      12              28
         *            \
         *            15
         *
         * Delete Node 35
         *
         * Expected Tree
         *                      25
         *                /            \
         *             20               45
         *            /  \             /   \
         *          10    24         30     57
         *        /   \             /
         *      9     16          27
         *     /      /             \
         *    5      12              28
         *            \
         *            15
         *
         */
        @Test
        void shouldBeAbleToDeleteAMiddleNodeInRightSubTree() {
            BSTNode<Integer> tree = new BSTNode<>(List.of(25, 20, 35, 10, 24, 30, 45, 9, 16, 27, 57, 5, 12, 28, 15));
            BSTNode<Integer> expectedTree = new BSTNode<>(List.of(25, 20, 45, 10, 24, 30, 57, 9, 16, 27, 5, 12, 28, 15));

            BSTNode<Integer> treeAfterDeletion = tree.deleteNode(35);
            assertThat(treeAfterDeletion).isEqualTo(expectedTree);
        }

        /**
         * Original Tree
         *                      25
         *                /            \
         *             20               35
         *            /  \             /   \
         *          10    24         30     45
         *        /   \             /        \
         *      9     16          27          57
         *     /      /             \
         *    5      12              28
         *            \
         *            15
         *
         * Delete Node 20
         *
         * Expected Tree
         *                      27
         *                /            \
         *             20               35
         *            /  \             /   \
         *          10    24         30     45
         *        /   \             /        \
         *      9     16          28          57
         *     /      /
         *    5      12
         *            \
         *            15
         *
         */
        @Test
        void shouldBeAbleToDeleteRootNodeOfTree() {
            BSTNode<Integer> tree = new BSTNode<>(List.of(25, 20, 35, 10, 24, 30, 45, 9, 16, 27, 57, 5, 12, 28, 15));
            BSTNode<Integer> expectedTree = new BSTNode<>(List.of(27, 20, 35, 10, 24, 30, 45, 9, 16, 28, 57, 5, 12, 15));

            BSTNode<Integer> treeAfterDeletion = tree.deleteNode(25);
            assertThat(treeAfterDeletion).isEqualTo(expectedTree);
        }
    }



    @Nested
    class Equality {

        @Test
        void shouldReturnTrueForIfOneNodeTreeAreEqual() {
            BSTNode<Integer> tree1 = new BSTNode<>(5);
            BSTNode<Integer> tree2 = new BSTNode<>(5);

            boolean areTreeSame = BSTNode.isEqual(tree1, tree2);
            assertThat(areTreeSame).isTrue();

        }

        @Test
        void shouldReturnFalseForIfRootNodeAreNotSame() {
            BSTNode<Integer> tree1 = new BSTNode<>(5);
            BSTNode<Integer> tree2 = new BSTNode<>(4);

            boolean areTreeSame = BSTNode.isEqual(tree1, tree2);

            assertThat(areTreeSame).isFalse();

        }

        @Test
        void shouldReturnFalseForIfTreeAreOfDifferentTypes() {
            BSTNode<Integer> tree1 = new BSTNode<>(Integer.valueOf(5));
            BSTNode<Float> tree2 = new BSTNode<>(Float.valueOf(5));

            boolean areTreeSame = BSTNode.isEqual(tree1, tree2);

            assertThat(areTreeSame).isFalse();

        }


        @Test
        void shouldReturnTrueIfTreesAreActuallySame() {
            BSTNode<Integer> tree1 = new BSTNode<>(5);
            tree1.addNode(10);
            tree1.addNode(15);
            tree1.addNode(12);
            tree1.addNode(4);
            tree1.addNode(9);
            tree1.addNode(1);


            BSTNode<Integer> tree2 = new BSTNode<>(5);
            tree2.addNode(10);
            tree2.addNode(15);
            tree2.addNode(4);
            tree2.addNode(9);
            tree2.addNode(12);
            tree2.addNode(1);

            boolean areTreeSame = BSTNode.isEqual(tree1, tree2);
            assertThat(areTreeSame).isTrue();

        }


        @Test
        void shouldReturnTrueIfTreesAreNotSame() {
            BSTNode<Integer> tree1 = new BSTNode<>(5);
            tree1.addNode(10);
            tree1.addNode(15);
            tree1.addNode(12);
            tree1.addNode(4);
            tree1.addNode(9);
            tree1.addNode(1);


            BSTNode<Integer> tree2 = new BSTNode<>(5);
            tree2.addNode(10);
            tree2.addNode(12);
            tree2.addNode(4);
            tree2.addNode(9);
            tree2.addNode(1);
            tree2.addNode(15);

            boolean areTreeSame = BSTNode.isEqual(tree1, tree2);
            assertThat(areTreeSame).isFalse();

        }

    }

}