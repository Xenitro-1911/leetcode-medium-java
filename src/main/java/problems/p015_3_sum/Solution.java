package problems.p015_3_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 1. Sort the array first
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 2. Skip duplicate first numbers
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Optimization: if the smallest possible sum > 0, stop
            if (nums[i] > 0) break;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 3. Skip duplicate second numbers
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // 4. Skip duplicate third numbers
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}