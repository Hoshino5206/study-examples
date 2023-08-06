package com.hoshino.algorithm.二分查找;

/**
 * @author Yy_hoshino
 * @date 2021-07-21 0:14
 */

/**
 * 二分查找的算法实现
 * 如果元素存在返回索引值
 * 如果元素不存在返回-1
 */
public class BinarySearch {
    public static int binaryserach(int[] arr,int number){
        int start = 0;
        int end = arr.length - 1;
        // 定义一个循环不断的折中查找
        while(start <= end){
            // 定位出中间元素的索引
            int middleIndex = (start + end) / 2;
            // 拿当前元素与中间元素的值进行比较
            if(number < arr[middleIndex]) {
                // 当前元素小于中间元素，向左查找，莫未位置要变化
                end = middleIndex - 1;
            }else if(number > arr[middleIndex]){
                // 当前元素大于中间元素，向右查找，起始位置要变化
                start = middleIndex + 1;
            }else if(number == arr[middleIndex]){
                return middleIndex;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {5 , 12, 31, 45, 50, 69, 77, 81, 92};
        int index = binaryserach(arr, 70);
        System.out.println(index);
    }
}
