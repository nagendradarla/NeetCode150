package LeetCode42;

/**
 * NeetCode #14 | LeetCode 42: Trapping Rain Water
 * Pattern: Two Pointers | Difficulty: Hard
 *
 * Approach hint:
 * Two pointers with leftMax/rightMax: water at i = min(leftMax,rightMax)-height[i]. Or prefix/suffix max arrays.
 *
 * Similar problems:
 *   - Container With Most Water (LC 11)
 *   - Trapping Rain Water II (LC 407)
 *
 * Problem:
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 * Example 1:
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 *
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * Constraints:
 *
 * - n == height.length
 *
 * - 1 <= n <= 2 * 10^4
 *
 * - 0 <= height[i] <= 10^5
 *
 * Link: https://leetcode.com/problems/trapping-rain-water/
 * https://www.youtube.com/watch?v=ZI2z5pq0TqA
 */
class Solution {
    public int trap(int[] height) {
        int size = height.length, res = 0;
        int l = 0, r = size - 1;
        int leftMax = height[l], rightMax = height[r];
        while (l < r) { // Base condition for traversal: left pointer < right pointer
            if (height[l] <= height[r]) {
                res += Math.min(leftMax, rightMax) - height[l];
                l++;
                leftMax = Math.max(leftMax, height[l]);
            } else {
                res += Math.min(leftMax, rightMax) - height[r];
                r--;
                rightMax = Math.max(rightMax, height[r]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Sample test cases
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(sol.trap(height1) == 6 ? "PASS" : "FAIL"); // Expected output: 6
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println(sol.trap(height2) == 9 ? "PASS" : "FAIL"); // Expected output: 9
    }

}
