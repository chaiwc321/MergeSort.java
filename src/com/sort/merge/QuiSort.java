package com.sort.merge;

import java.util.Scanner;

public class QuiSort {

    public static void main(String[] args) {
        int[] ints = {2,5,8,3,6,6,1,3,8};
//        Scanner scanner = new Scanner(System.in);
//        for (int i = 0; i < 9; i++) {
//            ints[i] = scanner.nextInt();
//        }
        process(ints, 0, 8);
        for (int i = 0; i < 9; i++) {
            System.out.println(ints[i]);
        }
    }

    public static void process(int[] arr, int l, int r) {

        if (l < r) {
            int num = arr[l + (int) (Math.random() * (r - l + 1))];
            int[] p = partition(arr, num, l, r);
            process(arr, l, p[0]);
            process(arr, p[1]+1, r);
        }
    }

    public static int[] partition(int[] arr, int n, int l, int r){
        int p1 = l - 1;
        while(l  <= r){
            if(arr[l] < n){
                swap(arr, ++p1, l++);
            }else if(arr[l] > n){
                swap(arr, l, r--);
            }else{
                l++;
            }
        }
        int[] res = {p1, r};
        return res;

    }

    public static void swap(int[] arr, int a, int b){
        int c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }
}
