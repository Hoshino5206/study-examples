package com.hoshino.algorithm.直接插入排序;

import java.util.Arrays;

/**
 * @author Yy_hoshino
 * @date 2021-07-21 0:08
 */
public class InsertSort {
    /**
     * 插入排序
     * @param array
     * @return
     * 最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n²)   平均情况：T(n) = O(n²)
     * 空间复杂的：S(n) = O(1)
     * 稳定性：稳定
     */
    public static int[] insertSort(int[] array) {
        if (array.length > 0) {
            for (int i = 0; i < array.length - 1; i++) {
                int index = i;
                int current = array[i + 1];
                while (index >= 0 && current < array[index]) {
                    array[index + 1] = array[index];
                    index--;
                }
                array[index + 1] = current;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {52, 18, 4, 15, 94, 73, 45, 28, 41, 36};
        System.out.println("排序前：" + Arrays.toString(array));
        int[] insertSort = insertSort(array);
        System.out.println("排序后：" + Arrays.toString(insertSort));
    }
}
