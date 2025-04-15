package com.amarildo.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicProgrammingTest {

    @Nested
    @DisplayName("minimum maximum path to target")
    class MinimumMaximumPathToTarget {

        @Test
        void testGrid_3x3() {
            int[][] grid = {
                    {1, 3, 1},
                    {1, 5, 1},
                    {4, 2, 1}
            };
            int expected = 7;
            assertEquals(expected, DynamicProgramming.minPathSum(grid));
        }

        @Test
        void testGrid_1x1() {
            int[][] grid = {{5}};
            int expected = 5;
            assertEquals(expected, DynamicProgramming.minPathSum(grid));
        }

        @Test
        void testGrid_1x3() {
            int[][] grid = {{1, 2, 3}};
            int expected = 6; // 1 + 2 + 3
            assertEquals(expected, DynamicProgramming.minPathSum(grid));
        }

        @Test
        void testGrid_3x1() {
            int[][] grid = {
                    {1},
                    {2},
                    {3}
            };
            int expected = 6; // 1 + 2 + 3
            assertEquals(expected, DynamicProgramming.minPathSum(grid));
        }

        @Test
        void testGrid_allZeros() {
            int[][] grid = {
                    {0, 0},
                    {0, 0}
            };
            int expected = 0;
            assertEquals(expected, DynamicProgramming.minPathSum(grid));
        }

        @Test
        void testGrid_largeValues() {
            int[][] grid = {
                    {100, 200},
                    {300, 400}
            };
            int expected = 100 + 200 + 400; // 100 → 200 → 400
            assertEquals(expected, DynamicProgramming.minPathSum(grid));
        }
    }
}