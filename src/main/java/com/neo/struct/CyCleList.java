package com.neo.struct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Description: 环形链表
 * </p>
 *
 * @author yangwuhai
 * @since 2021-07-04
 */
public class CyCleList {
    static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
//        node5.next = node3;
        System.out.println(hashCycle(node1));
        System.out.println(twoPoint(node1));
    }

    private static boolean hashCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    private static boolean twoPoint(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode quick = head.next;
        while (slow != quick) {
            if (quick == null || quick.next == null) {
                return false;
            }
            slow = slow.next;
            quick = quick.next.next;
        }

        return true;
    }
}
