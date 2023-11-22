package leetcode.linkedlist;

/**
 * @Description: LinkedListNode
 * @Author: whj
 * @Date: 2023-11-21 21:35
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
