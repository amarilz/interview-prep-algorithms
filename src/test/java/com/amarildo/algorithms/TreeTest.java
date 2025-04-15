package com.amarildo.algorithms;

import com.amarildo.structure.TreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeTest {

    @Nested
    @DisplayName("recursive dfs")
    class RecursiveDfs {

        @Test
        void testEmptyTree() {
            TreeNode root = TreeNode.createBinaryTreeFromArray(new Integer[]{});
            List<Integer> result = Tree.inorderTraversal(root);
            assertTrue(result.isEmpty());
        }

        @Test
        void testSingleNode() {
            TreeNode root = TreeNode.createBinaryTreeFromArray(new Integer[]{5});
            List<Integer> result = Tree.inorderTraversal(root);
            assertEquals(List.of(5), result);
        }

        @Test
        void testCompleteTree() {
            // Albero:       1
            //             /   \
            //            2     3
            //           / \   / \
            //          4  5  6   7
            TreeNode root = TreeNode.createBinaryTreeFromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7});
            List<Integer> result = Tree.inorderTraversal(root);
            assertEquals(Arrays.asList(4, 2, 5, 1, 6, 3, 7), result);
        }

        @Test
        void testTreeWithNulls() {
            // Albero:       1
            //             /   \
            //           null   2
            //                 /
            //                3
            TreeNode root = TreeNode.createBinaryTreeFromArray(new Integer[]{1, null, 2, 3});
            List<Integer> result = Tree.inorderTraversal(root);
            assertEquals(Arrays.asList(1, 3, 2), result);
        }

        @Test
        void testLeftSkewedTree() {
            // Albero:     4
            //            /
            //           3
            //          /
            //         2
            //        /
            //       1
            TreeNode root = TreeNode.createBinaryTreeFromArray(new Integer[]{4, 3, null, 2, null, 1});
            List<Integer> result = Tree.inorderTraversal(root);
            assertEquals(Arrays.asList(1, 2, 3, 4), result);
        }
    }
}