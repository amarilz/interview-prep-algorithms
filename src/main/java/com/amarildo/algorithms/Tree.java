package com.amarildo.algorithms;

import com.amarildo.structure.TreeNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tree {

    public static List<Integer> dfsInorderTraversal(TreeNode root) {
        return null;
    }

    public static List<Integer> bfsTraversal(TreeNode root) {
        return null;
    }

    static class Trie {

        public void insert(String word) {
        }

        public boolean search(String word) {
            return false;
        }

        public boolean startsWith(String prefix) {
            return false;
        }
    }

    static class DisjointSet {

        public DisjointSet(int n) {
        }

        int find(int x) {
            return -1;
        }

        void union(int x, int y) {
        }

        // Controlla se x e y appartengono allo stesso insieme
        public boolean connected(int x, int y) {
            return false;
        }
    }
}
