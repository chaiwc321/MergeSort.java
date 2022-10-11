package com.sort.heap;

import java.util.PriorityQueue;

public class HeapLearning {

    public static void main(String[] args) {
        int[] arr = {2,3,5,3,2,7,1,8};
//        heapInsert(arr, 6);
//        heapify(arr, 6);
        heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    // 堆排序,先堆化，将堆化后的第一个值放到数组最后一位，heapsize--
    public static void heapSort(int[] arr){
//        int heapsize = arr.length;
        for(int heapsize = arr.length;heapsize > 0;heapsize--){
            heapify(arr, heapsize);
            swap(arr, 0, heapsize-1);
        }
    }

    // 堆排序扩展，数组中几乎有序，即排序后每个 数 的位置 移动距离不大于k
    public static void almostSort(int[] arr, int k){
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        // 先将数组中的值加到堆中，堆的最大容量为k
        int index = 0;
        for (; index < Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (;index  < arr.length; i++, index++) {
            arr[i] = heap.poll();
            heap.add(arr[index]);
        }
        while(heap.iterator().hasNext()){
            arr[i] = heap.poll();
            i++;
        }
    }

    //heapinsert操作
    public static void heapInsert(int[] arr, int index){
        // index参数不是从数组的第几个开始堆化，而是测定该位置的数，使其在其分支上满足大根堆要求
        // index 小于 0 时，数组默认没有数，跳出while循环
        while(arr[index] > arr[(index - 1)/2]){
            swap(arr, index, (index - 1)/2);
            index = (index - 1)/2;
        }
    }

    // heapify操作
    public static void heapify(int[] arr, int size){
        /*
        1. 将数组抽象成完全二叉树
        2.判断每个根节点是否是该分堆的最大值，从最后一个根节点开始向上遍历，但是是向下比较并交换
        3.用该元素左右节点最大值与其比较（如何判断单子节点的情况：在迭代函数分情况判断），若大于则交换(如何确定较大值是左边还是右边：添加判断语句，或者三者同时比较)
        4.向上遍历
        */
        int fapoint = (size - 2)/2;  //最后一个父节点
        while(fapoint >= 0){  //第一个父节点
            heap(arr, fapoint, size);  //父节点在该分堆 进行大根堆化
            fapoint--;  // 上一个父节点
        }
    }

    // 递归函数 父节点向下比较
    public static void heap(int[] arr, int fapoint, int size){
        if(2*fapoint+1> size-1){  // 判断最后一个左孩子是否超过范围
            return;
        }else if(2*fapoint+2>size-1){  // 一个子节点
            if(arr[fapoint] < arr[2 * fapoint + 1]){  // 父节点的值与左子节点比较
                swap(arr, fapoint, 2 * fapoint + 1);  // 交换最大值与父节点
            }
        }else{
            if(arr[fapoint] < Math.max(arr[2 * fapoint + 1], arr[2 * fapoint + 2])){  // 父节点的值与两个子节点最大值比较
                int max = arr[2 * fapoint + 1] < arr[2 * fapoint + 2] ? 2 * fapoint + 2 : 2 * fapoint + 1;  // 最大值左表
                swap(arr, fapoint, max);  // 交换最大值与父节点
                fapoint = max;  // 交换后的子节点当作父节点往下迭代，判断该分堆是否满足大根堆
                heap(arr, fapoint, size);
            }
        }
    }

    // 交换函数
    public static void swap(int[] arr, int a, int b){
        int c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }


//    // 没有向下考虑
//    public static void heapify(int[] arr, int index, int size){
//        int left = index * 2 + 1;
//        while(left < size){
//            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
//            largest = arr[largest] > arr[index] ? largest : index;
//            if(largest == index){
//                break;
//            }
//            swap(arr, largest, index);
//            index = largest;
//            left = index * 2 + 1;
//        }
//    }


}
