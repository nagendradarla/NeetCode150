package LeetCode875;

/**
 * NeetCode #30 | LeetCode 875: Koko Eating Bananas
 * Pattern: Binary Search | Difficulty: Medium
 *
 * Approach hint:
 * Binary search speed k in [1, max(pile)]. Feasible if hoursNeeded(k) <= h.
 *
 * Similar problems:
 *   - Capacity To Ship Packages (LC 1011)
 *   - Minimum Number of Days to Make m Bouquets (LC 1482)
 *
 * Problem:
 * Koko loves to eat bananas. There are n piles of bananas, the i^th pile has piles[i] bananas. The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 * Constraints:
 *
 * - 1 <= piles.length <= 10^4
 *
 * - piles.length <= h <= 10^9
 *
 * - 1 <= piles[i] <= 10^9
 *
 * Link: https://leetcode.com/problems/koko-eating-bananas/
 */
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int i = 1, j = 0, mid = 0, k = 0;
        for (int pile : piles) {
            k = Math.max(k, pile);
        }
        j = k;
        while (i <= j) {
            mid = (i+j) >>>1 ;
            if (canFinish(piles, h, mid)) {
                k = Math.min(k, mid);
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return k;
    }

    private boolean canFinish(int[] piles, int h, int k) {
        int i = 0;
        for (int pile : piles) {
            i += Math.ceil((double) pile / k);
        }
        return i <= h;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] piles1 = {3,6,7,11};
        int h1 = 8;
        System.out.println(sol.minEatingSpeed(piles1, h1) == 4 ? "PASS" : "FAIL"); // Output: 4

        int[] piles2 = {30,11,23,4,20};
        int h2 = 5;
        System.out.println(sol.minEatingSpeed(piles2, h2) == 30 ? "PASS" : "FAIL"); // Output: 30

        int[] piles3 = {30,11,23,4,20};
        int h3 = 6;
        System.out.println(sol.minEatingSpeed(piles3, h3) == 23 ? "PASS" : "FAIL"); // Output: 23
    }
}
