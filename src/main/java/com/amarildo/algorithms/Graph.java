package com.amarildo.algorithms;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Graph {

    @NotNull
    public static Map<Integer, List<Integer>> convertToAdjacencyMap(int[][] edges) {
        return null;
    }

    @NotNull
    public static Map<Integer, List<Integer>> convertMatrixToAdjacencyMap(int[][] matrix) {
        return null;
    }

    @NotNull
    public static List<Integer> bfs(Map<Integer, List<Integer>> graph, int start) {
        return Collections.emptyList();
    }

    @NotNull
    public static List<Integer> dfsIterative(Map<Integer, List<Integer>> graph, int start) {
        return null;
    }

    @NotNull
    public static List<Integer> topologicalSortDfs(List<List<Integer>> adj, int V) {
        return null;
    }

    @NotNull
    public static List<Integer> topologicalSortKahn(List<List<Integer>> adj, int V) {
        return null;
    }
}
