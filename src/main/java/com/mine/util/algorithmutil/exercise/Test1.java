package com.mine.util.algorithmutil.exercise;

import java.util.LinkedList;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/3/9 16:28
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class Test1 {
    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param args
     */
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        ListNode node1 = new ListNode(9, new ListNode(9, new ListNode(9, null)));
        ListNode node2 = new ListNode(9, new ListNode(9, new ListNode(9, null)));
        LinkedList<Integer> obj = test(node1, node2);
        ListNode listNode1 = testListNode(node1, node2);
        long time = System.currentTimeMillis() - now;
        System.out.println(time + ":" + obj.toString());
    }


    //输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    //输出：7 -> 0 -> 8
    public static LinkedList<Integer> test(ListNode a, ListNode b) {
        LinkedList<Integer> list = new LinkedList<>();
        int param = 0;
        while (a != null || b != null) {
            int count = a.val + b.val;
            int init = count % 10;
            int p = param + init;
            param = (count - init) / 10;
            list.addFirst(p);
            a = a.next;
            b = b.next;
        }
        if (param != 0) {
            list.addFirst(param);
        }
        return list;
    }

    public static ListNode testListNode(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            //和
            int sum = carry + x + y;
            //进位数
            carry = sum / 10;
            //当前位数字
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int x, ListNode next) {
        this.val = x;
        this.next = next;
    }

    public ListNode(int x) {
        this.val = x;
    }
}
