# 15. 3Sum

## Approach — Sort + Two Pointers

Sort the array, then fix the first element with an outer loop and use two pointers (left, right) to find pairs that sum with it to zero. Skip duplicates at every level to avoid repeating triplets.

## Walkthrough

Given `nums = [-1, 0, 1, 2, -1, -4]` → sorted: `[-4, -1, -1, 0, 1, 2]`

| i  | nums[i] | left | right | sum | action              |
|----|---------|------|-------|-----|---------------------|
| 0  | -4      | 1    | 5     | -3  | sum < 0, left++     |
| 0  | -4      | 2    | 5     | -3  | sum < 0, left++     |
| 0  | -4      | 3    | 5     | -2  | sum < 0, left++     |
| 0  | -4      | 4    | 5     | -3  | sum < 0, left++     |
| 1  | -1      | 2    | 5     | 0   | found [-1,-1,2] ✓   |
| 1  | -1      | 3    | 4     | 0   | found [-1,0,1] ✓    |
| 2  | -1      | —    | —     | —   | duplicate, skip     |
| 3  | 0       | 4    | 5     | 3   | sum > 0, right--    |

Result: `[[-1,-1,2],[-1,0,1]]`

Duplicate skipping:
- Outer loop: `if (i > 0 && nums[i] == nums[i-1]) continue`
- After a match: advance left/right past duplicates before the `left++ right--`

## Complexity

- **Time**: O(n²) — O(n log n) sort + O(n²) two-pointer scan
- **Space**: O(1) extra (ignoring output list)

## Alternative Approach — HashSet

Fix two elements with nested loops, use a set to check if the complement exists. More intuitive but harder to deduplicate cleanly and still O(n²) time with higher constant.

## Common Mistakes

- Not sorting first — two-pointer only works on a sorted array
- Missing the `i > 0` guard on the outer duplicate check — would skip `i=0` incorrectly
- Not skipping inner duplicates after finding a match — produces repeated triplets
- Forgetting `nums[i] > 0` early-exit — harmless to omit but a clean optimization since a sorted array cannot have three positives summing to zero

## Key Insights

- Sorting reduces the 3-pointer search to a single sorted pass: fix one, two-pointer the rest
- Deduplication is done in-place by skipping equal adjacent values, avoiding any extra set
- The `nums[i] > 0` break is valid only because the array is sorted — all subsequent elements are also positive
