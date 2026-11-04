package LeetCode846;

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
        // TODO
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // TODO: add sample calls for Hand of Straights
        // Signature: public boolean isNStraightHand(int[] hand, int groupSize) {
        }
}
