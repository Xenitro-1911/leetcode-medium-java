package problems.p003_longest_substring_without_repeating_characters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // --- Provided examples ---

    @Test
    void testExample1_abcabcbb() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    void testExample2_bbbbb() {
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
    }

    @Test
    void testExample3_pwwkew() {
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
    }

    // --- Edge cases ---

    @Test
    void testEmptyString() {
        assertEquals(0, solution.lengthOfLongestSubstring(""));
    }

    @Test
    void testSingleCharacter() {
        assertEquals(1, solution.lengthOfLongestSubstring("a"));
    }

    @Test
    void testTwoDistinctChars() {
        assertEquals(2, solution.lengthOfLongestSubstring("ab"));
    }

    @Test
    void testTwoDuplicateChars() {
        assertEquals(1, solution.lengthOfLongestSubstring("aa"));
    }

    // --- Longest window not at start ---

    @Test
    void testLongestAtEnd() {
        // "abcde" is at the end
        assertEquals(5, solution.lengthOfLongestSubstring("aabcde"));
    }

    @Test
    void testLongestInMiddle() {
        assertEquals(3, solution.lengthOfLongestSubstring("aabca"));
    }

    // --- Full string is the answer ---

    @Test
    void testNoDuplicates() {
        assertEquals(5, solution.lengthOfLongestSubstring("abcde"));
    }

    // --- Special characters and spaces ---

    @Test
    void testStringWithSpaces() {
        // "ab c" has length 4 — space is a valid unique character
        assertEquals(4, solution.lengthOfLongestSubstring("ab c"));
    }

    @Test
    void testStringWithSymbols() {
        assertEquals(5, solution.lengthOfLongestSubstring("a1!b2"));
    }

    // --- Digits ---

    @Test
    void testDigits() {
        assertEquals(10, solution.lengthOfLongestSubstring("0123456789"));
    }

    // --- Long repeated pattern ---

    @Test
    void testLongRepeatingPattern() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcabcabc"));
    }
}