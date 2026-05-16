# 204. Count Primes

## Problem
Given an integer `n`, return the number of prime numbers strictly less than `n`.

Example: `n = 10` → `4` (primes are 2, 3, 5, 7)

---

## Approach 1 — Brute Force (O(n²))

For each number `i`, loop through all possible divisors and count how many divide evenly. If exactly 2 divisors (1 and itself) → prime.

**Why it fails:** For large `n` (e.g. 5,000,000) this is far too slow — LeetCode gives Time Limit Exceeded.

---

## Approach 2 — Sieve of Eratosthenes (O(n log log n))

### Core Idea
Start by assuming every number is prime. Then for each prime found, mark all its multiples as composite. What's left unmarked at the end are all the primes.

### Basic Sieve Trace (`n = 10`):
```
Start: [F, F, T, T, T, T, T, T, T, T]   (0,1 = not prime)
i=2:   mark 4,6,8 → [F, F, T, T, F, T, F, T, F, T]
i=3:   mark 9    → [F, F, T, T, F, T, F, T, F, F]
i=4:   4*4=16 > 10, stop
Count true values: 2,3,5,7 → return 4 ✅
```

### Why outer loop stops at √n?
If a number has no prime factor ≤ √n, it has no factors at all — it must be prime. Any composite number `c` must have at least one factor ≤ √c. So beyond √n, everything remaining is already correctly marked.

### Why inner loop starts at `i*i`?
All smaller multiples of `i` (like `2i`, `3i`) were already marked by earlier primes. Starting at `i*i` skips redundant work.

---

## Approach 3 — Optimised Sieve (Final Solution)

Two key optimisations over the basic sieve:

### Optimisation 1 — Skip even numbers entirely
2 is the only even prime. Every other even number is composite. So:
- Count starts at `1` (accounting for 2 upfront)
- Outer loop starts at `3`, steps by `2` (odd numbers only)
- Inner loop steps by `2*i` (skips even multiples of `i`)

```
i=3: mark 9, 15, 21 ...     (not 6, 12, 18 — already even, skipped)
i=5: mark 25, 35, 45 ...
```

This halves both array size and iterations.

### Optimisation 2 — `isComposite` instead of `isPrime`
`boolean[]` in Java defaults to `false`. Using `isComposite` (false = prime) means no `Arrays.fill()` needed — the array is already correct on creation.

### Optimisation 3 — Overflow guard for `i*i`
When `i` is large, `i * i` can exceed `Integer.MAX_VALUE` and overflow to a negative number, causing the inner loop to run incorrectly. Casting to `long` before multiplying prevents this:
```java
if ((long) i * i < n)
```

---

## Comparison

| Approach | Time | Space | Notes |
|---|---|---|---|
| Brute force | O(n²) | O(1) | TLE on large n |
| Basic sieve | O(n log log n) | O(n) | Correct and fast |
| Optimised sieve | O(n log log n) | O(n/2) | ~2x faster in practice |

Big-O is identical for both sieves — the optimised version wins on constant factors (half the array, half the iterations).

---

## Why `2 * i` in the Inner Loop?

When `i = 3`, multiples are: `9, 12, 15, 18, 21 ...`
Even multiples (`12, 18`) are already composite — no need to mark them.
Stepping by `2*i` hits only odd multiples: `9, 15, 21 ...`

---

## Complexity

| | Value |
|---|---|
| Time  | O(n log log n) |
| Space | O(n/2) — only odd indices tracked |

---

## Solution

```java
public class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0;

        boolean[] isComposite = new boolean[n];

        int count = 1; // account for 2
        for (int i = 3; i < n; i += 2) {
            if (!isComposite[i]) {
                count++;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += 2 * i) {
                        isComposite[j] = true;
                    }
                }
            }
        }

        return count;
    }
}
```