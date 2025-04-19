package com.amarildo.algorithms;

import com.amarildo.algorithms.Tree.Trie;
import com.amarildo.structure.TreeNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeTest {

    @Nested
    @DisplayName("recursive dfs")
    class RecursiveDfs {

        @Test
        void testEmptyTree() {
            TreeNode root = TreeNode.createBinaryTreeFromArray(new Integer[]{});
            List<Integer> result = Tree.dfsInorderTraversal(root);
            assertTrue(result.isEmpty());
        }

        @Test
        void testSingleNode() {
            TreeNode root = TreeNode.createBinaryTreeFromArray(new Integer[]{5});
            List<Integer> result = Tree.dfsInorderTraversal(root);
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
            List<Integer> result = Tree.dfsInorderTraversal(root);
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
            List<Integer> result = Tree.dfsInorderTraversal(root);
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
            List<Integer> result = Tree.dfsInorderTraversal(root);
            assertEquals(Arrays.asList(1, 2, 3, 4), result);
        }
    }

    @Nested
    @DisplayName("iterative bfs")
    class IterativeBfs {

        @Test
        void testSingleNodeTree() {
            TreeNode root = new TreeNode(1);
            List<Integer> result = Tree.bfsTraversal(root);
            assertEquals(List.of(1), result);
        }

        @Test
        void testCompleteBinaryTree() {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            root.right.left = new TreeNode(6);
            root.right.right = new TreeNode(7);

            List<Integer> result = Tree.bfsTraversal(root);
            assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), result);
        }

        @Test
        void testLeftSkewedTree() {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.left.left = new TreeNode(3);
            root.left.left.left = new TreeNode(4);

            List<Integer> result = Tree.bfsTraversal(root);
            assertEquals(List.of(1, 2, 3, 4), result);
        }

        @Test
        void testRightSkewedTree() {
            TreeNode root = new TreeNode(1);
            root.right = new TreeNode(2);
            root.right.right = new TreeNode(3);
            root.right.right.right = new TreeNode(4);

            List<Integer> result = Tree.bfsTraversal(root);
            assertEquals(List.of(1, 2, 3, 4), result);
        }
    }

    @Nested
    @DisplayName("trie")
    class TrieTest {

        @Test
        void testInsertAndSearch() {
            Trie trie = new Trie();

            trie.insert("apple");
            assertTrue(trie.search("apple"), "La parola 'apple' dovrebbe essere trovata");
            assertFalse(trie.search("app"), "La parola 'app' non dovrebbe essere trovata (non inserita)");
        }

        @Test
        void testStartsWith() {
            Trie trie = new Trie();

            trie.insert("banana");
            assertTrue(trie.startsWith("ban"), "'ban' è un prefisso valido");
            assertTrue(trie.startsWith("bana"), "'bana' è un prefisso valido");
            assertFalse(trie.startsWith("band"), "'band' non è un prefisso valido");
        }

        @Test
        void testMultipleWords() {
            Trie trie = new Trie();

            trie.insert("car");
            trie.insert("cat");
            trie.insert("cart");

            assertTrue(trie.search("car"));
            assertTrue(trie.search("cat"));
            assertTrue(trie.search("cart"));
            assertFalse(trie.search("ca")); // non è una parola inserita
            assertTrue(trie.startsWith("ca"));
            assertFalse(trie.search("cab")); // non è stata inserita
        }
    }
}