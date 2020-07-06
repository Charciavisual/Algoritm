package org.example.algorithm.acmicpc.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 동적계획법 - 피보나치(0,1 개수 세기)
 * https://www.acmicpc.net/problem/1003
 *
 * @author Changhee Choi
 * @since 06/07/2020
 */
public class Fibo_Count0And1 {

    private int t;
    private int[] numbers;
    private long[][] count;

    public String solution() {
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException("초기화 과정에서 입력 오류가 발생했습니다.");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            sb.append(count[numbers[i]][0]).append(" ").append(count[numbers[i]][1]).append("\n");
        }
        return sb.toString();
    }

    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        count = new long[41][2];
        prevCalcFibo();

        numbers = new int[t];

        for (int i = 0; i < t; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }

    //N의 최대값인 40까지의 0과 1개수를 구한다.
    private void prevCalcFibo() {
        //0
        count[0][0] = 1;
        count[0][1] = 0;

        //1
        count[1][0] = 0;
        count[1][1] = 1;

        for (int i = 2; i < 41; i++) {
            count[i][0] = count[i - 1][0] + count[i - 2][0];
            count[i][1] = count[i - 1][1] + count[i - 2][1];
        }
    }
}
