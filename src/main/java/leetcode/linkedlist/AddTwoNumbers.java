package leetcode.linkedlist;

/**
 * @Description: https://leetcode.cn/problems/add-two-numbers/
 * @Author: whj
 * @Date: 2023-11-21 21:33
 */
public class AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode h1, ListNode h2) {
        ListNode ans = null, cur = null;
        int carry = 0;
        for (int sum, val; h1 != null || h2 != null; ) {
            sum = carry + (h1 != null ? h1.val : 0) + (h2 != null ? h2.val : 0);
            val = sum % 10;
            //这个进位是给下一个node用的
            carry = sum / 10;
            if (ans == null) {
                ans = new ListNode(val);
                cur = ans;
            } else {
                cur.next = new ListNode(val);
                cur = cur.next;
            }
            h1 = h1 != null ? h1.next : null;
            h2 = h2 != null ? h2.next : null;
        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return ans;
    }

}

