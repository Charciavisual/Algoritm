package org.example.algorithm.leetcode.dp;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author Changhee Choi
 * @since 21/10/2020
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int answer = 0;
        int purchasePrice = Integer.MAX_VALUE;

        for (int day = 0; day < prices.length; day++) {
            if (prices[day] < purchasePrice) {
                purchasePrice = prices[day];
                continue;
            }
            answer = Math.max(answer, prices[day] - purchasePrice);
        }

        return answer;
    }
}
