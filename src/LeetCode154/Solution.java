package LeetCode154;

/**
 * LeetCode 154: Find Minimum in Rotated Sorted Array II
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Binary Search
 *
 * Problem:
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 * - [4,5,6,7,0,1,4] if it was rotated 4 times.
 *
 * - [0,1,4,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 * Example 1:
 *
 * Input: nums = [1,3,5]
 * Output: 1
 *
 * Example 2:
 *
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 *
 * Constraints:
 *
 * - n == nums.length
 *
 * - 1 <= n <= 5000
 *
 * - -5000 <= nums[i] <= 5000
 *
 * - nums is sorted and rotated between 1 and n times.
 *
 * Follow up: This problem is similar to Find Minimum in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 *
 * Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            while (l < r && nums[l] == nums[l + 1]) {
                l++;
            }
            while (l < r && nums[r] == nums[r - 1]) {
                r--;
            }
            int mid = (l + r) >>> 1;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
        /*while (l < r) {
            int mid = (l+r) >>> 1;
            if (nums[mid] > nums[r]) {
                //min is in right half
                l = mid+1;
            } else if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                r--;
            }
        }
        return nums[l];*/
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
