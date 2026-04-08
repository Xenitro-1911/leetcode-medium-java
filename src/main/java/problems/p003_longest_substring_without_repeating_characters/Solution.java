package problems.p003_longest_substring_without_repeating_characters;

import java.util.Arrays;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }


        int l = s.length();
        int left = 0, maxLen = 0;
        int[] lastSeen = new int[128];
        Arrays.fill(lastSeen, -1);

        for (int right = 0; right < l; right++) {
            char c = s.charAt(right);

            if (lastSeen[c] >= left) {
                left =lastSeen[c]+1; // where should left jump to?
            }

            lastSeen[c] = right;      // record where we saw c
            maxLen = Math.max(maxLen, right-left+1);  // window size?
        }
        return maxLen;
    }
}