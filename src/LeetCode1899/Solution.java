package LeetCode1899;

import java.util.ArrayList;
import java.util.List;

/**
 * NeetCode #127 | LeetCode 1899: Merge Triplets to Form Target Triplet
 * Pattern: Greedy | Difficulty: Medium
 *
 * Approach hint:
 * Keep triplets that don't exceed target in any dim; check if each target dim is achieved.
 *
 * Similar problems:
 *   - Largest Combination With Bitwise AND (LC 2275)
 *   - Merge Intervals (LC 56)
 *
 * Problem:
 * A triplet is an array of three integers. You are given a 2D integer array triplets, where triplets[i] = [ai, bi, ci] describes the i^th triplet. You are also given an integer array target = [x, y, z] that describes the triplet you want to obtain.
 *
 * To obtain target, you may apply the following operation on triplets any number of times (possibly zero):
 *
 * - Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
 *
 * - For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be updated to [max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5].
 *
 * Return true if it is possible to obtain the target triplet [x, y, z] as an element of triplets, or false otherwise.
 *
 * Example 1:
 *
 * Input: triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
 * Output: true
 * Explanation: Perform the following operations:
 * - Choose the first and last triplets [[2,5,3],[1,8,4],[1,7,5]]. Update the last triplet to be [max(2,1), max(5,7), max(3,5)] = [2,7,5]. triplets = [[2,5,3],[1,8,4],[2,7,5]]
 * The target triplet [2,7,5] is now an element of triplets.
 *
 * Example 2:
 *
 * Input: triplets = [[3,4,5],[4,5,6]], target = [3,2,5]
 * Output: false
 * Explanation: It is impossible to have [3,2,5] as an element because there is no 2 in any of the triplets.
 *
 * Example 3:
 *
 * Input: triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
 * Output: true
 * Explanation: Perform the following operations:
 * - Choose the first and third triplets [[2,5,3],[2,3,4],[1,2,5],[5,2,3]]. Update the third triplet to be [max(2,1), max(5,2), max(3,5)] = [2,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,2,3]].
 * - Choose the third and fourth triplets [[2,5,3],[2,3,4],[2,5,5],[5,2,3]]. Update the fourth triplet to be [max(2,5), max(5,2), max(5,3)] = [5,5,5]. triplets = [[2,5,3…
 *
 * Link: https://leetcode.com/problems/merge-triplets-to-form-target-triplet/
 */
class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        List<int[]> result = new ArrayList<int[]>();
        boolean x = false, y = false, z = false;
        for (int[] triplet : triplets) {
            // If any element in the triplet is greater than zero, skip it as it overshoots the element value corresponding to target element
            if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
                continue;
            }
            if (triplet[0] == target[0] || x) {
                x = true;
            }
            if (triplet[1] == target[1] || y) {
                y = true;
            }
            if (triplet[2] == target[2] || z) {
                z = true;
            }
            // Optimization : Early exist
            if (x && y && z) {
                return true;
            }
        }
        return x && y && z;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.mergeTriplets(new int[][]{{2,5,3},{1,8,4},{1,7,5}}, new int[]{2,7,5}) ? "PASS" : "FAIL"); // true
        System.out.println(sol.mergeTriplets(new int[][]{{3,4,5},{4,5,6}}, new int[]{3,2,5}) ? "FAIL" : "PASS"); // false
        System.out.println(sol.mergeTriplets(new int[][]{{2,5,3},{2,3,4},{1,2,5},{5,2,3}}, new int[]{5,5,5}) ? "PASS" : "FAIL"); // true
    }
}
