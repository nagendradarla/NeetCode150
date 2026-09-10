package LeetCode128;

import java.util.HashSet;
import java.util.Set;

/**
 * NeetCode #9 | LeetCode 128: Longest Consecutive Sequence
 * Pattern: Arrays & Hashing | Difficulty: Medium
 * <p>
 * Approach hint:
 * Put nums in a HashSet. Only start counting from numbers with no left neighbor (n-1 missing).
 * <p>
 * Similar problems:
 * - Longest Consecutive Sequence II (tree) (LC 549)
 * - Maximum Consecutive Floors (LC 2274)
 * <p>
 * Problem:
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,0,1,2]
 * Output: 3
 * <p>
 * Constraints:
 * <p>
 * - 0 <= nums.length <= 10^5
 * <p>
 * - -10^9 <= nums[i] <= 10^9
 * <p>
 * Link: https://leetcode.com/problems/longest-consecutive-sequence/
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int n : nums) {
            numSet.add(n);
        }
        int longestLength = 0;
        int currLength = 0;
        for (int num : numSet) {
            if (numSet.contains(num - 1)) {
                continue; // num is not the start of the sequence
            }
            currLength = 1;
            while (numSet.contains(num + 1)) { // Found the sequnce and continuing
                currLength++;
                num++;
            }
            longestLength = Math.max(longestLength, currLength);
        }
        return longestLength;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int length = sol.longestConsecutive(new int[]{1, 2, 3, 4, 5});
        if (length == 5) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }

        length = sol.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}); // expected: 4
        if (length == 4) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }
        length = sol.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}); // expected: 9
        if (length == 9) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }
        length = sol.longestConsecutive(new int[]{1, 0, 1, 2}); // expected: 3
        if (length == 3) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }
        length = sol.longestConsecutive(new int[]{1, 2, 0, 1}); // expected: 3
        if (length == 3) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }
        length = sol.longestConsecutive(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}); // expected: 7
        if (length == 7) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }
        length = sol.longestConsecutive(new int[]{0, 0, -1}); // expected: 2
        if (length == 2) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }
        length = sol.longestConsecutive(new int[]{0, 0, 0}); // expected: 1
        if (length == 1) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }
        length = sol.longestConsecutive(new int[]{0, 0, 1}); // expected: 2
        if (length == 2) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }
        length = sol.longestConsecutive(new int[]{0, 0, 1, 2}); // expected: 3
        if (length == 3) {
            System.out.println("PASS");
        }  else {
            System.out.println("FAIL");
        }
    }

}
