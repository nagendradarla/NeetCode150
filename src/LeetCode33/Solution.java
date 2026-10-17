package LeetCode33;

/**
 * NeetCode #32 | LeetCode 33: Search in Rotated Sorted Array
 * Pattern: Binary Search | Difficulty: Medium
 *
 * Approach hint:
 * One sorted half each step. Decide which half is sorted; check if target lies there.
 *
 * Similar problems:
 *   - Search in Rotated Sorted Array II (LC 81)
 *   - Find Minimum in Rotated Sorted Array (LC 153)
 *
 * Problem:
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 5000
 *
 * - -10^4 <= nums[i] <= 10^4
 *
 * - All values of nums are unique.
 *
 * - nums is an ascending array that is possibly rotated.
 *
 * - -10^4 <= target <= 10^4
 *
 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] == target)
                return mid;
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 0;
        System.out.println(sol.search(nums, target) == 4 ? "Test passed" : "Test failed");
        nums = new int[] { 4, 5, 6, 7, 0, 1, 2 };
        target = 3;
        System.out.println(sol.search(nums, target) == -1 ? "Test passed" : "Test failed");
        nums = new int[] { 1 };
        target = 0;
        System.out.println(sol.search(nums, target) == -1 ? "Test passed" : "Test failed");
        nums = new int[] { 1 };
        target = 1;
        System.out.println(sol.search(nums, target) == 0 ? "Test passed" : "Test failed");
        nums = new int[] { 5, 1, 3 };
        target = 5;
        System.out.println(sol.search(nums, target) == 0 ? "Test passed" : "Test failed");
        nums = new int[] { 4, 5, 6, 7, 0, 1, 2 };
        target = 3;
        System.out.println(sol.search(nums, target) == -1 ? "Test passed" : "Test failed");
    }
}
