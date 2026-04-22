package problems.p016_3_sum_closest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- LeetCode examples ---

    @Test
    void example1_closestIs2() {
        assertEquals(2, solution.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    @Test
    void example2_allZeros() {
        assertEquals(0, solution.threeSumClosest(new int[]{0, 0, 0}, 1));
    }

    // --- Exact match ---

    @Test
    void exactMatch_returnsTarget() {
        assertEquals(6, solution.threeSumClosest(new int[]{1, 2, 3, 4}, 6));
    }

    @Test
    void exactMatch_negative() {
        assertEquals(-6, solution.threeSumClosest(new int[]{-1, -2, -3, -4}, -6));
    }

    // --- Boundary / edge cases ---

    @Test
    void minimumLength_threeElements() {
        assertEquals(3, solution.threeSumClosest(new int[]{1, 1, 1}, 10));
    }

    @Test
    void targetVeryLarge() {
        assertEquals(6, solution.threeSumClosest(new int[]{1, 1, 1, 3}, 100));
    }

    @Test
    void targetVeryNegative() {
        assertEquals(-6, solution.threeSumClosest(new int[]{-1, -2, -3, -4}, -100));
    }

    // --- Closest but not exact ---

    @Test
    void closestAbove() {
        assertEquals(3, solution.threeSumClosest(new int[]{-1, 0, 1, 2}, 3));
    }

    @Test
    void closestBelow() {
        assertEquals(1, solution.threeSumClosest(new int[]{-3, -1, 1, 2}, 1));
    }

    // --- Duplicates ---

    @Test
    void duplicateValues() {
        assertEquals(3, solution.threeSumClosest(new int[]{1, 1, 1, 1}, 3));
    }

    @Test
    void mixedWithDuplicates() {
        assertEquals(2, solution.threeSumClosest(new int[]{-1, -1, 1, 2, 3}, 2));
    }
}
