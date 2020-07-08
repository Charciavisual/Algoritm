package org.example.algorithm.acmicpc.dp;

import java.util.Scanner;

/**
 * 동적계획법 - 1로 만들기
 * https://www.acmicpc.net/problem/1463
 *
 * @author Changhee Choi
 * @since 08/07/2020
 */
public class TransToOne {

    private int n;
    private int[] cache;

    public int solution() {
        int answer = 0;
        init();
        answer = countOperation();
        return answer;
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cache = new int[n + 1];
        sc.close();
    }

    private int countOperation() {
        if (n == 1) return 0;

        for (int number = n; number > 1; number--) {
            if (number % 3 == 0) {
                updateCache(number, number / 3);
            }

            if (number % 2 == 0) {
                updateCache(number, number / 2);
            }

            updateCache(number, number - 1);
        }

        return cache[1];
    }

    private void updateCache(int current, int next) {
        if (cache[next] == 0) {
            cache[next] = cache[current] + 1;
        } else {
            cache[next] = Math.min(cache[next], cache[current] + 1);
        }
    }
}
