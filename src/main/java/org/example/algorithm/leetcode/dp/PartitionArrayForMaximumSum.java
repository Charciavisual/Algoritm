package org.example.algorithm.leetcode.dp;

/**
 * @see <a>https://leetcode.com/problems/partition-array-for-maximum-sum/</a>
 * @author Changhee Choi
 * @since 23/12/2020
 */
public class PartitionArrayForMaximumSum {
  public int maxSumAfterPartitioning(int[] arr, int k) {
    final int n = arr.length;
    final int[] dp = new int[n + 1];

    for (int i = 0; i < n; i++) {
      int maxValue = 0;
      for (int j = 0; i - j >= 0 && j < k; j++) {
        maxValue = Math.max(maxValue, arr[i - j]);
        int partitionSum = maxValue * (j + 1);
        dp[i + 1] = Math.max(dp[i + 1], partitionSum + dp[i - j]);
      }
    }

    return dp[n];
  }
}
