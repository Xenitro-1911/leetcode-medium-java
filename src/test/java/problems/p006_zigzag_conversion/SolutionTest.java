package problems.p006_zigzag_conversion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // --- Provided Examples ---

    @Test
    void example1_numRows3() {
        assertEquals("PAHNAPLSIIGYIR", solution.convert("PAYPALISHIRING", 3));
    }

    @Test
    void example2_numRows4() {
        assertEquals("PINALSIGYAHRPI", solution.convert("PAYPALISHIRING", 4));
    }

    @Test
    void example3_singleChar() {
        assertEquals("A", solution.convert("A", 1));
    }

    // --- Edge Cases ---

    @Test
    void numRows1_noZigzag() {
        assertEquals("ABCDE", solution.convert("ABCDE", 1));
    }

    @Test
    void numRowsEqualsLength_noZigzag() {
        assertEquals("ABCD", solution.convert("ABCD", 4));
    }

    @Test
    void numRowsGreaterThanLength_noZigzag() {
        assertEquals("ABC", solution.convert("ABC", 10));
    }

    @Test
    void numRows2_alternating() {
        // P Y A S I I G
        // A P L I H R N
        assertEquals("PYASIIGAPLIHRIN", solution.convert("PAYPALISHIRING", 2));
    }

    @Test
    void singleCharWithMultipleRows() {
        assertEquals("X", solution.convert("X", 5));
    }

    @Test
    void twoChars_numRows2() {
        assertEquals("AB", solution.convert("AB", 2));
    }

    @Test
    void twoChars_numRows1() {
        assertEquals("AB", solution.convert("AB", 1));
    }

    // --- Cycle Boundary Cases ---

    @Test
    void exactlyOneCycle_numRows3() {
        // cycleLen = 4: A B C D → A C B D
        assertEquals("ACBD", solution.convert("ABCD", 3));
    }

    @Test
    void stringLengthNotMultipleOfCycle() {
        // numRows=3, cycleLen=4, s="ABCDE" (len 5)
        // row0: A, E  row1: B, D  row2: C
        assertEquals("AEBDC", solution.convert("ABCDE", 3));
    }

    @Test
    void allSameChars() {
        assertEquals("AAAAAAA", solution.convert("AAAAAAA", 3));
    }

    @Test
    void withSpecialChars() {
        // commas and dots are valid per constraints
        assertEquals(",.ABC.,", solution.convert(",.ABC.,", 1));
    }

    @Test
    void largeNumRows_effectivelyNoZigzag() {
        String s = "HELLO";
        assertEquals(s, solution.convert(s, 1000));
    }
}