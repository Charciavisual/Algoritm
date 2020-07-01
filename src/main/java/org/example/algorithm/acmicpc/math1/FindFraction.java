package org.example.algorithm.acmicpc.math1;

/**
 * 수학1 - 분수찾기
 * https://www.acmicpc.net/problem/1193
 *
 * @author Changhee Choi
 * @since 30/06/2020
 */
public class FindFraction {
    public String solution(int k) {
        if (k == 1) return "1/1";

        int sum = 0, num = 0;

        while (sum < k) {
            sum += ++num;
        }

        int move = k - (sum - num);
        int numerator = 0, denominator = 0;

        if (num % 2 == 1) {
            numerator = num - (move - 1);
            denominator = 1 + (move - 1);
        } else {
            numerator = 1 + (move - 1);
            denominator = num - (move - 1);
        }

        return numerator + "/" + denominator;
    }
}
