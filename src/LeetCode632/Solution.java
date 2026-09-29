package LeetCode632;

import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 632: Smallest Range Covering Elements from K Lists
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Sliding Window
 *
 * Problem:
 * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 *
 * Example 1:
 *
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 *
 * Example 2:
 *
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 *
 * Constraints:
 *
 * - nums.length == k
 *
 * - 1 <= k <= 3500
 *
 * - 1 <= nums[i].length <= 50
 *
 * - -10^5 <= nums[i][j] <= 10^5
 *
 * - nums[i] is sorted in non-decreasing order.
 *
 * Link: https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 */
class Solution {
    public static class Element implements Comparable<Element> {
        int val;
        int listIdx;
        int elmIdx;

        public Element(int val, int listIdx, int elmIdx) {
            this.val = val;
            this.listIdx = listIdx;
            this.elmIdx = elmIdx;
        }

        public int compareTo(Element that) {
            return Integer.compare(this.val, that.val);
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        int maxValue = Integer.MIN_VALUE;
        int range = Integer.MAX_VALUE;
        int[] res = null;
        PriorityQueue<Element> pq = new PriorityQueue<Element>();
        for (int i=0; i<nums.size(); i++) {
            int val = nums.get(i).get(0);
            pq.offer(new Element(val, i, 0));
            maxValue = Math.max(maxValue, val);
        }
        while (true) {
            Element e = pq.poll();
            if (maxValue - e.val < range) {
                range = maxValue - e.val;
                res = new int[] {e.val, maxValue};
            }
            if (nums.get(e.listIdx).size()-1 == e.elmIdx) { // end of list
                return res;
            }
            int nextVal = nums.get(e.listIdx).get(e.elmIdx+1);
            pq.offer(new Element(nextVal, e.listIdx, e.elmIdx+1));
            maxValue = Math.max(maxValue, nextVal);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<Integer>> nums1 = List.of(
                List.of(4,10,15,24,26),
                List.of(0,9,12,20),
                List.of(5,18,22,30)
        );
        int[] res1 = sol.smallestRange(nums1);
        //System.out.println("Smallest range: [" + res1[0] + ", " + res1[1] + "]"); // Expected: [20, 24]
        if (res1.length == 2 && res1[0] == 20 && res1[1] == 24) {
            System.out.println("Test case 1 passed.");
        } else {
            System.out.println("Test case 1 failed.");
        }

        List<List<Integer>> nums2 = List.of(
                List.of(1,2,3),
                List.of(1,2,3),
                List.of(1,2,3)
        );
        int[] res2 = sol.smallestRange(nums2);
        //System.out.println("Smallest range: [" + res2[0] + ", " + res2[1] + "]"); // Expected: [1, 1]
        if (res2.length == 2 && res2[0] == 1 && res2[1] == 1) {
            System.out.println("Test case 2 passed.");
        } else {
            System.out.println("Test case 2 failed.");
        }
    }
}
