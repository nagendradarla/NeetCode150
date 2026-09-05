package LeetCode238;

/**
 * NeetCode #6 | LeetCode 238: Product of Array Except Self
 * Pattern: Arrays & Hashing | Difficulty: Medium
 *
 * Approach hint:
 * Prefix products left→right, then multiply by suffix products right→left. O(n) without division.
 *
 * Similar problems:
 *   - Trapping Rain Water (LC 42)
 *   - Product of the Last K Numbers (LC 1352)
 *
 * Problem:
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 *
 * - 2 <= nums.length <= 10^5
 *
 * - -30 <= nums[i] <= 30
 *
 * - The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 *
 * Link: https://leetcode.com/problems/product-of-array-except-self/
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] leftCum = new int[length];
        int[] rightCum = new int[length];
        int i=0, j=length-1;
        while (i<length && j >=0) {
            if (i==0) {
                leftCum[i] = nums[i];
            }
            if (j==length-1) {
                rightCum[j] = nums[j];
            }
            if (i>0 || j<length-1) {
                leftCum[i] = leftCum[i-1] * nums[i];
                rightCum[j] = rightCum[j+1] * nums[j];
            }
            i++;
            j--;
        }
        //System.out.println("leftCum" + Arrays.toString(leftCum));
        //System.out.println("rightCum" + Arrays.toString(rightCum));
        int[] result = new int[length];
        for (int k=0; k<length; k++) {
            if (k==0) {
                result[k] = rightCum[k+1];
            } else if (k==length-1) {
                result[k] = leftCum[k-1];
            } else {
                result[k] = leftCum[k-1] * rightCum[k+1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // LeetCode examples
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{1, 2, 3, 4})));           // [24, 12, 8, 6]
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{-1, 1, 0, -3, 3})));    // [0, 0, 9, 0, 0]

        // Minimum length
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{2, 3})));                 // [3, 2]

        // Zeros
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{1, 0, 2})));              // [0, 2, 0]
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{0, 0, 1, 2})));           // [0, 0, 0, 0]

        // All negatives
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{-2, -3, -4})));           // [12, 8, 6]

        // Mixed signs
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{2, -3, 4})));             // [-12, 8, -6]

        // Uniform values
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{1, 1, 1, 1})));           // [1, 1, 1, 1]
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{-1, -1, -1})));           // [1, 1, 1]

        // Boundary values (constraint: -30 <= nums[i] <= 30)
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{30, -30})));              // [-30, 30]
        System.out.println(java.util.Arrays.toString(sol.productExceptSelf(new int[]{30, 30, 30})));           // [900, 900, 900]
    }
}
