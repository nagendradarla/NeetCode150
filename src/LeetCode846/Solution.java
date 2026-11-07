package LeetCode846;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * NeetCode #126 | LeetCode 846: Hand of Straights
 * Pattern: Greedy | Difficulty: Medium
 *
 * Approach hint:
 * TreeMap counts; repeatedly take groupSize consecutive starting at min key.
 *
 * Similar problems:
 *   - Divide Array in Sets of K Consecutive (LC 1296)
 *   - Split Array into Consecutive Subsequences (LC 659)
 *
 * Problem:
 * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
 *
 * Given an integer array hand where hand[i] is the value written on the i^th card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 *
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], groupSize = 4
 * Output: false
 * Explanation: Alice's hand can not be rearranged into groups of 4.
 *
 * Constraints:
 *
 * - 1 <= hand.length <= 10^4
 *
 * - 0 <= hand[i] <= 10^9
 *
 * - 1 <= groupSize <= hand.length
 *
 * Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 *
 * Link: https://leetcode.com/problems/hand-of-straights/
 */
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        HashMap<Integer, Integer> handCount = new HashMap<Integer, Integer>();
        for (int h : hand) {
            minHeap.offer(h);
            handCount.put(h, handCount.getOrDefault(h, 0)+1);
        }
        while(!minHeap.isEmpty()) {
            // Update the minHeap, poll the min values whose count is 0;
            while (!minHeap.isEmpty() && handCount.get(minHeap.peek()) == 0) {
                minHeap.poll();
            }
            //If the minHeap is empty before finding the next set of consecutive
            // integers, exit from the loop. You can also return true.
            if (minHeap.isEmpty()) {
                break;
            }
            int curr = minHeap.peek();
            for (int i=0; i<groupSize; i++) {
                int card = curr + i;
                //Check if current / next consecutive integer is present.
                //If the next consecutive integer is in the map, it will be there in minheap as well.
                if (handCount.get(card) == null || handCount.get(card) == 0) return false;
                handCount.put(card, handCount.get(card) - 1);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("LC 846: Hand of Straights — implement Solution then add sample calls here.");
        System.out.println("Test case 1 " + (sol.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3) ? "PASS" : "FAILED")); // true
        System.out.println("Test case 2 " + (sol.isNStraightHand(new int[]{1,2,3,4,5}, 4) ? "FAILED" : "PASS")); // false
    }
}
