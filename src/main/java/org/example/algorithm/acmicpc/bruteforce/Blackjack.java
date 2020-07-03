package org.example.algorithm.acmicpc.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 완전탐색 - 블랙잭
 * https://www.acmicpc.net/problem/2798
 *
 * @author Changhee Choi
 * @since 03/07/2020
 */
public class Blackjack {

    private int N;
    private int M;
    private int[] cards;
    private boolean[] selected;

    public int solution() {
        int answer = 0;

        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException("초기화 과정에서 입력 오류가 발생했습니다.");
        }

        answer = playGame(3, 0);

        return answer;
    }

    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] options = br.readLine().split(" ");
        N = Integer.parseInt(options[0]);
        M = Integer.parseInt(options[1]);
        cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        selected = new boolean[N];
        br.close();
    }

    private int playGame(int count, int sum) {
        if (count == 0) {
            return sum;
        }

        int ret = 0;

        for (int i = 0; i < N; i++) {
            if (!selected[i] && sum + cards[i] <= M) {
                selected[i] = true;
                ret = Math.max(ret, playGame(count - 1, sum + cards[i]));
                selected[i] = false;
            }
        }

        return ret;
    }
}
