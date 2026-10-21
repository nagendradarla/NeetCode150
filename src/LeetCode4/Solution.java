package LeetCode4;

/**
 * NeetCode #34 | LeetCode 4: Median of Two Sorted Arrays
 * Pattern: Binary Search | Difficulty: Hard
 *
 * Approach hint:
 * Binary search partition on smaller array so left halves ≤ right halves. O(log(min(m,n))).
 *
 * Similar problems:
 *   - Kth Element of Two Sorted Arrays (LC 4)
 *   - Find K-th Smallest Pair Distance (LC 719)
 *
 * Problem:
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Constraints:
 *
 * - nums1.length == m
 *
 * - nums2.length == n
 *
 * - 0 <= m <= 1000
 *
 * - 0 <= n <= 1000
 *
 * - 1 <= m + n <= 2000
 *
 * - -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * Link: https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //for time complexity of O(log(min(m,n))), we have to use smaller array to divide using binary search
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int half = (m + n + 1) / 2; // +1 is to have clear division incase the sume (m+n) is odd
        int l = 0, r = m; // We are using r=n because this represents the number of elements to pick from nums1, l=0 means zero elements, l = 1 means only element
        while (l <= r) {
            int i = (l + r) >>> 1; // num of elements from nums1
            int j = half - i; // num of elements from nums2
            int leftPartitionX = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int rightPartitionX = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int leftPartitionY = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int rightPartitionY = (j == n) ? Integer.MAX_VALUE : nums2[j];
            if (leftPartitionX <= rightPartitionY && leftPartitionY <= rightPartitionX) {
                if ((m + n) % 2 == 0) {
                    return (double) (Math.max(leftPartitionX, leftPartitionY)
                            + Math.min(rightPartitionX, rightPartitionY)) / 2;
                } else {
                    // For odd sum of (m+n) the median stays on the left side of the partition because we used (m+n+1)/2 to get the left half.
                    return (double) Math.max(leftPartitionX, leftPartitionY);
                }
            } else if (leftPartitionX > rightPartitionY) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = { 1, 3 };
        int[] nums2 = { 2 };
        System.out.println(sol.findMedianSortedArrays(nums1, nums2) == 2.0 ? "Pass" : "Fail");
        nums1 = new int[] { 1, 2 };
        nums2 = new int[] { 3, 4 };
        System.out.println(sol.findMedianSortedArrays(nums1, nums2) == 2.5 ? "Pass" : "Fail");
    }
}
