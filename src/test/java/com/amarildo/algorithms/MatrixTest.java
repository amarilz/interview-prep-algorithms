package com.amarildo.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {

    @Nested
    @DisplayName("matrix dfs")
    class MatrixDfs {

        @Test
        void testSingleIsland() {
            int[][] grid = {
                    {1, 1, 0, 0},
                    {1, 1, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            };
            assertEquals(1, Matrix.countIslandsDFS(grid));
        }

        @Test
        void testMultipleIslands() {
            int[][] grid = {
                    {1, 0, 0, 1},
                    {0, 0, 0, 0},
                    {1, 0, 1, 1},
                    {0, 0, 0, 0}
            };
            assertEquals(4, Matrix.countIslandsDFS(grid));
        }

        @Test
        void testAllWater() {
            int[][] grid = {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            };
            assertEquals(0, Matrix.countIslandsDFS(grid));
        }

        @Test
        void testAllLand() {
            int[][] grid = {
                    {1, 1},
                    {1, 1}
            };
            assertEquals(1, Matrix.countIslandsDFS(grid));
        }

        @Test
        void testDisconnectedSingleCells() {
            int[][] grid = {
                    {1, 0, 1},
                    {0, 1, 0},
                    {1, 0, 1}
            };
            assertEquals(5, Matrix.countIslandsDFS(grid));
        }
    }
}
