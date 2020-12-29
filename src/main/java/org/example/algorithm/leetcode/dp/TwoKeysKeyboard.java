package org.example.algorithm.leetcode.dp;

/**
 * @link https://leetcode.com/problems/2-keys-keyboard/
 * @author Changhee Choi
 * @since 29/12/2020
 */
public class TwoKeysKeyboard {
  private int n;
  private int[][] memo;
  private int INF = 1001;

  public int minSteps(int n) {
    if (n == 1) {
      return 0;
    }
    this.n = n;
    this.memo = new int[n + 1][n + 1];
    // copy를 한번 하고 시작
    return 2 + operate(2, 1);
  }

  private int operate(int len, int copyLen) {
    if (len > n) {
      return INF;
    }

    if (len == n) {
      return 0;
    }

    if (memo[len][copyLen] != 0) {
      return memo[len][copyLen];
    }

    // copy만 반복되는 경우를 예외처리하기 위해 copy를 했을때는 paste도 하도록 처리
    int copy = 2 + operate(len + len, len);
    int paste = 1 + operate(len + copyLen, copyLen);
    int ret = Math.min(copy, paste);
    memo[len][copyLen] = ret;
    return ret;
  }
}
