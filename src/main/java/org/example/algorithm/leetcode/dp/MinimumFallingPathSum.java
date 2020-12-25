package org.example.algorithm.leetcode.dp;

import java.util.Arrays;

/**
 * @link <a>https://leetcode.com/problems/minimum-falling-path-sum/</a>
 * @author Changhee Choi
 * @since 25/12/2020
 */
public class MinimumFallingPathSum {
  public int minFallingPathSum(int[][] A) {
    final int INF = 10001;
    final int rows = A.length;
    final int cols = A.length;
    final int[][] dp = new int[rows][cols];

    int answer = INF;

    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], INF);
    }

    for (int col = 0; col < cols; col++) {
      dp[0][col] = A[0][col];
    }

    for (int row = 1; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        for (int k = -1; k <= 1; k++) {
          if (col + k >= 0 && col + k < cols) {
            dp[row][col] = Math.min(dp[row][col], A[row][col] + dp[row - 1][col + k]);
          }
        }
      }
    }

    for (int col = 0; col < cols; col++) {
      answer = Math.min(answer, dp[rows - 1][col]);
    }

    return answer;
  }
}
