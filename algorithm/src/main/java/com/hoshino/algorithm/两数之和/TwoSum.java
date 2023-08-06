package com.hoshino.algorithm.两数之和;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yy_hoshino
 * @date 2021-07-21 0:26
 */

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    /*
    暴力解法
     */
    public static int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /*
    哈希表法
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hashmap.containsKey(target - nums[i])) {
                return new int[]{hashmap.get(target - nums[i]), i};
            }
            hashmap.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7};
        int[] sum = twoSum1(nums, 12);
        System.out.println(Arrays.toString(sum));
    }
}
