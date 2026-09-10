package LeetCode549;

/**
 * LeetCode 549: Longest Consecutive Sequence II (tree)
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Arrays & Hashing
 *
 * Problem:
 * (description unavailable)
 *
 * Link: https://leetcode.com/problems/longest-consecutive-sequence-ii-tree/
 */
class Solution {
    int maxLen;
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int longestConsecutive(TreeNode root) {
        maxLen = 0;
        dfs(root);
        return maxLen;
    }

    public int[] dfs (TreeNode node) {
        if (node == null) return new int[]{0, 0}; // {inc, dec}
        int inc = 1, dec = 1;
        if (node.left != null) {
            int[] left = dfs(node.left);
            if (node.val == node.left.val + 1) {
                dec += left[1];
                //dec = Math.max(dec, left[1]+1);
            } else if (node.val == node.left.val - 1) {
                inc += left[0];
                //inc = Math.max(inc, left[0]+1);
            }
        }
        if (node.right != null) {
            int[] right = dfs(node.right);
            if (node.val == node.right.val + 1) {
                dec += right[1];
                //dec = Math.max(dec, right[1]+1);
            } else if (node.val == node.right.val - 1) {
                inc += right[0];
                //inc = Math.max(inc, right[0]+1);
            }
        }
        maxLen = Math.max(maxLen, inc + dec - 1);
        return new int[]{inc, dec};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("LC 549: Longest Consecutive Sequence II (tree) — implement Solution then add sample calls here.");
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(sol.longestConsecutive(root) == 3 ? "PASS" : "FAIL"); // Expected output: 3 (the sequence is 1-2-3)
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(3);
        System.out.println(sol.longestConsecutive(root2) == 3 ? "PASS" : "FAIL"); // Expected output: 3 (the sequence is 1-2-3)
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(1);
        System.out.println(sol.longestConsecutive(root3) == 3 ? "PASS" : "FAIL"); // Expected output: 3 (the sequence is 3-2-1)
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(0);
        System.out.println(sol.longestConsecutive(root4) == 3 ? "PASS" : "FAIL"); // Expected output: 2 (the sequence is 1-2 or 1-0)
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(0);
        root5.right.right = new TreeNode(3);
        System.out.println(sol.longestConsecutive(root5) == 3 ? "PASS" : "FAIL"); // Expected output: 3 (the sequence is 3-2-1)
        System.out.println(sol.longestConsecutive(null) == 0 ? "PASS" : "FAIL"); // Expected output: 3 (the sequence is 3-2-1)
    }
}
