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
        return calculation(n);
    }

    private int calculation(int n) {
        if (n == 1) return 0;

        if (cache[n] != 0) {
            return cache[n];
        }

        int ret = 987654321;

        if (n % 3 == 0) {
            ret = Math.min(ret, calculation(n / 3) + 1);
        }
        if (n % 2 == 0) {
            ret = Math.min(ret, calculation(n / 2) + 1);
        }
        ret = Math.min(ret, calculation(n - 1) + 1);

        cache[n] = ret;

        return ret;
    }
}
