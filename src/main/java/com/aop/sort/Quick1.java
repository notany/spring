package com.aop.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Describe
 *
 * @Author RED
 * @Date 2019/8/8 10:20
 */
public class Quick1 {

    private static void printArr(int[] arr) {
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int temp = arr[left];
        while (right > left) {
            // 先判断基准数和后面的数依次比较
            while (temp <= arr[right] && left < right) {
                --right;
            }
            // 当基准数大于了 arr[right]，则填坑
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }
            // 现在是 arr[right] 需要填坑了
            while (temp >= arr[left] && left < right) {
                ++left;
            }
            if (left < right) {
                arr[right] = arr[left];
                --right;
            }
        }
        arr[left] = temp;
        return left;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (arr == null || left >= right || arr.length <= 1)
            return;
        int mid = partition(arr, left, right);
        quickSort(arr, left, mid);
        quickSort(arr, mid + 1, right);
    }


    public static void main(String[] args) {
        int[] arr = {6, 4, 3, 2, 7, 9, 1, 8, 5};
//        quickSort(arr, 0, arr.length - 1);
//        printArr(arr);
//        List list = new ArrayList<>();
        String[] s = {"a","b","c"};
        List sa =Arrays.asList(s);
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        Arrays.asList(arr);
        System.out.println(Arrays.asList(1,2));
        if(Arrays.asList(1,2).contains(1)){
            System.out.println(1);
        }

    }
}
