package com.amarildo.algorithms;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.amarildo.algorithms.Graph.Edge;
import static com.amarildo.algorithms.Graph.bfs;
import static com.amarildo.algorithms.Graph.convertMatrixToAdjacencyMap;
import static com.amarildo.algorithms.Graph.convertToAdjacencyMap;
import static com.amarildo.algorithms.Graph.dfsIterative;
import static com.amarildo.algorithms.Graph.dijkstra;
import static com.amarildo.algorithms.Graph.topologicalSortBfsKahn;
import static com.amarildo.algorithms.Graph.topologicalSortDfs;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {

    @Nested
    @DisplayName("list of archs to map")
    class ListOfArchsToMap {

        @Test
        @DisplayName("simple directed graph")
        void testSimpleDirectedGraph() {
            // given
            int[][] edges = {
                    {0, 1},
                    {1, 2},
                    {2, 0}
            };

            Map<Integer, List<Integer>> expected = new HashMap<>();
            expected.put(0, List.of(1));
            expected.put(1, List.of(2));
            expected.put(2, List.of(0));

            // when
            Map<Integer, List<Integer>> actual = convertToAdjacencyMap(edges);

            // then
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("adjacency matrix to map")
    class AdjacencyMatrixToMap {

        @Test
        @DisplayName("simple graph")
        void testSimpleGraph() {
            // given
            int[][] matrix = {
                    {0, 1, 0},
                    {0, 0, 1},
                    {1, 0, 0}
            };

            Map<Integer, List<Integer>> expected = new HashMap<>();
            expected.put(0, List.of(1));
            expected.put(1, List.of(2));
            expected.put(2, List.of(0));

            // when
            Map<Integer, List<Integer>> actual = convertMatrixToAdjacencyMap(matrix);

            // then
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("disconnected nodes")
        void testDisconnectedNodes() {
            // given
            int[][] matrix = {
                    {0, 1, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            };

            Map<Integer, List<Integer>> expected = new HashMap<>();
            expected.put(0, List.of(1));
            expected.put(1, Collections.emptyList());
            expected.put(2, Collections.emptyList());

            // when
            Map<Integer, List<Integer>> actual = convertMatrixToAdjacencyMap(matrix);

            // then
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("bfs")
    class Bfs {

        @Test
        @DisplayName("simple graph")
        void simpleGraph() {
            // given
            Map<Integer, List<Integer>> graph = new HashMap<>();
            graph.put(1, Arrays.asList(2, 3));
            graph.put(2, List.of(4));
            graph.put(3, List.of(5));
            graph.put(4, Collections.emptyList());
            graph.put(5, Collections.emptyList());
            List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

            // when
            List<Integer> actual = bfs(graph, 1);

            // then
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("bfs with disconnected nodes")
        void bfsWithDisconnectedNodes() {
            // given
            Map<Integer, List<Integer>> graph = new HashMap<>();
            graph.put(1, List.of(2));
            graph.put(2, Collections.emptyList());
            graph.put(3, List.of(4));
            graph.put(4, Collections.emptyList());

            List<Integer> expected = Arrays.asList(1, 2);

            // when
            List<Integer> actual = bfs(graph, 1);

            // then
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("dfs")
    class Dfs {

        @NotNull
        private Map<Integer, List<Integer>> buildGraph(Object[][] edges) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (Object[] edge : edges) {
                int from = (int) edge[0];
                int to = (int) edge[1];
                graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
                graph.putIfAbsent(to, new ArrayList<>());
            }
            return graph;
        }

        @Test
        @DisplayName("simple graph")
        void simpleGraph() {
            // given
            Map<Integer, List<Integer>> graph = buildGraph(new Object[][]{
                    {0, 1}, {0, 2}, {1, 3}, {2, 3}
            });

            // when
            List<Integer> expected = Arrays.asList(0, 1, 3, 2);

            // then
            assertEquals(expected, dfsIterative(graph, 0));
        }

        @Test
        @DisplayName("graph with cycle")
        void graphWithCycle() {
            // given
            Map<Integer, List<Integer>> graph = buildGraph(new Object[][]{
                    {0, 1}, {1, 2}, {2, 0}
            });

            // when
            List<Integer> expected = Arrays.asList(0, 1, 2);

            // then
            assertEquals(expected, dfsIterative(graph, 0));
        }

        @Test
        @DisplayName("self loop")
        void testSelfLoop() {
            // given
            Map<Integer, List<Integer>> graph = buildGraph(new Object[][]{
                    {1, 1}
            });

            // when
            List<Integer> expected = List.of(1);

            // then
            assertEquals(expected, dfsIterative(graph, 1));
        }

        @Test
        @DisplayName("disconnected graph")
        void disconnectedGraph() {
            // given
            Map<Integer, List<Integer>> graph = buildGraph(new Object[][]{
                    {0, 1}, {2, 3}
            });

            // when
            List<Integer> expectedFrom0 = Arrays.asList(0, 1);
            List<Integer> expectedFrom2 = Arrays.asList(2, 3);

            // then
            assertEquals(expectedFrom0, dfsIterative(graph, 0));
            assertEquals(expectedFrom2, dfsIterative(graph, 2));
        }
    }

    @Nested
    @DisplayName("topological sort")
    class TopologicalSort {

        private List<List<Integer>> buildAdjacencyList(int V, int[][] edges) {
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
            }
            return adj;
        }

        private boolean isValidTopologicalOrder(List<Integer> order, List<List<Integer>> adj) {
            Map<Integer, Integer> position = new HashMap<>();
            for (int i = 0; i < order.size(); i++) {
                position.put(order.get(i), i);
            }
            for (int u = 0; u < adj.size(); u++) {
                for (int v : adj.get(u)) {
                    if (position.get(u) > position.get(v)) {
                        return false; // invalid: u comes after v
                    }
                }
            }
            return true;
        }

        @Nested
        @DisplayName("dfs")
        class Dfs {

            @Test
            @DisplayName("simple DAG")
            void testSimpleDAG() {
                // given
                int V = 6;
                int[][] edges = {
                        {5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}
                };
                List<List<Integer>> adj = buildAdjacencyList(V, edges);

                // when
                List<Integer> order = topologicalSortDfs(adj, V);

                // then
                assertEquals(V, order.size());
                assertTrue(isValidTopologicalOrder(order, adj));
            }

            @Test
            @DisplayName("graph with isolated node")
            void testGraphWithIsolatedNode() {
                // given
                int V = 4;
                int[][] edges = {
                        {0, 1}, {1, 2}
                        // node 3 is isolated
                };
                List<List<Integer>> adj = buildAdjacencyList(V, edges);

                // when
                List<Integer> order = topologicalSortDfs(adj, V);

                // then
                assertEquals(V, order.size());
                assertTrue(isValidTopologicalOrder(order, adj));
                assertTrue(order.contains(3));
            }

            @Test
            @DisplayName("multiple valid orders")
            void testMultipleValidOrders() {
                // given
                int V = 3;
                int[][] edges = {
                        {0, 1}
                        // 2 is independent
                };
                List<List<Integer>> adj = buildAdjacencyList(V, edges);

                // when
                List<Integer> order = topologicalSortDfs(adj, V);

                // then
                assertEquals(V, order.size());
                assertTrue(isValidTopologicalOrder(order, adj));
            }
        }

        @Nested
        @DisplayName("kahn bfs")
        class KahnBfs {

            @Test
            @DisplayName("simple DAG")
            void testSimpleDAG() throws Exception {
                // given
                int V = 6;
                int[][] edges = {
                        {5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}
                };
                List<List<Integer>> adj = buildAdjacencyList(V, edges);

                // when
                List<Integer> result = topologicalSortBfsKahn(adj, V);

                // then
                assertEquals(V, result.size());
                assertTrue(isValidTopologicalOrder(result, adj));
            }

            @Test
            @DisplayName("multiple valid orders")
            void testMultipleValidOrders() throws Exception {
                // given
                int V = 3;
                int[][] edges = {
                        {0, 1}
                        // 2 è indipendente
                };
                List<List<Integer>> adj = buildAdjacencyList(V, edges);

                // when
                List<Integer> result = topologicalSortBfsKahn(adj, V);

                // then
                assertEquals(V, result.size());
                assertTrue(isValidTopologicalOrder(result, adj));
                assertTrue(result.contains(2));
            }

            @Test
            @DisplayName("isolated nodes")
            void isolatedNodes() throws Exception {
                // given
                int V = 4;
                int[][] edges = {
                        {0, 1}
                        // 2 e 3 isolati
                };
                List<List<Integer>> adj = buildAdjacencyList(V, edges);

                // when
                List<Integer> result = topologicalSortBfsKahn(adj, V);

                // then
                assertEquals(V, result.size());
                assertTrue(isValidTopologicalOrder(result, adj));
                assertTrue(result.contains(2));
                assertTrue(result.contains(3));
            }

            @Test
            @DisplayName("graph with cycle")
            void testGraphWithCycleShouldThrow() {
                // given
                int V = 3;
                int[][] edges = {
                        {0, 1}, {1, 2}, {2, 0} // ciclo
                };
                List<List<Integer>> adj = buildAdjacencyList(V, edges);

                // when
                // then
                assertThrows(Exception.class, () -> topologicalSortBfsKahn(adj, V));
            }
        }
    }

    @Nested
    @DisplayName("shortest path")
    class ShortestPath {

        @Test
        void testSingleNodeGraph() {
            Map<Integer, List<Edge>> graph = new HashMap<>();
            int[] result = dijkstra(graph, 0, 1);
            assertArrayEquals(new int[]{0}, result);
        }

        @Test
        void testSimpleGraph() {
            Map<Integer, List<Edge>> graph = new HashMap<>();
            graph.put(0, Arrays.asList(new Edge(1, 2), new Edge(2, 4)));
            graph.put(1, List.of(new Edge(2, 1)));
            graph.put(2, List.of());

            int[] result = dijkstra(graph, 0, 3);
            assertArrayEquals(new int[]{0, 2, 3}, result);
        }

        @Test
        void testDisconnectedGraph() {
            Map<Integer, List<Edge>> graph = new HashMap<>();
            graph.put(0, List.of(new Edge(1, 3)));
            graph.put(1, List.of());
            graph.put(2, List.of()); // Nodo 2 è disconnesso

            int[] result = dijkstra(graph, 0, 3);
            assertArrayEquals(new int[]{0, 3, Integer.MAX_VALUE}, result);
        }

        @Test
        void testZeroWeightEdges() {
            Map<Integer, List<Edge>> graph = new HashMap<>();
            graph.put(0, Arrays.asList(new Edge(1, 0), new Edge(2, 1)));
            graph.put(1, List.of(new Edge(2, 0)));
            graph.put(2, List.of());

            int[] result = dijkstra(graph, 0, 3);
            assertArrayEquals(new int[]{0, 0, 0}, result);
        }

        @Test
        void testCyclicGraph() {
            Map<Integer, List<Edge>> graph = new HashMap<>();
            graph.put(0, List.of(new Edge(1, 1)));
            graph.put(1, List.of(new Edge(2, 2)));
            graph.put(2, List.of(new Edge(0, 3))); // Ciclo

            int[] result = dijkstra(graph, 0, 3);
            assertArrayEquals(new int[]{0, 1, 3}, result);
        }
    }

    @Nested
    @DisplayName("bipartite")
    class Bipartite {

        @Test
        @DisplayName("Catena semplice (1-2-3-4) ⇒ bipartito")
        void catenaSemplice() {
            int n = 4;
            int[][] edges = {
                    {1, 2}, {2, 3}, {3, 4}
            };
            assertTrue(Graph.isBipartiteGraph(n, edges));
        }

        @Test
        @DisplayName("Ciclo pari (1-2-3-4-1) ⇒ bipartito")
        void cicloPari() {
            int n = 4;
            int[][] edges = {
                    {1, 2}, {2, 3}, {3, 4}, {4, 1}
            };
            assertTrue(Graph.isBipartiteGraph(n, edges));
        }

        @Test
        @DisplayName("Ciclo dispari (triangolo 1-2-3-1) ⇒ NON bipartito")
        void cicloDispariTriangolo() {
            int n = 3;
            int[][] edges = {
                    {1, 2}, {2, 3}, {3, 1}
            };
            assertFalse(Graph.isBipartiteGraph(n, edges));
        }

        @Test
        @DisplayName("Autoanello (2-2) ⇒ NON bipartito")
        void autoAnello() {
            int n = 3;
            int[][] edges = {
                    {2, 2}
            };
            assertFalse(Graph.isBipartiteGraph(n, edges));
        }

        @Test
        @DisplayName("Grafo disconnesso: componente con ciclo dispari ⇒ NON bipartito")
        void grafoDisconnessoConComponenteNonBipartita() {
            int n = 6;
            int[][] edges = {
                    // componente 1: triangolo (non bipartito)
                    {1, 2}, {2, 3}, {3, 1},
                    // componente 2: singolo arco 4-5 (bipartito) e 6 isolato
                    {4, 5}
            };
            assertFalse(Graph.isBipartiteGraph(n, edges));
        }

        @Test
        @DisplayName("Archi duplicati e multiarco ⇒ ancora bipartito")
        void archiDuplicatiEMultipli() {
            int n = 3;
            int[][] edges = {
                    {1, 2}, {2, 1}, // duplicato inverso
                    {2, 3}, {2, 3}  // duplicato identico
            };
            assertTrue(Graph.isBipartiteGraph(n, edges));
        }

        @Test
        @DisplayName("Vertici isolati non influiscono ⇒ bipartito")
        void verticiIsolati() {
            int n = 5;
            int[][] edges = {
                    {2, 3} // 1, 4, 5 isolati
            };
            assertTrue(Graph.isBipartiteGraph(n, edges));
        }

        @Test
        @DisplayName("Ciclo pari con vertici isolati ⇒ bipartito")
        void cicloPariConIsolati() {
            int n = 6;
            int[][] edges = {
                    {1, 2}, {2, 3}, {3, 4}, {4, 1} // 5 e 6 isolati
            };
            assertTrue(Graph.isBipartiteGraph(n, edges));
        }
    }
}
