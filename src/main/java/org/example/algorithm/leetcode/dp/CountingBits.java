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

        for (int i = 1; i <= num; i++) {
            answer[i] = answer[i >> 1] + (i & 1);
        }

        return answer;
    }
}
