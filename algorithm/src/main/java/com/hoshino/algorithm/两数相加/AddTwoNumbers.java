package com.hoshino.algorithm.两数相加;

/**
 * @author Yy_hoshino
 * @date 2021-07-21 0:43
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() { }
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode list = new ListNode(0);
        int res = 0;
        while(l1.next != null || l2.next != null) {
            int sum = l1.val + l2.val;
            if(res == 1) {
                sum += 1;
            }
            list.next.val = sum;
            if (sum > 9) {
                res = 1;
            } else {
                res = 0;
            }
        }
        return list;
    }
}
