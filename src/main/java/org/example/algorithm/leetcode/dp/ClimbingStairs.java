package org.example.algorithm.leetcode.dp;

/**
 * https://leetcode.com/problems/climbing-stairs/
 *
 * @author Changhee Choi
 * @since 23/10/2020
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n - 1];
    }
}
