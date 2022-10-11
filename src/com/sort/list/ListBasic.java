package com.sort.list;

public class ListBasic {

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

     // 双链表
    public class Node {
        public int val;
        public Node prev;
        public Node next;

         public Node(int val, Node prev, Node next) {
             this.val = val;
             this.prev = prev;
             this.next = next;
         }
     }

    // 反转
    public Node inverseList(Node head){
        Node node = null;
        while(head!=null){
            Node next = head.next;
            head.next = head.prev;
            head.prev = next;
            node = head;
            head = next;
        }
        return node;
    }


}
