package com.sort.bucket;

public class BucketSort {

    public static void main(String[] args) {
        int[] arr = {287,325,564,301,282,791,134,870};
        int digit = getMaxBits(arr);
        bucketsort(arr,0,arr.length-1,digit);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /*
    已知范围0 ~ 200的数在数组中，对其进行排序
    1.建立统计数组，size是数组中最大的数+1
    2.相同的数在统计数组对应的位置上+1
    3.再“倒”回原数组
     */
    public static void countSort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < bucket.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            while(bucket[j] > 0){
                arr[i] = j;
                bucket[j]--;
                i++;
            }
        }
    }

    /*
    桶排序：
    1.建立统计数组，size = 10， 0~9
    2.从个位数开始，同一位数上相同的数在统计数组上+1，统计数组改进为前缀和，原数组从右往左遍历，按统计数组倒进桶里，桶的大小是数组中指定范围+1
    3.重复2至整个数组上最大的位数
    4.排序完成
     */
    // 函数：桶排序
    public static void bucketsort(int[] arr, int begin, int end, int digit){
        final int size = 10;
        int i = 0; // 原数组下标
        int j = 0; // 统计数组下标
        int[] bucket = new int[end - begin + 1];
        // 每个位数一次循环，次数是最大的位数digit
        for (int d = 1; d <= digit;d++){
            // 循环：相同位置上的数统计进统计数组
            int[] count = new int[size];
            for (i = begin; i < end+1; i++) {
                j = getDigit(arr[i],d);
                count[j]++;
            }
            // 循环：将统计数组改进
            for (int k = 1; k < size; k++) {
                count[k] = count[k] + count[k-1];
            }
            // 循环：放进桶里,从右向左遍历
            for(i = end;i>=begin;i--){
                j = getDigit(arr[i],d);
                bucket[count[j]-1] = arr[i];
                count[j]--;
            }
            // 循环：将桶里的放进数组中
            for(int k = 0; k < bucket.length;k++){
                i = begin + k;
                arr[i] = bucket[k];
            }
        }
    }
    // 函数：从数（x）得到指定位数（d）上的值,个位数的d=1，以此类推
    public static int getDigit(int x, int d){
        return ((x/(int)(Math.pow(10, d-1)))%10);
    }
    // 函数：取得数组中最大位数
    public static int getMaxBits(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int d = 0;
        while (max != 0){
            d++;
            max = max/10;
        }
        return d;
    }

}
