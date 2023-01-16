package com.xiaozhu.data.algo;

import java.util.Arrays;

public class Sort {
    public static void quick_sort(int[] elements, int l, int r) {
        if(l > r) return;
        //
        int low = l;
        int high = r;
        int key = elements[low];
        while (low < high) {
            // 从高位开始直到遇到比较key小才停下来并
            while (low < high && elements[high] >= key) {
                high--;
            }
            elements[low] = elements[high];

            while (low < high && elements[low] <= key) {
                low ++;
            }
            // 交换
            elements[high] = elements[low];
        }
        // 把参考元素放到
        elements[low] = key;

        quick_sort(elements, l, low - 1);
        quick_sort(elements, low + 1, r);
    }

    // 核心思想是二分法
    // 递归直到产生无法再分为止
    public static void merge_sort(int[] elements, int low, int high){
        if(high == low) return;

        int mid = low + (high - low) / 2;

        merge_sort(elements, low, mid);
        merge_sort(elements, mid + 1, high);

        // 合并，
        int i = 0;
        int j = 0;
        int m = 0;
        int[] newArr = new int[high - low];
        while (i <= mid && j <= high) {
            newArr[m++] = elements[i] >= elements[j] ? elements[j++] : elements[i++];
        }

        // 把剩余的元素拷贝到
        while (i <= mid) newArr[m++] = elements[i++];
        while (j <= high) newArr[m++] = elements[j++];
    }

    public static void main(String[] args) {
        int[] el = {2,4,67,8,9,5};
        Arrays.stream(el).forEach(x -> System.out.print(x + " "));
        merge_sort(el, 0, el.length-1);
        System.out.println("\n---------------------------------------------------");
        Arrays.stream(el).forEach(x -> System.out.print(x + " "));
    }
}
