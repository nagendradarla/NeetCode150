package LeetCode153;

/**
 * NeetCode #31 | LeetCode 153: Find Minimum in Rotated Sorted Array
 * Pattern: Binary Search | Difficulty: Medium
 *
 * Approach hint:
 * Compare mid with right: if nums[mid] > nums[hi], min is in right half; else left (including mid).
 *
 * Similar problems:
 *   - Find Minimum in Rotated Sorted Array II (LC 154)
 *   - Search in Rotated Sorted Array (LC 33)
 *
 * Problem:
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * - [4,5,6,7,0,1,2] if it was rotated 4 times.
 *
 * - [0,1,2,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * Example 3:
 *
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 *
 * Constraints:
 *
 * - n == nums.length
 *
 * - 1 <= n <= 5000
 *
 * - -5000 <= nums[i] <= 5000
 *
 * - All the integers of nums are unique.
 *
 * - nums is sorted and rotated between 1 and n times.
 *
 * Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        /* int min = nums[0]; // assume
        while(l<=r) {
            int mid = (l+r)>>>1;
            min = Math.min(min, nums[mid]);
            if (nums[l] <= nums[mid]) {
                min = Math.min(min, nums[l]);
                l = mid + 1;
            } else {
                r = mid - 1; 
            }
        }
        return min;
        */
        // Optimal solution
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 3, 4, 5, 1, 2 };
        System.out.println(sol.findMin(nums) == 1 ? "Test passed" : "Test failed");
        nums = new int[] { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(sol.findMin(nums) == 0 ? "Test passed" : "Test failed");
        nums = new int[] { 11, 13, 15, 17 };
        System.out.println(sol.findMin(nums) == 11 ? "Test passed" : "Test failed");
    }
}
