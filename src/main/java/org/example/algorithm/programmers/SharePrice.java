package org.example.algorithm.programmers;

/**
 * 프로그래머스 - 주식가격
 * https://programmers.co.kr/learn/courses/30/lessons/42584
 *
 * @author Changhee Choi
 * @since 02/04/2020
 */
public class SharePrice {
    /**
     * @param prices 초 단위로 기록된 주식가격 배열, 1 <= 가격 <= 10000, 2<= 배열길이 <=100000
     * @return 각 시점에서 가격이 떨어지지 않은 기간
     */
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int curPrice = prices[i];
            int count = 0;

            for (int j = i + 1; j < n; j++) {
                count++;
                if (prices[j] < curPrice) break;
            }

            answer[i] = count;
        }

        return answer;
    }
}
