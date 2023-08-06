package com.hoshino.algorithm.冒泡排序;

import java.util.Arrays;

/**
 * @author Yy_hoshino
 * @date 2021-07-20 23:47
 */
public class BubbleSort {
    /**
     * 冒泡排序
     * @param array
     * @return array
     * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n²)   平均情况：T(n) = O(n²)
     * 空间复杂的：S(n) = O(1)
     * 稳定性：稳定
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length - 1 - i; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {52, 18, 4, 15, 94, 73, 45, 28, 41, 36};
        System.out.println("排序前：" + Arrays.toString(array));
        int[] bubbleSort = bubbleSort(array);
        System.out.println("排序后：" + Arrays.toString(bubbleSort));
    }
}
