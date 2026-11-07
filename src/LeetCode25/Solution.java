package LeetCode25;

/**
 * NeetCode #45 | LeetCode 25: Reverse Nodes in k-Group
 * Pattern: Linked List | Difficulty: Hard
 *
 * Approach hint:
 * Count k nodes; reverse that segment; recurse/iterate for next groups. Leave remainder as-is.
 *
 * Similar problems:
 *   - Swap Nodes in Pairs (LC 24)
 *   - Reverse Linked List II (LC 92)
 *
 * Problem:
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Example 2:
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Constraints:
 *
 * - The number of nodes in the list is n.
 *
 * - 1 <= k <= n <= 5000
 *
 * - 0 <= Node.val <= 1000
 *
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 *
 * Link: https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;
        while (true) {
            // Get kth element
            head = groupPrev.next;
            ListNode kth = getKthNode(head, k);
            // If kth element is null, break the loop and exit
            if (kth == null) {
                break;
            }
            ListNode groupNext = kth.next;
            // reverse the sub group of size 'k'
            ListNode prev = groupNext; // important move
            ListNode curr = head;
            while (curr != groupNext) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }

            // connect the reversed sub group to the linked list
            ListNode tailOfGroup = kth;
            groupPrev.next = tailOfGroup;
            groupPrev = head;
            head = curr;
        }
        return dummy.next;
    }

    private ListNode getKthNode(ListNode node, int k) {
        ListNode n = node;
        while (n != null && k > 1) {
            n = n.next;
            k--;
        }
        return n;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k = 2;
        System.out.println("Before reversing (k = 2): " + printList(head));
        ListNode result = sol.reverseKGroup(head, k);
        System.out.println("After reversing (k = 2): " + printList(result));

        head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        k = 3;
        System.out.println("Before reversing (k = 3): " + printList(head));
        result = sol.reverseKGroup(head, k);
        System.out.println("After reversing (k = 3): " + printList(result));
    }

    private static String printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode current = this;
        sb.append("[");
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
