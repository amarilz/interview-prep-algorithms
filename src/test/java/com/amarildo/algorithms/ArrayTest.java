package com.amarildo.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
        void testNoSubarrayFound() {
            int[] nums = {1, 1, 1, 1, 1};
            int target = 11;
            int expected = 0;
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
        void testEmptyArray() {
            int[] nums = {};
            int target = 100;
            int expected = 0;
            assertEquals(expected, Array.minSubArrayLen(nums, target));
        }

        @Test
        void testSingleElementGreaterThanTarget() {
            int[] nums = {11};
            int target = 7;
            int expected = 1;
            assertEquals(expected, Array.minSubArrayLen(nums, target));
        }

        @Test
        void testSingleElementLessThanTarget() {
            int[] nums = {5};
            int target = 7;
            int expected = 0;
            assertEquals(expected, Array.minSubArrayLen(nums, target));
        }
    }
}
