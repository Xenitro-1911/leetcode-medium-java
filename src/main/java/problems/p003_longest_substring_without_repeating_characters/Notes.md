# 003 — Longest Substring Without Repeating Characters

## Problem Summary
Given a string `s`, find the length of the longest substring without duplicate characters.

## Approach — Sliding Window + ASCII Lookup Table

Two pointers `left` and `right` define the current window `s[left..right]`.  
`right` advances every iteration — it never resets.  
When a duplicate is found, `left` jumps forward past the last occurrence of the duplicate.  
An `int[128]` array indexed by ASCII value tracks the last seen index of each character in O(1).

## Key Insight
Instead of restarting from scratch on each duplicate (O(n²/n³)), the window simply shrinks from the left. Every character is visited at most twice (once by `right`, once by `left`), giving O(n) overall.

## Complexity
| | |
|---|---|
| Time | O(n) — single pass |
| Space | O(1) — fixed `int[128]` array |

## Final Solution
```java
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int l = s.length();
        int left = 0, maxLen = 0;
        int[] lastSeen = new int[128];
        Arrays.fill(lastSeen, -1);

        for (int right = 0; right < l; right++) {
            char c = s.charAt(right);
            if (lastSeen[c] >= left) {
                left = lastSeen[c] + 1;
            }
            lastSeen[c] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

## Mistakes Made Along the Way

### 1. Null check after first access
Calling `s.charAt(0)` before checking `s.length() == 0` causes `StringIndexOutOfBoundsException` on empty input. Guards must come before the operations they protect.

### 2. `continue` instead of `break` on duplicate
Skipping a duplicate with `continue` and carrying on builds non-contiguous characters — not a valid substring. On a duplicate, `break` immediately.

### 3. No `maxLen` tracker
Returning `sub.length()` at the end only captures the last window built, not the best one seen. A running `maxLen` updated after every window is required.

### 4. `sub` not reset between starting positions
Without resetting `sub = ""` at the start of each outer iteration, characters from the previous window pollute the next one.

### 5. Wrong `left` jump target
- `left = right` jumps too far — collapses the window to a single character.
- `left = right + 1` jumps past `right` — empties the window entirely.
- Correct: `left = lastSeen[c] + 1` — moves just past the previous occurrence of the duplicate.

### 6. Window size off-by-one
`right - left` undercounts by 1. A window from index 2 to index 4 has 3 characters: `right - left + 1`.

## Patterns & Reminders
- **Sliding window** is the go-to pattern when asked for the longest/shortest subarray/substring satisfying a condition.
- **`int[128]` by ASCII** beats `HashMap` for fixed small character sets — O(1) with lower constant.
- Early exit guards for length 0 and 1 are redundant when the sliding window handles them correctly — but harmless.