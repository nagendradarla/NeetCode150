package LeetCode704;

/**
 * NeetCode #28 | LeetCode 704: Binary Search
 * Pattern: Binary Search | Difficulty: Easy
 *
 * Approach hint:
 * Classic mid = lo+(hi-lo)/2. Shrink half until found or lo>hi.
 *
 * Similar problems:
 *   - Search Insert Position (LC 35)
 *   - First Bad Version (LC 278)
 *
 * Problem:
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 *
 * Example 2:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 10^4
 *
 * - -10^4 < nums[i], target < 10^4
 *
 * - All the integers in nums are unique.
 *
 * - nums is sorted in ascending order.
 *
 * Link: https://leetcode.com/problems/binary-search/
 */
class Solution {
    public int search(int[] nums, int target) {
        int i = 0, j = nums.length - 1, mid = 0;
        while (i <= j) {
            mid = i + (j - i) / 2;
            //mid = (i+j)>>>1; using bit shift operator
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { -1, 0, 3, 5, 9, 12 };
        int target = 9;
        System.out.println(sol.search(nums, target) == 4 ? "Test 1 passed" : "Test 1 failed");
        nums = new int[] { -1, 0, 3, 5, 9, 12 };
        target = 2;
        System.out.println(sol.search(nums, target) == -1 ? "Test 2 passed" : "Test 2 failed");
    }
}
