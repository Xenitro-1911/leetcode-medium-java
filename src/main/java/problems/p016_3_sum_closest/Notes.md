# 16. 3Sum Closest

## Approach — Sort + Two Pointers with Pruning

Sort the array, fix an anchor `i`, then use two pointers to find the pair whose sum with `nums[i]` is closest to target. Track the best sum seen so far and update it whenever a closer sum is found.

Two pruning steps per anchor reduce unnecessary inner scans:
- If the **minimum possible** sum with this anchor already exceeds target, no later anchor can do better → break.
- If the **maximum possible** sum with this anchor is still below target, this anchor is too small → skip to next `i`.

## Walkthrough

Given `nums = [-1, 2, 1, -4]`, `target = 1` → sorted: `[-4, -1, 1, 2]`, `closestSum = -4`

| i | nums[i] | left | right | sum | diff to target | action         |
|---|---------|------|-------|-----|----------------|----------------|
| 0 | -4      | 1    | 3     | -3  | 4              | closest=-3, left++ |
| 0 | -4      | 2    | 3     | -1  | 2              | closest=-1, left++ |
| 1 | -1      | 2    | 3     | 2   | 1              | closest=2, right-- |

Result: `2`

## Complexity

- **Time**: O(n²) — O(n log n) sort + O(n²) two-pointer scan
- **Space**: O(1) extra

## Common Mistakes

- Initializing `closestSum` to 0 instead of a real triplet sum — breaks when all sums are negative
- Using `<` instead of `<` in the update condition — misses ties (though the problem guarantees a unique answer)
- Returning early on exact match inside the pruning block before checking the two-pointer path
- Skipping duplicates on the inner pointers incorrectly (off-by-one: compare to `left-1` after increment, `right+1` after decrement)

## Key Insights

- The pruning on min/max sum per anchor gives a meaningful speedup in practice even though worst-case stays O(n²)
- Unlike 3Sum, there is no need to skip duplicate anchors for correctness (the problem asks for a value, not unique triplets) — but skipping them avoids redundant work
- Returning `target` immediately on exact match is safe because there is guaranteed to be exactly one closest value
