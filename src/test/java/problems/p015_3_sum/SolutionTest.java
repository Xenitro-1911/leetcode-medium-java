package problems.p015_3_sum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- LeetCode examples ---

    @Test
    void example1_mixed() {
        List<List<Integer>> result = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of(-1, -1, 2)));
        assertTrue(result.contains(List.of(-1, 0, 1)));
    }

    @Test
    void example2_allZeros() {
        List<List<Integer>> result = solution.threeSum(new int[]{0, 0, 0});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(0, 0, 0)));
    }

    @Test
    void example3_noTriplet() {
        List<List<Integer>> result = solution.threeSum(new int[]{0, 1, 1});
        assertTrue(result.isEmpty());
    }

    // --- Boundary / edge cases ---

    @Test
    void tooShort_twoElements() {
        List<List<Integer>> result = solution.threeSum(new int[]{0, 0});
        assertTrue(result.isEmpty());
    }

    @Test
    void allPositive_noTriplet() {
        List<List<Integer>> result = solution.threeSum(new int[]{1, 2, 3, 4});
        assertTrue(result.isEmpty());
    }

    @Test
    void allNegative_noTriplet() {
        List<List<Integer>> result = solution.threeSum(new int[]{-4, -3, -2, -1});
        assertTrue(result.isEmpty());
    }

    // --- Duplicate handling ---

    @Test
    void manyDuplicates_singleTriplet() {
        List<List<Integer>> result = solution.threeSum(new int[]{0, 0, 0, 0});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(0, 0, 0)));
    }

    @Test
    void duplicateFirstElement() {
        List<List<Integer>> result = solution.threeSum(new int[]{-2, -2, 0, 2, 2});
        assertEquals(1, result.size());
        assertTrue(result.contains(List.of(-2, 0, 2)));
    }

    @Test
    void multipleTriplets() {
        List<List<Integer>> result = solution.threeSum(new int[]{-4, -1, -1, 0, 1, 2});
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of(-1, -1, 2)));
        assertTrue(result.contains(List.of(-1, 0, 1)));
    }

    // --- Stress / larger input ---

    @Test
    void largeRange() {
        List<List<Integer>> result = solution.threeSum(new int[]{-10, -7, -3, 0, 1, 3, 5, 7, 10});
        assertTrue(result.contains(List.of(-10, 3, 7)));
        assertTrue(result.contains(List.of(-7, 0, 7)));
        assertTrue(result.contains(List.of(-3, -7, 10)));
    }
}
