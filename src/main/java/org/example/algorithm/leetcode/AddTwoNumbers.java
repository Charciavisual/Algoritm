package org.example.algorithm.leetcode;

/**
 * Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 *
 * @author Changhee Choi
 * @since 28/08/2020
 */
public class AddTwoNumbers {
    class ListNode {
        private int val;
        private ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curL1 = l1, curL2 = l2;
        ListNode head = new ListNode(); //dummy
        ListNode tail = head;
        int carry = 0;

        while (curL1 != null && curL2 != null) {
            int val = curL1.val + curL2.val + carry;
            tail.next = new ListNode(val % 10);

            tail = tail.next;
            curL1 = curL1.next;
            curL2 = curL2.next;

            carry = val / 10;
        }

        if (curL1 == null) {
            while (curL2 != null) {
                int val = curL2.val + carry;
                tail.next = new ListNode(val % 10);
                tail = tail.next;
                curL2 = curL2.next;
                carry = val / 10;
            }
        } else {
            while (curL1 != null) {
                int val = curL1.val + carry;
                tail.next = new ListNode(val % 10);
                tail = tail.next;
                curL1 = curL1.next;
                carry = val / 10;
            }
        }

        if (carry == 1) {
            tail.next = new ListNode(carry);
        }

        return head.next;
    }

    public void runTest(int[] num1, int[] num2) {
        ListNode l1Head = new ListNode(), l2Head = new ListNode();
        ListNode l1Tail = l1Head, l2Tail = l2Head;

        for (int num : num1) {
            l1Tail.next = new ListNode(num);
            l1Tail = l1Tail.next;
        }

        for (int num : num2) {
            l2Tail.next = new ListNode(num);
            l2Tail = l2Tail.next;
        }

        ListNode answer = addTwoNumbers(l1Head.next, l2Head.next);

        while (answer != null) {
            System.out.println(answer.val);
            answer = answer.next;
        }
    }
}


