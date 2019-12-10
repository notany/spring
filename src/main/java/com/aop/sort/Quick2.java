package com.aop.sort;

import java.util.Arrays;

/**
 * Describe
 *
 * @Author RED
 * @Date 2019/8/8 10:20
 */
public class Quick2 {
    private static int count;
    /**
     * 快速排序方法
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int[] QuickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            QuickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            QuickSort(array, smallIndex + 1, end);
        return array;
    }
    /**
     * 快速排序算法——partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        System.out.format("%-2s %2s\n",pivot,array[pivot]);
        int smallIndex = start - 1;
        swap(array, pivot, start);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }

    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        count++;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {8, 9, 1,2, 7,  3, 5, 4, 6, 0};
        long c = System.currentTimeMillis();
        int[] ints = QuickSort(arr, 0, arr.length - 1);
        System.out.println("time="+(System.currentTimeMillis()-c));
        System.out.println(Arrays.toString(ints));
        System.out.println("count="+count);
    }
}
