package problems.p011_container_with_most_water;

class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {
            int hL = height[left];
            int hR = height[right];

            // Calculate height and width once
            int h = (hL < hR) ? hL : hR;
            int currentArea = h * (right - left);

            if (currentArea > max) max = currentArea;

            // Skipping logic: if the next wall is shorter or equal,
            // the area can't possibly be bigger. Skip them!
            if (hL < hR) {
                while (left < right && height[left] <= hL) left++;
            } else {
                while (left < right && height[right] <= hR) right--;
            }
        }
        return max;
    }
}