package LeetCode18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 18: 4Sum
 * Related / similar problem from Day 2 NeetCode practice.
 * Pattern: Arrays & Hashing
 *
 * Problem:
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * - 0 <= a, b, c, d < n
 *
 * - a, b, c, and d are distinct.
 *
 * - nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 * Constraints:
 *
 * - 1 <= nums.length <= 200
 *
 * - -10^9 <= nums[i] <= 10^9
 *
 * - -10^9 <= target <= 10^9
 *
 * Link: https://leetcode.com/problems/4sum/
 */
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        kSum(nums, 0, nums.length - 1, target, 4, new ArrayList<Integer>());
        return res;
    }

    // tar should be long to avoid overflow.
    private void kSum(int[] nums, int l, int r, long tar, int k, List<Integer> quad) {
        if (k == 2) {
            while (l < r) {
                long sum = (long)nums[l] + nums[r]; // cast to long to avoid int overflow
                if (sum == tar) {
                    List<Integer> newQuad = new ArrayList<Integer>(quad);
                    newQuad.add(nums[l]);
                    newQuad.add(nums[r]);
                    res.add(newQuad);
                    l++;
                    r--;
                    while (l<r && nums[l] == nums[l-1]) l++;
                    while (l<r && nums[r] == nums[r+1]) r--;
                } else if (sum < tar) {
                    l++;
                } else {
                    r--;
                }
            }
        } else {
            for (int i = l; i <= nums.length - k; i++) {
                if (i > l && nums[i] == nums[i - 1]) {
                    continue;
                }
                long t = (long) tar - nums[i]; // cast to long to avoid int overflow
                quad.add(nums[i]);
                kSum(nums, i + 1, r, t, k - 1, quad);
                quad.remove(quad.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        // expected: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
        System.out.println(sol.fourSum(new int[]{2, 2, 2, 2, 2}, 8));
        // expected: [[2, 2, 2, 2]]
    }
}
