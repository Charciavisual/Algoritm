package org.example.algorithm.leetcode.dp;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 *
 * @author Changhee Choi
 * @since 21/10/2020
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int answer = 0;
        int n = cost.length;
        for (int i = 2; i < n; i++) {
            cost[i] += Math.min(cost[i - 2], cost[i - 1]);
        }
        answer = Math.min(cost[n - 1], cost[n - 2]);
        return answer;
    }
}

