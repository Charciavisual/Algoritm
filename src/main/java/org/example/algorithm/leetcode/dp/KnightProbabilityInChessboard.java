package org.example.algorithm.leetcode.dp;

/**
 * @link https://leetcode.com/problems/knight-probability-in-chessboard/
 * @author Changhee Choi
 * @since 29/12/2020
 */
public class KnightProbabilityInChessboard {
  private final int[][] directions = {
    {-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}, {-1, 2}, {1, 2}
  };
  private double[][][] dp;
  private int N;

  public double knightProbability(int N, int K, int r, int c) {
    this.N = N;
    dp = new double[N][N][K + 1];
    double answer = move(r, c, K);
    return answer;
  }

  private double move(int curR, int curC, int k) {
    if (k == 0) {
      return 1;
    }

    if (dp[curR][curC][k] != 0) {
      return dp[curR][curC][k];
    }

    double ret = 0;

    for (int[] dir : directions) {
      int nextR = curR + dir[0];
      int nextC = curC + dir[1];
      if (isRange(nextR, nextC)) {
        ret += 0.125 * move(nextR, nextC, k - 1);
      }
    }

    dp[curR][curC][k] = ret;
    return ret;
  }

  private boolean isRange(int r, int c) {
    if (r < 0 || c < 0 || r >= N || c >= N) {
      return false;
    }
    return true;
  }
}
