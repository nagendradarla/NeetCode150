package LeetCode122;

/**
 * LeetCode 122: Best Time to Buy and Sell Stock II
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Sliding Window
 *
 * Problem:
 * You are given an integer array prices where prices[i] is the price of a given stock on the i^th day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.
 *
 * Find and return the maximum profit you can achieve.
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 *
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 *
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 *
 * Constraints:
 *
 * - 1 <= prices.length <= 3 * 10^4
 *
 * - 0 <= prices[i] <= 10^4
 *
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * https://youtu.be/3SJ3pUkPQMc?si=_zUiuV6LTpmGAMTJ
 */
class Solution {
    public int maxProfit(int[] prices) {
        int profit=0;
        for (int i=1; i<prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.maxProfit(new int[]{7,1,5,3,6,4})==7 ? "PASS" : "FAIL"); // 7
        System.out.println(sol.maxProfit(new int[]{1,2,3,4,5})==4 ? "PASS" : "FAIL"); // 4
        System.out.println(sol.maxProfit(new int[]{7,6,4,3,1})==0 ? "PASS" : "FAIL"); // 0
    }
}
