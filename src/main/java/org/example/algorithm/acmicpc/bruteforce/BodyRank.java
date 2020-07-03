package org.example.algorithm.acmicpc.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 완전탐색 - 덩치등수
 *
 * @author Changhee Choi
 * @since 03/07/2020
 */
public class BodyRank {

    private int N;
    private int[][] bodyInfo;

    public String solution() {
        String answer = "";

        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException("초기화 과정에서 입력 오류가 발생했습니다.");
        }

        int[] ranks = calcRank();
        answer = makeRankString(ranks);
        return answer;
    }

    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        bodyInfo = new int[N][2];

        for (int i = 0; i < N; i++) {
            bodyInfo[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        br.close();
    }

    private int[] calcRank() {
        int[] ret = new int[N];
        Arrays.fill(ret, 1);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (bodyInfo[i][0] > bodyInfo[j][0] && bodyInfo[i][1] > bodyInfo[j][1]) {
                    ret[j]++;
                } else if (bodyInfo[i][0] < bodyInfo[j][0] && bodyInfo[i][1] < bodyInfo[j][1]) {
                    ret[i]++;
                }
            }
        }
        return ret;
    }

    private String makeRankString(int[] ranks) {
        StringBuilder sb = new StringBuilder();
        for (int rank : ranks) {
            sb.append(rank).append(" ");
        }
        return sb.toString().trim();
    }
}
