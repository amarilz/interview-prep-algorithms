package com.amarildo.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapTest {

    @Nested
    @DisplayName("min heap")
    class MinHeap {

        @Test
        void testInsertAndPeekSingleElement() {
            Heap.MinHeap heap = new Heap.MinHeap();
            heap.insert(10);
            assertEquals(10, heap.peek());
        }

        @Test
        void testInsertMultipleAndPeek() {
            Heap.MinHeap heap = new Heap.MinHeap();
            heap.insert(10);
            heap.insert(5);
            heap.insert(20);
            assertEquals(5, heap.peek());
        }

        @Test
        void testExtractMinReturnsMinimum() {
            Heap.MinHeap heap = new Heap.MinHeap();
            heap.insert(30);
            heap.insert(10);
            heap.insert(20);
            assertEquals(10, heap.extractMin());
        }

        @Test
        void testExtractMinMaintainsHeapProperty() {
            Heap.MinHeap heap = new Heap.MinHeap();
            heap.insert(40);
            heap.insert(10);
            heap.insert(30);
            heap.insert(5);
            heap.insert(20);

            assertEquals(5, heap.extractMin());
            assertEquals(10, heap.peek());
            assertEquals(10, heap.extractMin());
            assertEquals(20, heap.peek());
        }

        @Test
        void testMinHeapOrderWithMultipleExtracts() {
            Heap.MinHeap heap = new Heap.MinHeap();
            int[] values = {15, 3, 17, 10, 84, 19, 6, 22, 9};
            for (int value : values) {
                heap.insert(value);
            }

            int[] expectedOrder = {3, 6, 9, 10, 15, 17, 19, 22, 84};
            for (int expected : expectedOrder) {
                assertEquals(expected, heap.extractMin());
            }
        }
    }
}
