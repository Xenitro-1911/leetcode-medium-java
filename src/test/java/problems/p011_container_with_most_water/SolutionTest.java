package problems.p011_container_with_most_water;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // --- Basic examples ---

    @Test
    void testLeetcodeExample1() {
        // heights [1,8,6,2,5,4,8,3,7]: best is i=1,i=8 → min(8,7)*7=49
        assertEquals(49, solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    @Test
    void testLeetcodeExample2() {
        // [1,1]: only one pair
        assertEquals(1, solution.maxArea(new int[]{1, 1}));
    }

    // --- Two elements ---

    @Test
    void testTwoElementsUnequalHeight() {
        // min(3,7)*1 = 3
        assertEquals(3, solution.maxArea(new int[]{3, 7}));
    }

    @Test
    void testTwoElementsSameHeight() {
        assertEquals(5, solution.maxArea(new int[]{5, 5}));
    }

    // --- Uniform heights ---

    @Test
    void testAllSameHeight() {
        // min=4, width=4 → 16
        assertEquals(16, solution.maxArea(new int[]{4, 4, 4, 4, 4}));
    }

    // --- Monotonic arrays ---

    @Test
    void testAscending() {
        // [1,2,3,4,5]: best is i=0,i=4 → min(1,5)*4=4
        assertEquals(4, solution.maxArea(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testDescending() {
        // [5,4,3,2,1]: best is i=0,i=4 → min(5,1)*4=4
        assertEquals(4, solution.maxArea(new int[]{5, 4, 3, 2, 1}));
    }

    // --- Skip optimization cases ---

    @Test
    void testSkipShortWalls() {
        // [1,1,1,1,100,100]: skip all short walls, best=i=4,i=5 → 100*1=100
        // but i=0,i=5 → min(1,100)*5=5, i=4,i=5 → 100
        assertEquals(100, solution.maxArea(new int[]{1, 1, 1, 1, 100, 100}));
    }

    @Test
    void testBestPairNotAtEdges() {
        // [1,50,50,1]: best is i=1,i=2 → 50*1=50, but i=0,i=3 → 1*3=3
        // i=0,i=3=3, i=1,i=2=50
        assertEquals(50, solution.maxArea(new int[]{1, 50, 50, 1}));
    }

    // --- Width vs height tradeoff ---

    @Test
    void testWidthBeatsHeight() {
        // [8,1,1,1,1,1,1,8]: wide container wins
        // i=0,i=7 → 8*7=56 vs i=0,i=1 → 1*1=1
        assertEquals(56, solution.maxArea(new int[]{8, 1, 1, 1, 1, 1, 1, 8}));
    }

    @Test
    void testHeightBeatsWidth() {
        // [1,100,100,1]: narrow but tall beats wide and short
        assertEquals(100, solution.maxArea(new int[]{1, 100, 100, 1}));
    }

    // --- Edge cases ---

    @Test
    void testOneVeryTallWall() {
        // [1,1,1,1,1000]: best = i=0,i=4 → min(1,1000)*4=4
        assertEquals(4, solution.maxArea(new int[]{1, 1, 1, 1, 1000}));
    }

    @Test
    void testValleyShape() {
        // [5,1,1,1,5]: best = i=0,i=4 → 5*4=20
        assertEquals(20, solution.maxArea(new int[]{5, 1, 1, 1, 5}));
    }

    @Test
    void testPeakInMiddle() {
        // [1,2,10,2,1]: best = i=1,i=3 → 2*2=4, i=0,i=4 → 1*4=4
        assertEquals(4, solution.maxArea(new int[]{1, 2, 10, 2, 1}));
    }

    @Test
    void testLargeValues() {
        int[] height = new int[10000];
        for (int i = 0; i < 10000; i++) height[i] = 10000;
        // All same: min=10000, width=9999 → 99990000
        assertEquals(99990000, solution.maxArea(height));
    }
}