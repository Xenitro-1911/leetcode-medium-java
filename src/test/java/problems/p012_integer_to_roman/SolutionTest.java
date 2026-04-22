package problems.p012_integer_to_roman;

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
    void example1_3() {
        assertEquals("III", solution.intToRoman(3));
    }

    @Test
    void example2_58() {
        assertEquals("LVIII", solution.intToRoman(58));
    }

    @Test
    void example3_1994() {
        assertEquals("MCMXCIV", solution.intToRoman(1994));
    }

    // --- Boundary values ---

    @Test
    void minimum_1() {
        assertEquals("I", solution.intToRoman(1));
    }

    @Test
    void maximum_3999() {
        assertEquals("MMMCMXCIX", solution.intToRoman(3999));
    }

    // --- Subtractive forms ---

    @Test
    void subtractive_4_IV() {
        assertEquals("IV", solution.intToRoman(4));
    }

    @Test
    void subtractive_9_IX() {
        assertEquals("IX", solution.intToRoman(9));
    }

    @Test
    void subtractive_40_XL() {
        assertEquals("XL", solution.intToRoman(40));
    }

    @Test
    void subtractive_90_XC() {
        assertEquals("XC", solution.intToRoman(90));
    }

    @Test
    void subtractive_400_CD() {
        assertEquals("CD", solution.intToRoman(400));
    }

    @Test
    void subtractive_900_CM() {
        assertEquals("CM", solution.intToRoman(900));
    }

    // --- Round numbers ---

    @Test
    void thousand_1000() {
        assertEquals("M", solution.intToRoman(1000));
    }

    @Test
    void fiveHundred_500() {
        assertEquals("D", solution.intToRoman(500));
    }

    @Test
    void tripleThousand_3000() {
        assertEquals("MMM", solution.intToRoman(3000));
    }

    // --- Mixed digit stress ---

    @Test
    void mixed_2024() {
        assertEquals("MMXXIV", solution.intToRoman(2024));
    }
}