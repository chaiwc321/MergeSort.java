package com.sort.list;

import java.util.ArrayList;
import java.util.Stack;

public class PrintShare {

    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(2);
        head.next.next = new Node(5);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(6);
//        head.next.next.next.next.next = new Node(1);
        printList(head);
        head = listPartition1(head, 4);
        printList(head);
    }
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    // 复制含有随机指针节点的链表
    public static Node randNodeCopy(Node head){
        if(head == null){
            return null;
        }
        Node start = head;
        Node tmp;
        while(head != null){
            tmp = head.next;
            head.next = new Node(head.value);
            head.next.next = tmp;
            head = tmp;
        }
        head = start;
        while (head != null){
            tmp = head;
            head.next.rand = tmp.rand == null ? null : tmp.rand;
            head = head.next.next;
        }
        head = start;
        Node res = head.next;
        while(head != null){
            tmp = head.next;
            start = head.next.next;
            head.next = head.next.next;
            tmp.next = tmp.next.next;
            head = start;
        }
        return res;
    }

    // 面试 partition
    public static  Node listPartition1(Node head, int pivot){
        if (head == null || head.next == null){
            return head;
        }
        Node lhead = null;
        Node ltail = null;
        Node rhead = null;
        Node rtail = null;
        Node mhead = null;
        Node mtail = null;
        Node next = null;
        while(head != null){
//            next = head.next;
//            head.next = null;
            // 这里将每个节点的next置空是为了后面好判断范围
            if(head.value < pivot) {
                if (lhead == null) {
                    lhead = head;
                    ltail = head;
                }else{
                    ltail.next = head;
                    ltail = head;
                }
            }else if (head.value == pivot){
                if (mhead == null) {
                    mhead = head;
                    mtail = head;
                }else{
                    mtail.next = head;
                    mtail = head;
                }
            }else{
                if (rhead == null) {
                    rhead = head;
                    rtail = head;
                }else{
                    rtail.next = head;
                    rtail = head;
                }
            }
            head = head.next;
        }
        ltail.next = mhead;
        mtail.next = rhead;
        return lhead;
    }

    // 单链表partition 笔试
    public static Node listPartition(Node head, int pivot){
        if (head == null || head.next == null){
            return head;
        }
        Node tmp = head;
        int i = 0;
        while(tmp != null){
            i++;
            tmp = tmp.next;
        }
        tmp = head;
        Node[] arr = new Node[i];
        for (int j = 0; j < i; j++) {
            arr[j] = tmp;
            tmp = tmp.next;
        }
        int a=0, b=a-1, c=i-1;
        while(a<=c){
            if (arr[a].value < pivot){
                swap(arr,++b,a++);
            }else if (arr[a].value == pivot){
                a++;
            }else{
                swap(arr, a,c--);
            }
        }
        arr[i-1].next = null;
        for (int j = 0; j < i-1; j++) {
            arr[j].next = arr[j+1];
        }
        return arr[0];
    }

    // 交换函数
    public static void swap(Node[] arr, int l, int r){
        Node tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

    // 打印公共部分，前提是有序
    public static void printCommonPart(Node head1, Node head2){
        while(head1 != null && head2 != null){
            if(head1.value == head2.value){
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
                System.out.println(head1.value);
            }else if(head1.value < head2.value){
                head1 = head1.next;
            }else {
                head2 = head2.next;
            }
        }
    }

    // 判断一个单链表是否为回文结构
    public static boolean judgeHuiwen(Node head){
        if(head == null || head.next == null){ // 去空
            return true;
        }
        Node fast = head; // 快指针
        Node slow = head; // 慢指针
        // 找出中间指针
        try{
            while(slow.next != null && fast.next.next != null){
                fast = fast.next.next;
                slow = slow.next;
            }
        }catch (NullPointerException e){} // 当fast.next为空指针时，fast.next.next不存在，回报错

        Stack<Node> nodeStack = new Stack<Node>(); // 为后半段建栈
        while(slow.next != null){
            slow = slow.next;
            nodeStack.push(slow);
        }
        while(nodeStack.iterator().hasNext()){
            if(head.value != nodeStack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean judgeHuiwen1(Node head){
        // 去空
        if(head == null || head.next == null){
            return true;
        }
        // 定义两个指针，一开始的功能是找中间指针，后面当作链表反转的指针变量
        Node fast = head;
        Node slow = head;
        try{
            while(slow.next != null && fast.next.next != null){
                fast = fast.next.next;
                slow = slow.next;
            }
        }catch (NullPointerException e){}
        // 定义mid为中间位置指针
        Node mid = slow;
        slow = slow.next; // slow为右边第一个位置的指针
        mid.next = null;  // 中间位置的指针指向空，断开指向右边第一个节点的连接
        while(slow != null){ // slow为当前指针，在每次循环后，slow左边的指针都是与slow断开连接的状态，都是指向左边或者一开始的空
            fast = slow.next; // fast为slow后一个指针
            slow.next = mid; // 将当前指针的指向指向左边的指针，断开向右边的指针
            mid = slow;  // 右边的指针移向当前指针位置位置
            slow = fast;  // 当前指针移向右边一个指针，因为已经断开连接，但是前面用fast保存了右边指针位置
        }
        slow = mid;  // 上一个反转结束后，mid是最右边的指针，fast和slow都是mid指向的空指针，这里要保存最右边的指针用于后续的链表复原
        while (head != null && mid != null){  // 一个从左边向中间遍历，一个从右边向中间遍历，直至有个指针走到了之前中间位置指针指向的空指针
            if(head.value != mid.value){
                return false;
            }
            head = head.next;
            mid = mid.next;
        }
        mid = slow.next;  // 从右边开始反转，进行链表的复原，mid是倒数第二个右边的指针
        slow.next = null; // 最右边的指针指向空
        while(mid != null){ // 当mid走到中间位置指向的空指针时，与前面的指针是没有连接的，前面的链表已经反转完毕
            fast = mid.next; // 保留即将与其断开连接的下下个指针
            mid.next = slow; // 下一个指针指向当前，断开连接与下下指针的连接
            slow = mid;  // 当前指针变成写下一个指针
            mid = fast;  // 下一个指针变成下下个指针
        }
        return true;

    }

    public static void printList(Node head){
        if (head == null){
            System.out.println("指针为空");
        }
        while(head != null){
            System.out.println(head.value);
            head = head.next;
        }
    }
}
