package com.amarildo.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayTest {

    @Nested
    @DisplayName("binary search")
    class BinarySearch {

        @Test
        void testTargetFoundInMiddle() {
            int[] array = {1, 3, 5, 7, 9};
            assertTrue(Array.binarySearch(array, 5));
        }

        @Test
        void testTargetFoundAtBeginning() {
            int[] array = {2, 4, 6, 8, 10};
            assertTrue(Array.binarySearch(array, 2));
        }

        @Test
        void testTargetFoundAtEnd() {
            int[] array = {2, 4, 6, 8, 10};
            assertTrue(Array.binarySearch(array, 10));
        }

        @Test
        void testTargetNotFound() {
            int[] array = {1, 3, 5, 7, 9};
            assertFalse(Array.binarySearch(array, 4));
        }

        @Test
        void testEmptyArray() {
            int[] array = {};
            assertFalse(Array.binarySearch(array, 1));
        }

        @Test
        void testSingleElementFound() {
            int[] array = {7};
            assertTrue(Array.binarySearch(array, 7));
        }

        @Test
        void testSingleElementNotFound() {
            int[] array = {7};
            assertFalse(Array.binarySearch(array, 3));
        }

        @Test
        void testNegativeNumbers() {
            int[] array = {-10, -5, 0, 5, 10};
            assertTrue(Array.binarySearch(array, -5));
            assertFalse(Array.binarySearch(array, -3));
        }
    }

    @Nested
    @DisplayName("fixed sliding window")
    class FixedSlidingWindow {

        @Test
        void testCasoBase() {
            int[] nums = {2, 1, 5, 1, 3, 2};
            int k = 3;
            int expected = 9; // subarray: [5, 1, 3]
            assertEquals(expected, Array.subarraySumFixed(nums, k));
        }

        @Test
        void testArrayDiLunghezzaEsatta() {
            int[] nums = {1, 2, 3};
            int k = 3;
            int expected = 6;
            assertEquals(expected, Array.subarraySumFixed(nums, k));
        }

        @Test
        void testConNumeriNegativi() {
            int[] nums = {-2, -1, 0, -3, -4};
            int k = 2;
            int expected = -1; // subarray: [-1, 0]
            assertEquals(expected, Array.subarraySumFixed(nums, k));
        }

        @Test
        void testKMaggioreDiArray() {
            int[] nums = {1, 2};
            int k = 3;
            int expected = 0; // input non valido
            assertEquals(expected, Array.subarraySumFixed(nums, k));
        }

        @Test
        void testKUgualeZero() {
            int[] nums = {1, 2, 3};
            int k = 0;
            int expected = 0;
            assertEquals(expected, Array.subarraySumFixed(nums, k));
        }

        @Test
        void testArrayConUnElemento() {
            int[] nums = {10};
            int k = 1;
            int expected = 10;
            assertEquals(expected, Array.subarraySumFixed(nums, k));
        }

        @Test
        void testArrayVuoto() {
            int[] nums = {};
            int k = 1;
            int expected = 0;
            assertEquals(expected, Array.subarraySumFixed(nums, k));
        }
    }

    @Nested
    @DisplayName("variable sliding window")
    class VariableSlidingWindow {

        @Test
        void testExampleCase() {
            int[] nums = {2, 3, 1, 2, 4, 3};
            int target = 7;
            int expected = 2; // [4,3]
            assertEquals(expected, Array.minSubArrayLen(nums, target));
        }

        @Test
        void testSingleElementEqualToTarget() {
            int[] nums = {5, 1, 3, 5, 10, 7, 4, 9, 2, 8};
            int target = 10;
            int expected = 1; // [10]
            assertEquals(expected, Array.minSubArrayLen(nums, target));
        }

        @Test
        void testWholeArrayNeeded() {
            int[] nums = {1, 2, 3, 4, 5};
            int target = 15;
            int expected = 5;
            assertEquals(expected, Array.minSubArrayLen(nums, target));
        }

        @Test
        void testMultiplePossibleSubarrays() {
            int[] nums = {1, 4, 4};
            int target = 4;
            int expected = 1;
            assertEquals(expected, Array.minSubArrayLen(nums, target));
        }

        @Test
        void testSingleElementGreaterThanTarget() {
            int[] nums = {11};
            int target = 7;
            int expected = 1;
            assertEquals(expected, Array.minSubArrayLen(nums, target));
        }
    }

    @Nested
    @DisplayName("prefix sum")
    class PrefixSum {

        @Test
        void testPrefixSumAndQuery_basicCase() {
            int[] arr = {1, 2, 3, 4, 5};
            int[] prefix = Array.buildPrefixSum(arr);

            // Verifica valori del prefix array
            assertArrayEquals(new int[]{1, 3, 6, 10, 15}, prefix);

            // Verifica query su sottoarray
            assertEquals(6, Array.queryPrefixSum(prefix, 0, 2));  // 1+2+3
            assertEquals(12, Array.queryPrefixSum(prefix, 2, 4)); // 3+4+5
            assertEquals(9, Array.queryPrefixSum(prefix, 1, 3));  // 2+3+4
            assertEquals(1, Array.queryPrefixSum(prefix, 0, 0));  // solo primo elemento
            assertEquals(5, Array.queryPrefixSum(prefix, 4, 4));  // solo ultimo elemento
        }

        @Test
        void testPrefixSumAndQuery_singleElement() {
            int[] arr = {42};
            int[] prefix = Array.buildPrefixSum(arr);

            assertArrayEquals(new int[]{42}, prefix);
            assertEquals(42, Array.queryPrefixSum(prefix, 0, 0));
        }

        @Test
        void testPrefixSumAndQuery_allZeros() {
            int[] arr = {0, 0, 0, 0};
            int[] prefix = Array.buildPrefixSum(arr);

            assertArrayEquals(new int[]{0, 0, 0, 0}, prefix);
            assertEquals(0, Array.queryPrefixSum(prefix, 1, 3));
        }

        @Test
        void testPrefixSumAndQuery_negativeNumbers() {
            int[] arr = {-1, -2, -3, -4};
            int[] prefix = Array.buildPrefixSum(arr);

            assertArrayEquals(new int[]{-1, -3, -6, -10}, prefix);
            assertEquals(-3, Array.queryPrefixSum(prefix, 0, 1));   // -1 + -2
            assertEquals(-9, Array.queryPrefixSum(prefix, 1, 3));   // -2 + -3 + -4
        }

        @Test
        void testPrefixSumAndQuery_fullArraySum() {
            int[] arr = {10, 20, 30};
            int[] prefix = Array.buildPrefixSum(arr);

            assertEquals(60, Array.queryPrefixSum(prefix, 0, 2));  // somma completa
        }
    }

    @Nested
    @DisplayName("interval increment")
    class IntervalIncrement {

        @Test
        void testNoOperations() {
            int[][] operations = {};
            int n = 5;
            int[] expected = {0, 0, 0, 0, 0};
            assertArrayEquals(expected, Array.intervalIncrement(operations, n));
        }

        @Test
        void testSingleOperation() {
            int[][] operations = {{1, 3, 2}};
            int n = 5;
            int[] expected = {0, 2, 2, 2, 0};
            assertArrayEquals(expected, Array.intervalIncrement(operations, n));
        }

        @Test
        void testMultipleNonOverlappingOperations() {
            int[][] operations = {{0, 1, 1}, {3, 4, 2}};
            int n = 5;
            int[] expected = {1, 1, 0, 2, 2};
            assertArrayEquals(expected, Array.intervalIncrement(operations, n));
        }

        @Test
        void testOverlappingOperations() {
            int[][] operations = {{0, 2, 1}, {1, 3, 2}};
            int n = 4;
            int[] expected = {1, 3, 3, 2};
            assertArrayEquals(expected, Array.intervalIncrement(operations, n));
        }

        @Test
        void testFullRangeUpdate() {
            int[][] operations = {{0, 4, 5}};
            int n = 5;
            int[] expected = {5, 5, 5, 5, 5};
            assertArrayEquals(expected, Array.intervalIncrement(operations, n));
        }

        @Test
        void testNegativeValues() {
            int[][] operations = {{0, 2, -1}, {1, 3, -2}};
            int n = 4;
            int[] expected = {-1, -3, -3, -2};
            assertArrayEquals(expected, Array.intervalIncrement(operations, n));
        }

        @Test
        void testEdgeIndices() {
            int[][] operations = {{0, 0, 4}, {4, 4, 2}};
            int n = 5;
            int[] expected = {4, 0, 0, 0, 2};
            assertArrayEquals(expected, Array.intervalIncrement(operations, n));
        }
    }

    @Nested
    @DisplayName("monotonic stack")
    class MonotonicStack {

        @Test
        void testEmptyArray() {
            int[] input = {};
            Stack<Integer> result = Array.monotonicStack(input);
            assertTrue(result.isEmpty(), "Stack should be empty");
        }

        @Test
        void testStrictlyIncreasingArray() {
            int[] input = {1, 2, 3, 4, 5};
            Stack<Integer> result = Array.monotonicStack(input);

            assertEquals(5, result.size());
            assertEquals(1, result.firstElement());
            assertEquals(5, result.peek());
        }

        @Test
        void testStrictlyDecreasingArray() {
            int[] input = {5, 4, 3, 2, 1};
            Stack<Integer> result = Array.monotonicStack(input);

            assertEquals(1, result.size());
            assertEquals(1, result.peek());
        }

        @Test
        void testArrayWithDuplicates() {
            int[] input = {3, 2, 2, 4, 1};
            Stack<Integer> result = Array.monotonicStack(input);

            // Si dovrebbero mantenere solo i numeri che mantengono la monotonicità crescente
            Stack<Integer> expected = new Stack<>();
            expected.push(1);  // Alla fine rimane solo il più piccolo

            assertEquals(expected, result);
        }

        @Test
        void testArrayMixed() {
            int[] input = {2, 1, 3, 2, 4};
            Stack<Integer> result = Array.monotonicStack(input);

            Stack<Integer> expected = new Stack<>();
            expected.push(1);
            expected.push(2);
            expected.push(4);

            assertEquals(expected, result);
        }

        @Test
        void testSingleElementArray() {
            int[] input = {42};
            Stack<Integer> result = Array.monotonicStack(input);

            assertEquals(1, result.size());
            assertEquals(42, result.peek());
        }
    }
}
