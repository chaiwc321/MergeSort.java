package com.sort.tree;

import java.util.Stack;

public class EcTree {

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

//        System.out.print("先序遍历:   ");
//        preOrderRecur(head);
//        System.out.println(" ");
//        preOrderUnRecur(head);
//        System.out.println("==========================");
        System.out.print("中序遍历:   ");
        inOrderRecur(head);
        System.out.println("=");
        inOrderUnRecur(head);
//        System.out.print("后序遍历:   ");
//        posOrderRecur(head);
//        System.out.println(" ");
//        posOrderUnRecur(head);

    }

    // 先序遍历
    public static void preOrderRecur(Node head){
        if (head == null){
            return;
        }
        System.out.print(head.value + "" + "\t");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }
    // 先序遍历 非递归实现
    public static void preOrderUnRecur(Node head){
        if (head == null){
            return;
        }
        Stack<Node> nodes = new Stack<>();
        nodes.push(head);
        while(nodes.iterator().hasNext()){
            Node current = nodes.pop();
            System.out.print(current.value + " \t");
            if(current.right != null){
                nodes.push(current.right);
            }
            if(current.left != null){
                nodes.push(current.left);
            }
        }

    }

    // 中序遍历
    public static void inOrderRecur(Node head){
        if (head == null){
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + "" + "\t");
        inOrderRecur(head.right);
    }
    // 中序遍历 非递归实现
    public static void inOrderUnRecur(Node head){
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || head != null){
            if(head != null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                System.out.print(head.value + " \t");
                head = head.right;
            }
        }
    }

    // 后序遍历
    public static void posOrderRecur(Node head){
        if (head == null){
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + "" + "\t");
    }
    // 后序遍历 非递归实现
    public static void posOrderUnRecur(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> coStack = new Stack<>();
        stack.push(head);
        while(stack.iterator().hasNext()){
            Node current = stack.pop();
//            System.out.print(current + " \t");
            coStack.push(current);
            if(current.left != null){
                stack.push(current.left);
            }
            if(current.right != null){
                stack.push(current.right);
            }
        }
        while(coStack.iterator().hasNext()){
            System.out.print(coStack.pop().value + "\t");
        }

    }

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }


}
