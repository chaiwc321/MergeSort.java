package com.sort.merge;

import java.util.Scanner;

public class MergeSort {

    public static void main(String[] args) {
        int[] ints = new int[6];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            ints[i] = scanner.nextInt();
        }
        mergeSort(ints, 0, 5);
        for(int j=0; j<6;j++) {
            System.out.println( ints[j]);
        }
    }
    public static void mergeSort(int[] arr,int l,int r){
        if(arr[l] == arr[r]){
            return;
        }
        int mid = l +((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid+1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r){
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while(p1<=mid && p2 <= r){
            help[i++] = arr[p1]<arr[p2] ? arr[p1++]:arr[p2++];
        }
        while(p2<=r){
            help[i++] = arr[p2++];
        }
        while (p1<=mid){
            help[i++] = arr[p1++];
        }
        i = 0;
        for (int j = l; j < r+1; j++) {
            arr[j] = help[i++];
        }
    }
}
