package LeetCode217;

import java.util.Set;

/**
 * NeetCode #1 | LeetCode 217: Contains Duplicate
 * Pattern: Arrays & Hashing | Difficulty: Easy
 *
 * Approach hint:
 * Use a HashSet while iterating. If a value is already in the set, return true. O(n) time / O(n) space.
 *
 * Similar problems:
 *   - Contains Duplicate II (LC 219)
 *   - Contains Duplicate III (LC 220)
 *
 * Problem:
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 *
 * Output: true
 *
 * Explanation:
 *
 * The element 1 occurs at the indices 0 and 3.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 *
 * Output: false
 *
 * Explanation:
 *
 * All elements are distinct.
 *
 * Example 3:
 *
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 *
 * Output: true
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 10^5
 *
 * - -10^9 <= nums[i] <= 10^9
 *
 * Link: https://leetcode.com/problems/contains-duplicate/
 */
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // Create a HashSet to store unique numbers
        Set<Integer> seen = new java.util.HashSet<>();
        // Iterate through the array
        for (int num : nums) {
            // If the number is already in the set, return true
            if (seen.contains(num)) {
                return true;
            }
            // Otherwise, add the number to the set
            seen.add(num);
        }
        // If no duplicates were found, return false
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.containsDuplicate(new int[]{1, 2, 3, 1})); // true
        System.out.println(sol.containsDuplicate(new int[]{1, 2, 3, 4})); // false
        System.out.println(sol.containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2})); // true
    }
}
