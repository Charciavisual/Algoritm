package org.example.algorithm.acmicpc.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 동적계획법 - 정수 삼각형
 * https://www.acmicpc.net/problem/1932
 *
 * @author Changhee Choi
 * @since 07/07/2020
 */
public class Triangle {

    private int n;
    private int[][] triangle;
    private int[][] cache;

    public int solution() {
        int answer = 0;

        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException("초기화 과정에서 입력오류가 발생했습니다.");
        }

        sumTriangle();
        answer = getMaxSum();

        return answer;
    }

    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        triangle = new int[n][];
        cache = new int[n][n];

        for (int i = 0; i < n; i++) {
            triangle[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        br.close();
    }

    private void sumTriangle() {
        cache[0][0] = triangle[0][0];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                cache[i + 1][j] = Math.max(cache[i + 1][j], cache[i][j] + triangle[i + 1][j]);
                cache[i + 1][j + 1] = Math.max(cache[i + 1][j + 1], cache[i][j] + triangle[i + 1][j + 1]);
            }
        }
    }

    private int getMaxSum() {
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, cache[n - 1][i]);
        }
        return ret;
    }
}
