package com.amarildo.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BacktrackingTest {

    @Nested
    @DisplayName("generate all solutions")
    class Basic {

        @Test
        void testPermute_3Elementi() {
            int[] input = {1, 2, 3};
            List<List<Integer>> expected = Arrays.asList(
                    Arrays.asList(1, 2, 3),
                    Arrays.asList(1, 3, 2),
                    Arrays.asList(2, 1, 3),
                    Arrays.asList(2, 3, 1),
                    Arrays.asList(3, 1, 2),
                    Arrays.asList(3, 2, 1)
            );

            List<List<Integer>> actual = Backtracking.permute(input);

            assertEquals(expected.size(), actual.size(), "Numero di permutazioni errato");
            assertTrue(actual.containsAll(expected), "Permutazioni mancanti o errate");
        }

        @Test
        void testPermute_2Elementi() {
            int[] input = {1, 2};
            List<List<Integer>> expected = Arrays.asList(
                    Arrays.asList(1, 2),
                    Arrays.asList(2, 1)
            );

            List<List<Integer>> actual = Backtracking.permute(input);

            assertEquals(expected.size(), actual.size());
            assertTrue(actual.containsAll(expected));
        }


        @Test
        void testPermute_FattorialeNumeroPermutazioni() {
            int[] input = {1, 2, 3, 4};
            List<List<Integer>> result = Backtracking.permute(input);
            assertEquals(24, result.size()); // 4! = 24
        }
    }

    @Nested
    @DisplayName("count solutions")
    class ContareSoluzioni {

        @Test
        void testNEquals1() {
            assertEquals(1, Backtracking.totalNQueens(1), "N=1 dovrebbe avere 1 soluzione");
        }

        @Test
        void testNEquals2() {
            assertEquals(0, Backtracking.totalNQueens(2), "N=2 non ha soluzioni");
        }

        @Test
        void testNEquals3() {
            assertEquals(0, Backtracking.totalNQueens(3), "N=3 non ha soluzioni");
        }

        @Test
        void testNEquals4() {
            assertEquals(2, Backtracking.totalNQueens(4), "N=4 ha 2 soluzioni");
        }

        @Test
        void testNEquals5() {
            assertEquals(10, Backtracking.totalNQueens(5), "N=5 ha 10 soluzioni");
        }

        @Test
        void testNEquals6() {
            assertEquals(4, Backtracking.totalNQueens(6), "N=6 ha 4 soluzioni");
        }

        @Test
        void testNEquals8() {
            assertEquals(92, Backtracking.totalNQueens(8), "N=8 ha 92 soluzioni");
        }
    }
}