package com.sort.merge;

public class XiaoHe {

    static public void process(int[] arr, int l, int r){
        if (r == l){
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        smallSum(arr, l, mid, r);
    }

    static public void smallSum(int[] arr, int l, int m, int r){
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int sum = 0;
        while(p1 <= m && p2 <= r){
            sum += arr[p1]<arr[p2] ? arr[p1]*(r - p2 + 1):0;
            help[i++] = arr[p1]<arr[p2] ? arr[p1++]:arr[p2++];
        }
        while(p2<=r){
            help[i++] = arr[p2++];
        }
        while (p1<=m){
            help[i++] = arr[p1++];
        }
        i = 0;
        for (int j = l; j < r+1; j++) {
            arr[j] = help[i++];
        }
    }
}
