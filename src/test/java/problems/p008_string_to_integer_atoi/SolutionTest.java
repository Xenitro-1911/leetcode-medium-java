package problems.p008_string_to_integer_atoi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // --- Basic positive numbers ---

    @Test
    void testSimplePositive() {
        assertEquals(42, solution.myAtoi("42"));
    }

    @Test
    void testExplicitPlusSign() {
        assertEquals(1, solution.myAtoi("+1"));
    }

    @Test
    void testLeadingWhitespace() {
        assertEquals(42, solution.myAtoi("   42"));
    }

    @Test
    void testLeadingWhitespaceWithSign() {
        assertEquals(42, solution.myAtoi("   +42"));
    }

    // --- Negative numbers ---

    @Test
    void testSimpleNegative() {
        assertEquals(-42, solution.myAtoi("-42"));
    }

    @Test
    void testNegativeWithWhitespace() {
        assertEquals(-42, solution.myAtoi("   -42"));
    }

    // --- Trailing non-digit characters ---

    @Test
    void testTrailingLetters() {
        assertEquals(4193, solution.myAtoi("4193 with words"));
    }

    @Test
    void testWordsBeforeDigits() {
        assertEquals(0, solution.myAtoi("words and 987"));
    }

    // --- Leading zeros ---

    @Test
    void testLeadingZeros() {
        assertEquals(32, solution.myAtoi("0032"));
    }

    @Test
    void testAllZeros() {
        assertEquals(0, solution.myAtoi("000"));
    }

    @Test
    void testSingleZero() {
        assertEquals(0, solution.myAtoi("0"));
    }

    // --- Overflow / Underflow ---

    @Test
    void testOverflow() {
        assertEquals(Integer.MAX_VALUE, solution.myAtoi("2147483648"));
    }

    @Test
    void testLargeOverflow() {
        assertEquals(Integer.MAX_VALUE, solution.myAtoi("99999999999999999999"));
    }

    @Test
    void testUnderflow() {
        assertEquals(Integer.MIN_VALUE, solution.myAtoi("-91283472332"));
    }

    @Test
    void testExactMaxValue() {
        assertEquals(Integer.MAX_VALUE, solution.myAtoi("2147483647"));
    }

    @Test
    void testExactMinValue() {
        assertEquals(Integer.MIN_VALUE, solution.myAtoi("-2147483648"));
    }

    // --- Edge / invalid inputs ---

    @Test
    void testEmptyString() {
        assertEquals(0, solution.myAtoi(""));
    }

    @Test
    void testOnlyWhitespace() {
        assertEquals(0, solution.myAtoi("   "));
    }

    @Test
    void testInvalidDoubleSign() {
        assertEquals(0, solution.myAtoi("+-12"));
    }

    @Test
    void testSignOnly() {
        assertEquals(0, solution.myAtoi("-"));
    }

    @Test
    void testPlusSignOnly() {
        assertEquals(0, solution.myAtoi("+"));
    }
}