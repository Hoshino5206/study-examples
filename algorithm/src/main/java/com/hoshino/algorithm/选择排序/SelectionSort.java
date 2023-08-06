package com.hoshino.algorithm.选择排序;

import java.util.Arrays;

/**
 * @author Yy_hoshino
 * @date 2021-07-20 23:58
 */
public class SelectionSort {
    /**
     * 选择排序
     * @param array
     * @return array
     * 最佳情况：T(n) = O(n²)  最差情况：T(n) = O(n²)  平均情况：T(n) = O(n²)
     * 空间复杂的：(n) = O(1)
     * 稳定性：不稳定
     */
    public static int[] selectionSort(int[] array) {
        if (array.length > 0) {
            for (int i = 0; i < array.length - 1; i++) {
                int minIndex = i;
                for (int j = i; j < array.length; j++) {//遍历未剩余未排序元素中继续寻找最小元素
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    int temp = array[minIndex];
                    array[minIndex] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {52, 18, 4, 15, 94, 73, 45, 28, 41, 36};
        System.out.println("排序前：" + Arrays.toString(array));
        int[] bubbleSort = selectionSort(array);
        System.out.println("排序后：" + Arrays.toString(bubbleSort));
    }
}
