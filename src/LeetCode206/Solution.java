package LeetCode206;

/**
 * NeetCode #35 | LeetCode 206: Reverse Linked List
 * Pattern: Linked List | Difficulty: Easy
 *
 * Approach hint:
 * Iterative: prev/curr/next pointers. Or recursive reverse of rest then link.
 *
 * Similar problems:
 *   - Reverse Linked List II (LC 92)
 *   - Palindrome Linked List (LC 234)
 *
 * Problem:
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Example 2:
 *
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 * Constraints:
 *
 * - The number of nodes in the list is the range [0, 5000].
 *
 * - -5000 <= Node.val <= 5000
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 * Link: https://leetcode.com/problems/reverse-linked-list/
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // TODO: add sample calls for Reverse Linked List
        // Signature: public ListNode reverseList(ListNode head) {
        }
}



// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
