package org.example.algorithm.leetcode.dp;

/**
 * @author Changhee Choi
 * @since 23/12/2020
 */
public class PartitionArrayForMaximumSum {

    private int[][] dp;

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int answer = 0;
        int n = arr.length;
        dp = new int[n][n];

        answer = Math.max(answer, getLargestSum(arr, 0, n - 1, k));

        return answer;
    }

    private int getLargestSum(int[] arr, int start, int end, int k) {
        if (start > end) {
            return 0;
        }
        if (dp[start][end] > 0) {
            return dp[start][end];
        }

        int ret = 0;
        int maxValue = 0;
        for (int i = 0; i < k; i++) {
            if (start + i >= arr.length) {
                break;
            }
            maxValue = Math.max(maxValue, arr[start + i]);
            int partitionSum = maxValue * (i + 1);
            dp[start][start + i] = partitionSum;
            ret = Math.max(ret, partitionSum + getLargestSum(arr, start + i + 1, end, k));
        }
        dp[start][end] = Math.max(dp[start][end], ret);
        return ret;
    }
}
