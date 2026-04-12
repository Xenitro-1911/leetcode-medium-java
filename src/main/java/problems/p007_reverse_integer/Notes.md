# 7. Reverse Integer

## Problem
Given a signed 32-bit integer `x`, return `x` with its digits reversed.
If reversing causes overflow outside `[-2^31, 2^31 - 1]`, return `0`.

## Approach — Long Accumulator

### Key Idea
Extract digits one at a time using `% 10` and build the reversed number using `* 10 + digit`.
Use a `long` to safely accumulate the reversed value, then check bounds at the end.

### Algorithm
1. Declare `rev = 0L` (long)
2. While `x != 0`:
    - Extract last digit: `x % 10`
    - Append to `rev`: `rev = rev * 10 + (x % 10)`
    - Remove last digit from `x`: `x = x / 10`
3. If `rev > Integer.MAX_VALUE` or `rev < Integer.MIN_VALUE` → return `0`
4. Otherwise return `(int) rev`

## Why `long`?
A reversed `int` can temporarily exceed 32-bit range during accumulation.
Using `long` (64-bit) gives enough headroom to detect overflow *after* the loop
rather than mid-computation.

## Negative Numbers
Java's `%` operator preserves the sign of the dividend.
- `(-123) % 10 == -3` ✅
- So negative numbers are handled naturally — no special casing needed.

## Overflow Check
```java
if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) return 0;
```
- Uses strict `>` / `<` — boundary values like `2147483647` are valid and pass through.
- Check happens after the loop — safe because `rev` is a `long`.

## Complexity
- **Time:** O(log x) — number of digits in x
- **Space:** O(1)

## Edge Cases
| Input        | Output | Reason                        |
|--------------|--------|-------------------------------|
| `0`          | `0`    | Loop doesn't run, rev stays 0 |
| `120`        | `21`   | Trailing zeros dropped        |
| `-123`       | `-321` | Sign preserved via `%`        |
| `1534236469` | `0`    | Reversed value overflows      |
| `-2147483648`| `0`    | Reversed value overflows      |
| `1000000003` | `0`    | Reversed = 3000000001 > MAX   |