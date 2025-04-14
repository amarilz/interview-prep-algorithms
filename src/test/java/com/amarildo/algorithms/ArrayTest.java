package com.amarildo.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
}