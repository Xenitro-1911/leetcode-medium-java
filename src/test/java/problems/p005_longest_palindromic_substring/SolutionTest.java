package problems.p005_longest_palindromic_substring;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // --- Valid cases ---

    @Test
    void testBasicOddPalindrome() {
        String result = solution.longestPalindrome("babad");
        assertTrue(result.equals("bab") || result.equals("aba"),
                "Expected 'bab' or 'aba', got: " + result);
    }

    @Test
    void testBasicEvenPalindrome() {
        assertEquals("bb", solution.longestPalindrome("cbbd"));
    }

    @Test
    void testFullStringPalindromeOdd() {
        assertEquals("racecar", solution.longestPalindrome("racecar"));
    }

    @Test
    void testFullStringPalindromeEven() {
        assertEquals("abba", solution.longestPalindrome("abba"));
    }

    @Test
    void testPalindromeInMiddle() {
        assertEquals("aba", solution.longestPalindrome("xabax"));
    }

    @Test
    void testLongerEvenPalindrome() {
        assertEquals("bbbb", solution.longestPalindrome("abbbbz"));
    }

    @Test
    void testAllSameCharacters() {
        assertEquals("aaaa", solution.longestPalindrome("aaaa"));
    }

    // --- Edge cases ---

    @Test
    void testSingleCharacter() {
        assertEquals("a", solution.longestPalindrome("a"));
    }

    @Test
    void testTwoSameCharacters() {
        assertEquals("aa", solution.longestPalindrome("aa"));
    }

    @Test
    void testTwoDifferentCharacters() {
        String result = solution.longestPalindrome("ac");
        assertEquals(1, result.length(),
                "Expected any single character, got: " + result);
    }

    @Test
    void testNoPalindromeGreaterThanOne() {
        String result = solution.longestPalindrome("abcd");
        assertEquals(1, result.length(),
                "Expected any single character, got: " + result);
    }

    @Test
    void testPalindromeAtEnd() {
        assertEquals("aba", solution.longestPalindrome("xyzaba"));
    }

    @Test
    void testPalindromeAtStart() {
        assertEquals("aba", solution.longestPalindrome("abaxyz"));
    }
}