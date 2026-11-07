package LeetCode1296;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * LeetCode 1296: Divide Array in Sets of K Consecutive
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Greedy
 *
 * Problem:
 * (description unavailable)
 *
 * Link: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive/
 */
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0)
            return false;
        HashMap<Integer, Integer> numCount = new HashMap<Integer, Integer>();
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        for (int num : nums) {
            numCount.put(num, numCount.getOrDefault(num, 0) + 1);
            minHeap.offer(num);
        }

        while (!minHeap.isEmpty()) {
            // Lazy Deletion, instead of deleting in the for look below
            while (!minHeap.isEmpty() && numCount.get(minHeap.peek()) == 0) {
                minHeap.poll();
            }
            // If already traversed through all the elements
            if (minHeap.isEmpty()) {
                break;
            }
            int curr = minHeap.peek();
            //Check if each consecutive element exists
            for (int i = 0; i < k; i++) {
                int next = curr + i;
                if (numCount.get(next) == null || numCount.get(next) == 0)
                    return false;
                numCount.put(next, numCount.get(next) - 1);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("LC 1296: Divide Array in Sets of K Consecutive — implement Solution then add sample calls here.");
        System.out.println("Test case 1 " + (sol.isPossibleDivide(new int[]{1,2,3,3,4,4,5,6}, 4) ? "PASS" : "FAILED")); // true
        System.out.println("Test case 2 " + (sol.isPossibleDivide(new int[]{3,3,2,2,1,1}, 3) ? "PASS" : "FAILED")); // true
        System.out.println("Test case 3 " + (sol.isPossibleDivide(new int[]{1,2,3,4}, 3) ? "FAILED" : "PASS")); // false
        System.out.println("Test case 4 " + (sol.isPossibleDivide(new int[]{1,2,3,4,5,6}, 2) ? "PASS" : "FAILED")); // true
        System.out.println("Test case 5 " + (sol.isPossibleDivide(new int[]{1,2,3,4,5,6}, 3) ? "PASS" : "FAILED")); // true
        System.out.println("Test case 6 " + (sol.isPossibleDivide(new int[]{1,2,3,4,5,6}, 4) ? "FAILED" : "PASS")); // false
        System.out.println("Test case 7 " + (sol.isPossibleDivide(new int[]{1,2,3,4,5,6}, 5) ? "FAILED" : "PASS")); // false
        System.out.println("Test case 8 " + (sol.isPossibleDivide(new int[]{1,2,3,4,5,6}, 6) ? "PASS" : "FAILED")); // true
    }
}
