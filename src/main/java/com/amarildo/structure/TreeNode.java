package com.amarildo.structure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {

    private static final Random random = new Random();

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * Generates a random binary tree.
     *
     * @param maxNodes   The maximum number of nodes in the tree.
     * @param valueRange The range of values for each node (0 to valueRange - 1).
     * @return The root of the generated binary tree.
     */
    public static TreeNode generateRandomTree(int maxNodes, int valueRange) {
        if (maxNodes <= 0) {
            return null; // No tree to generate
        }

        TreeNode root = new TreeNode(random.nextInt(valueRange));
        generateNodes(root, maxNodes - 1, valueRange);
        return root;
    }

    public static TreeNode createBinaryTreeFromArray(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null; // Caso di array vuoto o root nulla
        }

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < values.length) {
            TreeNode current = queue.poll();

            // Aggiunge il figlio sinistro
            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;

            // Aggiunge il figlio destro
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static TreeNode createBSTFromArray(int[] values) {
        if (values == null || values.length == 0) {
            return null; // Array vuoto o nullo, restituisce null
        }

        TreeNode root = new TreeNode(values[0]); // Il primo elemento è la radice

        for (int i = 1; i < values.length; i++) {
            insertIntoBST(root, values[i]);
        }

        return root;
    }

    // Metodo di supporto per inserire un nodo nel BST
    private static void insertIntoBST(TreeNode node, int value) {
        if (value <= node.val) {
            if (node.left == null) {
                node.left = new TreeNode(value);
            } else {
                insertIntoBST(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(value);
            } else {
                insertIntoBST(node.right, value);
            }
        }
    }

    /**
     * Helper method to recursively add nodes to the tree.
     *
     * @param currentNode    The current node to attach children to.
     * @param remainingNodes The number of nodes left to generate.
     * @param valueRange     The range of values for each node.
     * @return The number of nodes still to be added after this call.
     */
    private static int generateNodes(TreeNode currentNode, int remainingNodes, int valueRange) {
        if (remainingNodes <= 0) {
            return 0;
        }

        // Randomly decide if we add a left child
        if (random.nextBoolean()) {
            currentNode.left = new TreeNode(random.nextInt(valueRange));
            remainingNodes = generateNodes(currentNode.left, remainingNodes - 1, valueRange);
        }

        // Randomly decide if we add a right child
        if (remainingNodes > 0 && random.nextBoolean()) {
            currentNode.right = new TreeNode(random.nextInt(valueRange));
            remainingNodes = generateNodes(currentNode.right, remainingNodes - 1, valueRange);
        }

        return remainingNodes;
    }
}
