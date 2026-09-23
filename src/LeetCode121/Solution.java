package LeetCode121;

/**
 * NeetCode #15 | LeetCode 121: Best Time to Buy and Sell Stock
 * Pattern: Sliding Window | Difficulty: Easy
 *
 * Approach hint:
 * Track min price so far; max profit = price - minPrice. One pass sliding min.
 *
 * Similar problems:
 *   - Best Time to Buy and Sell Stock II (LC 122)
 *   - Best Time with Cooldown (LC 309)
 *
 * Problem:
 * You are given an array prices where prices[i] is the price of a given stock on the i^th day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Example 2:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * Constraints:
 *
 * - 1 <= prices.length <= 10^5
 *
 * - 0 <= prices[i] <= 10^4
 *
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
class Solution {
    public int maxProfit(int[] prices) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // TODO: add sample calls for Best Time to Buy and Sell Stock
        // Signature: public int maxProfit(int[] prices) {
        }
}
