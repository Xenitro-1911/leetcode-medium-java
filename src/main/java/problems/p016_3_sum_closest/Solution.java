package problems.p016_3_sum_closest;

import java.util.Arrays;

import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            // 1. Skip duplicate anchors
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 2. PRUNING: Smallest sum possible with this anchor
            int minSum = nums[i] + nums[i + 1] + nums[i + 2];
            if (minSum > target) {
                if (Math.abs(minSum - target) < Math.abs(closestSum - target)) closestSum = minSum;
                break; // All subsequent triplets will be even further from target
            }

            // 3. PRUNING: Largest sum possible with this anchor
            int maxSum = nums[i] + nums[n - 2] + nums[n - 1];
            if (maxSum < target) {
                if (Math.abs(maxSum - target) < Math.abs(closestSum - target)) closestSum = maxSum;
                continue; // This anchor is too small, move to the next 'i'
            }

            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) return target; // Found exact match

                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                if (sum < target) {
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) left++; // Skip duplicates
                } else {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) right--; // Skip duplicates
                }
            }
        }
        return closestSum;
    }
}
