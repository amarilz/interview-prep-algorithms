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
}