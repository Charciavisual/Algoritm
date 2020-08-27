package org.example.algorithm.leetcode;

import java.util.HashMap;

/**
 * Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * @author Changhee Choi
 * @since 27/08/2020
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        HashMap<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            numMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int other = target - nums[i];
            if (numMap.get(other) != null && numMap.get(other) != i) {
                answer[0] = i;
                answer[1] = numMap.get(other);
                break;
            }
        }
        return answer;
    }
}
