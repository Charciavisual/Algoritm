package org.example.algorithm.leetcode.dp;

/**
 * @link https://leetcode.com/problems/minimum-cost-for-tickets/
 * @author Changhee Choi
 * @since 27/12/2020
 */
public class MinimumCostForTickets {
  private int[] memo;
  private int[] costs;
  private int[] days;
  private int[] diff = {1, 7, 30};

  public int mincostTickets(int[] days, int[] costs) {
    this.days = days;
    this.memo = new int[days.length];
    this.costs = costs;

    return dp(0);
  }

  private int dp(int curIdx) {
    if (curIdx >= days.length) {
      return 0;
    }
    if (memo[curIdx] != 0) {
      return memo[curIdx];
    }

    int answer = Integer.MAX_VALUE;
    int afterIdx = curIdx;

    for (int i = 0; i < 3; i++) {
      // 1-day-pass, 7-day-pass, 30-day-pass 를 적용했을때의 cost를 계산하여 min 값을 구한다.
      // 현재 날짜 ~ pass가 끝나는 날에 속하는 날짜만큼 afterIdx를 증가시킨다.
      while (afterIdx < days.length && days[afterIdx] < days[curIdx] + diff[i]) {
        afterIdx++;
      }
      answer = Math.min(answer, dp(afterIdx) + costs[i]);
    }

    memo[curIdx] = answer;
    return answer;
  }
}
