package LeetCode134;

/**
 * NeetCode #125 | LeetCode 134: Gas Station
 * Pattern: Greedy | Difficulty: Medium
 *
 * Approach hint:
 * If total gas>=cost, unique start exists. Reset start when tank goes negative.
 *
 * Similar problems:
 *   - Complete Circuit variants (LC 134)
 *   - Jump Game (LC 55)
 *
 * Problem:
 * There are n gas stations along a circular route, where the amount of gas at the i^th station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the i^th station to its next (i + 1)^th station. You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.
 *
 * Example 1:
 *
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 *
 * Example 2:
 *
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 * Constraints:
 *
 * - n == gas.length == cost.length
 *
 * - 1 <= n <= 10^5
 *
 * - 0 <= gas[i], cost[i] <= 10^4
 *
 * - The input is generated such that the answer is unique.
 *
 * Link: https://leetcode.com/problems/gas-station/
 */
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int n = gas.length;
        int startIdx = 0; // default should be 0
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        //if total gas is less than required cost then return -1
        if (totalGas < totalCost)
            return -1;
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += gas[i] - cost[i];
            // if the total is '0' you can still go to next station. 
            // See the problme description : 
            // cost[i] of gas to travel from the ith station to its next (i + 1)th station
            if (total < 0) {
                total = 0;
                startIdx = i + 1;
            }
        }
        return startIdx;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] gas = { 1, 2, 3, 4, 5 };
        int[] cost = { 3, 4, 5, 1, 2 };
        System.out.println(sol.canCompleteCircuit(gas, cost) == 3 ? "Test Case 1 Passed" : "Test Case 1 Failed");
        int[] gas2 = { 2, 3, 4 };
        int[] cost2 = { 3, 4, 3 };
        System.out.println(sol.canCompleteCircuit(gas2, cost2) == -1 ? "Test Case 2 Passed" : "Test Case 2 Failed");
        int[] gas3 = { 3, 1, 1 };
        int[] cost3 = { 1, 2, 2 };
        System.out.println(sol.canCompleteCircuit(gas3, cost3) == 0 ? "Test Case 3 Passed" : "Test Case 3 Failed");
        int[] gas4 = { 1, 2, 3, 4, 5 };
        int[] cost4 = { 3, 4, 5, 1, 2 };
        System.out.println(sol.canCompleteCircuit(gas4, cost4) == 3 ? "Test Case 4 Passed" : "Test Case 4 Failed");
        int[] gas5 = { 2, 3, 4 };
        int[] cost5 = { 3, 4, 3 };
        System.out.println(sol.canCompleteCircuit(gas5, cost5) == -1 ? "Test Case 5 Passed" : "Test Case 5 Failed");
    }
}
