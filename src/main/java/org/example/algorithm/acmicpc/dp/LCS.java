package org.example.algorithm.acmicpc.dp;

import java.util.Scanner;

/**
 * LCS
 * https://www.acmicpc.net/problem/9251
 *
 * @author Changhee Choi
 * @since 24/07/2020
 */
public class LCS {
    private String[] strings = new String[2];

    public void solution() {
        init();
        int answer = calcLCSLength("0" + strings[0], "0" + strings[1]);
        System.out.println(answer);
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        strings[0] = sc.nextLine();
        strings[1] = sc.nextLine();
        sc.close();
    }

    private int calcLCSLength(String s1, String s2) {
        int[][] lengthOfLCS = new int[s1.length()][s2.length()];

        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                //문자가 서로 같으면 왼쪽 대각선 위 요소의 값에 1을 더한 값으로 설정
                if (s1.charAt(i) == s2.charAt(j)) {
                    lengthOfLCS[i][j] = lengthOfLCS[i - 1][j - 1] + 1;
                } else {
                    //왼쪽 또는 위의 값중 큰 값을 가져온다.
                    lengthOfLCS[i][j] = Math.max(lengthOfLCS[i - 1][j], lengthOfLCS[i][j - 1]);
                }
            }
        }

        return lengthOfLCS[s1.length() - 1][s2.length() - 1];
    }
}
