# 11. Container With Most Water

## Problem
Given an integer array `height` of length `n`, find two lines that together with the x-axis form a container that holds the most water. Return the maximum area.

**Area formula:** `min(height[L], height[R]) * (R - L)`

## Approach: Two Pointers (with skip optimization)

Start with the widest possible container: `left = 0`, `right = n - 1`.

**Key insight:** The shorter wall is always the bottleneck. Moving the taller wall inward can only decrease area (width shrinks, height can't improve). Moving the shorter wall gives a *chance* at a taller wall, potentially increasing area.

**Skip optimization:** After computing the area, instead of moving one step, skip over all walls that are `<=` the current shorter wall — they can't possibly produce a better area since both width and height would be worse or equal.

## Solution

```java
public int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int max = 0;

    while (left < right) {
        int hL = height[left];
        int hR = height[right];

        int h = (hL < hR) ? hL : hR;
        int currentArea = h * (right - left);

        if (currentArea > max) max = currentArea;

        // Skip walls that are shorter or equal — can't improve
        if (hL < hR) {
            while (left < right && height[left] <= hL) left++;
        } else {
            while (left < right && height[right] <= hR) right--;
        }
    }
    return max;
}
```

## Complexity
- **Time:** O(n) — each element visited at most once across all iterations
- **Space:** O(1)

## Key Lessons
- Never move both pointers at once — you skip valid pairs
- Always move the pointer at the **shorter** wall
- The skip optimization doesn't change worst-case complexity but reduces iterations in practice
- `mid` is irrelevant — the two pointers converge naturally, no midpoint needed

## Common Mistakes
- Moving both `left++` and `right--` every iteration (skips many pairs)
- Stopping at mid instead of letting pointers meet
- Forgetting to move pointers at all → infinite loop