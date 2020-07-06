package org.example.algorithm.acmicpc.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 동적계획법 - 계단오르기
 * https://www.acmicpc.net/problem/2579
 *
 * @author Changhee Choi
 * @since 06/07/2020
 */
public class StairClimb {
    private int n;
    private int[] stairs;
    private int[] score;

    public int solution() {
        int answer = 0;

        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException("초기화 과정에서 입력오류가 발생했습니다.");
        }

        answer = calcMaxScores();
        return answer;
    }

    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        stairs = new int[n + 1];
        score = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }

    private int calcMaxScores() {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return stairs[1];
        }

        score[1] = stairs[1];
        score[2] = Math.max(stairs[1] + stairs[2], stairs[2]);

        for (int i = 3; i <= n; i++) {
            score[i] = Math.max(score[i - 2] + stairs[i], score[i - 3] + stairs[i - 1] + stairs[i]);
        }

        return score[n];
    }
}
