# 8. String to Integer (atoi)

**Difficulty**: Medium  
**Topic**: String, Simulation

---

## Problem Summary
Convert a string to a 32-bit signed integer by:
1. Ignoring leading whitespace
2. Reading an optional sign (`+` or `-`)
3. Reading digits until a non-digit or end of string
4. Clamping to `[Integer.MIN_VALUE, Integer.MAX_VALUE]` if overflow

---

## Approach: Linear Scan with Early Overflow Detection

### Key Steps
1. **Trim** leading/trailing whitespace, return `0` if empty
2. **Sign**: Check first character for `+` or `-`, store as `sign = 1` or `-1`, advance index
3. **Digits**: Walk forward while `Character.isDigit(c)`, building result as:
   ```
   res = res * 10 + (c - '0')
   ```
4. **Overflow**: Check after each digit addition — if `res` exceeds bounds (accounting for sign), clamp and return immediately
5. **Return**: `(int) res * sign`

### Why `long` for `res`?
Using `long` lets you accumulate digits freely and compare against `Integer.MAX_VALUE` / `Integer.MIN_VALUE` without needing `try/catch` or pre-validation.

### Overflow Check Inside the Loop
```java
if (sign == 1  && res > Integer.MAX_VALUE)  return Integer.MAX_VALUE;
if (sign == -1 && res * -1 < Integer.MIN_VALUE) return Integer.MIN_VALUE;
```
Checking *inside* the loop means you short-circuit as soon as overflow is detected, avoiding any further computation.

---

## Solution
```java
class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;

        int i = 0;
        int sign = 1;
        long res = 0;

        // 1. Handle Sign
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 2. Convert Digits
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            res = res * 10 + (s.charAt(i) - '0');

            // 3. Handle Overflow immediately
            if (sign == 1  && res > Integer.MAX_VALUE)  return Integer.MAX_VALUE;
            if (sign == -1 && res * -1 < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        return (int) res * sign;
    }
}
```

---

## Complexity
| | |
|---|---|
| **Time** | O(n) — single pass through the string |
| **Space** | O(1) — no extra data structures |

---

## Edge Cases
| Input | Output | Reason |
|---|---|---|
| `"   42"` | `42` | Leading whitespace trimmed |
| `"-91283472332"` | `-2147483648` | Underflow → MIN_VALUE |
| `"2147483648"` | `2147483647` | Overflow → MAX_VALUE |
| `"words and 987"` | `0` | No leading digits |
| `"+-12"` | `0` | Invalid sign sequence |
| `"   "` | `0` | Empty after trim |
| `"0032"` | `32` | Leading zeros handled naturally |
| `"+1"` | `1` | Explicit positive sign |

---

## Key Takeaways
- Use `long` to accumulate digits — avoids `parseInt` exceptions entirely
- Check overflow **inside** the digit loop for early exit
- Sign is only valid as the **first character** after whitespace — the index-based approach enforces this naturally (no `hasSign` flag needed)
- `(s.charAt(i) - '0')` converts a digit char to its int value