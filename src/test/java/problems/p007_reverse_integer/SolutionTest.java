package problems.p007_reverse_integer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // --- Basic positive numbers ---

    @Test
    void testSingleDigit() {
        assertEquals(5, solution.reverse(5));
    }

    @Test
    void testSimplePositive() {
        assertEquals(321, solution.reverse(123));
    }

    @Test
    void testLargerPositive() {
        assertEquals(9876543, solution.reverse(3456789));
    }

    // --- Trailing zeros ---

    @Test
    void testTrailingZero() {
        assertEquals(21, solution.reverse(120));
    }

    @Test
    void testMultipleTrailingZeros() {
        assertEquals(1, solution.reverse(1000));
    }

    // --- Negative numbers ---

    @Test
    void testSimpleNegative() {
        assertEquals(-321, solution.reverse(-123));
    }

    @Test
    void testNegativeWithTrailingZero() {
        assertEquals(-21, solution.reverse(-120));
    }

    // --- Zero ---

    @Test
    void testZero() {
        assertEquals(0, solution.reverse(0));
    }

    // --- Overflow cases ---

    @Test
    void testPositiveOverflow() {
        assertEquals(0, solution.reverse(1534236469));
    }

    @Test
    void testNegativeOverflow() {
        assertEquals(0, solution.reverse(-2147483648));
    }

    @Test
    void testJustOverMaxWhenReversed() {
        // 1000000003 reversed = 3000000001 > Integer.MAX_VALUE
        assertEquals(0, solution.reverse(1000000003));
    }

    // --- Boundary values ---

    @Test
    void testIntMaxValue() {
        // 2147483647 reversed = 7463847412 → overflow
        assertEquals(0, solution.reverse(Integer.MAX_VALUE));
    }

    @Test
    void testIntMinValue() {
        // -2147483648 reversed = -8463847412 → overflow
        assertEquals(0, solution.reverse(Integer.MIN_VALUE));
    }

    // --- Palindrome number ---

    @Test
    void testPalindrome() {
        assertEquals(1221, solution.reverse(1221));
    }
}