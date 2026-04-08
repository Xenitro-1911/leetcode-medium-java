# 005 – Longest Palindromic Substring (Optimized)

## Problem Summary
Given a string `s`, return the longest substring that is a palindrome.

---

## Approach: Expand Around Center (Index Tracking)

Every palindrome has a center. Instead of checking every possible substring, treat every index (and every gap between indices) as a potential center and expand outward as long as characters match.

There are `2n - 1` possible centers for a string of length `n`:
- `n` odd-length centers (single character at index `i`)
- `n - 1` even-length centers (gap between index `i` and `i+1`)

Instead of returning a substring from the helper, this version tracks `start` and `maxLen` as instance variables and does a single `substring` call at the very end.

---

## Key Insight: Track Indexes, Not Substrings

The previous approach returned a new `String` from the helper on every expansion — one object allocation per center per iteration. This version instead tracks two integers:

```java
private int start, maxLen;
```

And computes the result once at the end:
```java
return s.substring(start, start + maxLen);
```

This avoids repeated object allocation in the hot loop.

---

## Key Insight: Length Formula After Loop

After the while loop exits, `left` and `right` have gone one step too far in each direction. The valid palindrome spans from `left+1` to `right-1` (inclusive), so:

```
length = (right - 1) - (left + 1) + 1 = right - left - 1
```

```java
int currentLen = right - left - 1;
if (currentLen > maxLen) {
    start = left + 1;
    maxLen = currentLen;
}
```

---

## Key Insight: Bounds Check Before charAt

The bounds check **must** come before the character comparison:
```java
while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
```
If `charAt` is called before the bounds check, a `StringIndexOutOfBoundsException` is thrown even if the condition would have been false.

---

## Why void Helper Instead of String Helper?

| String-returning helper | void helper (this solution) |
|---|---|
| Creates a new String object per expansion | No object allocation in the loop |
| Simpler to reason about | Requires instance variables |
| Single `substring` at the end | Single `substring` at the end |

Both are O(n²) time — the difference is constant-factor: fewer allocations in the hot path.

---

## Complexity

| | Complexity |
|---|---|
| Time | O(n²) — n centers, each expanding up to O(n) |
| Space | O(1) — only two integer fields and the final result |

---

## Final Solution

```java
public class Solution {
    private int start, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        for (int i = 0; i < len; i++) {
            expandAroundCenter(s, i, i);       // odd length
            expandAroundCenter(s, i, i + 1);   // even length
        }
        return s.substring(start, start + maxLen);
    }

    private void expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        int currentLen = right - left - 1;
        if (currentLen > maxLen) {
            start = left + 1;
            maxLen = currentLen;
        }
    }
}
```