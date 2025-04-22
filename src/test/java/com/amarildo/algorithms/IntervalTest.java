package com.amarildo.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntervalTest {

    @Nested
    @DisplayName("greedy and priority queue")
    class GreedyAndPriorityQueue {

        @Test
        void testBasicOverlap() {
            int[][] intervals = {{1, 4}, {2, 5}, {7, 9}};
            assertEquals(2, Interval.maxOverlap(intervals));
        }

        @Test
        void testNoOverlap() {
            int[][] intervals = {{1, 2}, {3, 4}, {5, 6}};
            assertEquals(1, Interval.maxOverlap(intervals));
        }

        @Test
        void testFullOverlap() {
            int[][] intervals = {{1, 10}, {2, 9}, {3, 8}, {4, 7}};
            assertEquals(4, Interval.maxOverlap(intervals));
        }

        @Test
        void testSingleInterval() {
            int[][] intervals = {{5, 10}};
            assertEquals(1, Interval.maxOverlap(intervals));
        }

        @Test
        void testIntervalsWithSameStartAndEnd() {
            int[][] intervals = {{1, 1}, {1, 1}, {1, 1}};
            assertEquals(0, Interval.maxOverlap(intervals));
        }

        @Test
        void testTouchingButNotOverlapping() {
            int[][] intervals = {{1, 2}, {2, 3}, {3, 4}};
            assertEquals(1, Interval.maxOverlap(intervals));
        }

        @Test
        void testOverlappingAtSameTime() {
            int[][] intervals = {{1, 5}, {1, 5}, {1, 5}};
            assertEquals(3, Interval.maxOverlap(intervals));
        }
    }
}
