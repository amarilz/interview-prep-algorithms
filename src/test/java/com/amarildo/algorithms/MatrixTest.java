package com.amarildo.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {

    @Nested
    @DisplayName("matrix bfs")
    class MatrixBfs {

        @Test
        void testSimplePath() {
            int[][] matrix = {
                    {0, 0, 0},
                    {1, 1, 0},
                    {0, 0, 0}
            };
            int result = Matrix.bfsShortestPath(matrix);
            assertEquals(4, result); // Percorso: (0,0) → (0,1) → (0,2) → (1,2) → (2,2)
        }

        @Test
        void testBlockedEnd() {
            int[][] matrix = {
                    {0, 0},
                    {0, 1}
            };
            int result = Matrix.bfsShortestPath(matrix);
            assertEquals(-1, result); // End bloccato
        }

        @Test
        void testNoPathAvailable() {
            int[][] matrix = {
                    {0, 1},
                    {1, 0}
            };
            int result = Matrix.bfsShortestPath(matrix);
            assertEquals(-1, result); // Nessun percorso disponibile
        }

        @Test
        void testLargeMatrix() {
            int[][] matrix = new int[10][10];
            // Tutto 0, quindi percorso disponibile
            int result = Matrix.bfsShortestPath(matrix);
            assertEquals(18, result); // 9 right + 9 down
        }
    }
}
