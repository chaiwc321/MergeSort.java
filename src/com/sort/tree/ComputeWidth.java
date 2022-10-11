package com.sort.tree;

import java.util.HashMap;
import java.util.LinkedList;

public class ComputeWidth {
    public static void main(String[] args) {
        EcTree.Node head = new EcTree.Node(5);
        head.left = new EcTree.Node(3);
        head.right = new EcTree.Node(8);
        head.left.left = new EcTree.Node(2);
        head.left.right = new EcTree.Node(4);
        head.left.left.left = new EcTree.Node(1);
        head.right.left = new EcTree.Node(7);
        head.right.left.left = new EcTree.Node(6);
        head.right.right = new EcTree.Node(10);
        head.right.right.left = new EcTree.Node(9);
        head.right.right.right = new EcTree.Node(11);

        System.out.println("二叉树的宽度为 " + treeWidth(head));
    }

    // 用hashmap求宽度
    public static int treeWidth(EcTree.Node head){
        int max = Integer.MIN_VALUE;
        int level = 0;
        int count = 0;
        HashMap<EcTree.Node, Integer> levelMap = new HashMap<>(); // 记录节点在哪一层
        LinkedList<EcTree.Node> queue = new LinkedList<>();  // 按宽度顺序遍历
        levelMap.put(head, 1);
        queue.add(head);
        EcTree.Node node = null;
        EcTree.Node left = null;
        EcTree.Node right = null;
        while (!queue.isEmpty()){
            node = queue.poll();
            left = node.left;
            right = node.right;
            if(left != null){
                levelMap.put(left, levelMap.get(node) + 1);
                queue.add(left);
            }
            if(right != null){
                levelMap.put(right, levelMap.get(node) + 1);
                queue.add(right);
            }
            if(level != levelMap.get(node)){
                level++;
                count = 1;
            }else{
                count++;
            }
            max = Math.max(count,max);
        }

        return max;
    }



}
