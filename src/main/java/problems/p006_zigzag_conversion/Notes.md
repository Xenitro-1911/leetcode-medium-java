# 6. Zigzag Conversion

**Difficulty:** Medium  
**Topic:** String, Math/Index Pattern

---

## Problem Summary

Given a string `s` and `numRows`, simulate writing the string in a zigzag pattern across `numRows` rows, then read it back row by row.

---

## Key Insight — Cycle Length

The zigzag pattern repeats every **`cycleLen = 2 * numRows - 2`** characters.

For `numRows = 4`, cycleLen = 6:
```
P     I    N       → row 0: indices 0, 6, 12
A   L S  I G       → row 1: indices 1, 5, 7, 11
Y A   H R          → row 2: indices 2, 4, 8, 10
P     I            → row 3: indices 3, 9
```

---

## Approach — Mathematical Index Calculation

Instead of simulating the zigzag with direction tracking, directly compute which characters belong to each row.

For row `i`, starting at offset `j` (multiples of `cycleLen`):
1. **First character** in cycle: index `j + i`
2. **Second character** (diagonal, only for middle rows): index `j + cycleLen - i`

Middle rows (`i != 0 && i != numRows - 1`) have two characters per cycle.  
First and last rows have only one.

---

## Solution

```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;
        char[] result = new char[s.length()];
        int count = 0;
        int cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < s.length(); j += cycleLen) {
                result[count++] = s.charAt(j + i);
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < s.length()) {
                    result[count++] = s.charAt(j + cycleLen - i);
                }
            }
        }
        return new String(result);
    }
}
```

---

## Complexity

- **Time:** O(n) — each character is visited exactly once
- **Space:** O(n) — output char array

---

## Edge Cases

| Case | Reason |
|---|---|
| `numRows == 1` | No zigzag, return as-is |
| `numRows >= s.length()` | Each char gets its own row, no diagonal, return as-is |
| Single character | Covered by edge case above |
| `numRows == 2` | cycleLen = 2, only first/last rows, no middle rows |

---

## Alternative Approach (Simulation)

Use `numRows` StringBuilders, walk the string with a direction flag (`+1` down, `-1` up), flip at row `0` and row `numRows - 1`. Append each char to the current row's builder. Concatenate at the end.

- Same O(n) time but slightly more overhead from direction tracking and multiple StringBuilders.
- More intuitive, less math.

---

## Lessons Learned

- `break` only exits the **innermost loop** — guards must be placed at the entry of each loop when `j` can go out of bounds mid-cycle.
- The mathematical approach avoids direction tracking entirely by exploiting the fixed cycle structure.
- `numRows >= s.length()` must short-circuit — otherwise `cycleLen` math breaks down.