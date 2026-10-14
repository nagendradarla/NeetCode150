package LeetCode853;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * NeetCode #26 | LeetCode 853: Car Fleet
 * Pattern: Stack | Difficulty: Medium
 *
 * Approach hint:
 * Sort by position desc. Time to target = (target-pos)/speed. Fleets merge if behind catches front.
 *
 * Similar problems:
 *   - Car Fleet II (LC 1776)
 *   - Asteroid Collision (LC 735)
 *
 * Problem:
 * There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.
 *
 * You are given two integer arrays position and speed, both of length n, where position[i] is the starting mile of the i^th car and speed[i] is the speed of the i^th car in miles per hour.
 *
 * A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.
 *
 * A car fleet is a single car or a group of cars driving next to each other. The speed of the car fleet is the minimum speed of any car in the fleet.
 *
 * If a car catches up to a car fleet at the mile target, it will still be considered as part of the car fleet.
 *
 * Return the number of car fleets that will arrive at the destination.
 *
 * Example 1:
 *
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 *
 * Output: 3
 *
 * Explanation:
 *
 * - The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12. The fleet forms at target.
 *
 * - The car starting at 0 (speed 1) does not catch up to any other car, so it is a fleet by itself.
 *
 * - The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 *
 * Example 2:
 *
 * Input: target = 10, position = [3], speed = [3]
 *
 * Output: 1
 *
 * Explanation:
 *
 * There is only one car, hence there is only one fleet.
 *
 * Example 3:
 *
 * Input: target = 100, position = [0,2,4], speed = [4,2,1]
 *
 * Output: 1
 *
 * Explanation:
 *
 * - The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The car starting at 4 (speed 1) travels to 5.
 *
 * - Then, the fleet at 4 (speed 2) and the car at position 5 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 *
 * Constraints:
 *
 * - n == position.length == speed.length
 *
 * - 1 <= n…
 *
 * Link: https://leetcode.com/problems/car-fleet/
 */
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0])); // Sort by position in descending order
        Deque<Double> stack = new ArrayDeque<Double>();
        for (double[] car : cars) {
            double time = (target - car[0]) / car[1];
            while (stack.isEmpty() || time > stack.peek()) {
                stack.push(time);
            }
        }
        return stack.size();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int target = 12;
        int[] position = { 10, 8, 0, 5, 3 };
        int[] speed = { 2, 4, 1, 1, 3 };
        System.out.println(sol.carFleet(target, position, speed) == 3 ? "Test 1 passed" : "Test 1 failed");
        target = 10;
        position = new int[] { 3 };
        speed = new int[] { 3 };
        System.out.println(sol.carFleet(target, position, speed) == 1 ? "Test 2 passed" : "Test 2 failed");
        target = 100;
        position = new int[] { 0, 2, 4 };
        speed = new int[] { 4, 2, 1 };
        System.out.println(sol.carFleet(target, position, speed) == 1 ? "Test 3 passed" : "Test 3 failed");
    }
}
