package problems.p005_longest_palindromic_substring;

public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        String longest = "";

        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd length (center is one character)
            String s1 = expand(s, i, i);
            // Case 2: Even length (center is between two characters)
            String s2 = expand(s, i, i + 1);

            if (s1.length() > longest.length()) longest = s1;
            if (s2.length() > longest.length()) longest = s2;
        }
        return longest;
    }

    private String expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // When the loop breaks, left and right are outside the palindrome.
        // left + 1 is the start, right is the exclusive end.
        return s.substring(left + 1, right);
    }
}
