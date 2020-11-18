package org.example.algorithm.leetcode.dp;

/**
 * https://leetcode.com/problems/counting-bits/
 *
 * @author Changhee Choi
 * @since 18/11/2020
 */
public class CountingBits {
    public int[] countBits(int num) {
        int[] answer = new int[num + 1];
        answer[0] = 0;

        for (int n = 1; n <= num; n++) {
            //2의 제곱수 일때
            if (n == (n & -n)) {
                answer[n] = 1;
            } else if (n % 2 == 1) {
                answer[n] = answer[n / 2] + 1;
            } else {
                answer[n] = answer[n / 2];
            }
        }

        return answer;
    }
}
