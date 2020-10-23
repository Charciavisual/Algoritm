package org.example.algorithm.leetcode.dp;

/**
 * https://leetcode.com/problems/maximum-subarray/
 *
 * @author Changhee Choi
 * @since 23/10/2020
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int answer = 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        answer = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int curNum = nums[i];
            dp[i] = Math.max(curNum + dp[i - 1], curNum);
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}
