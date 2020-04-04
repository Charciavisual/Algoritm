package org.example.algorithm.programmers;

/**
 * 프로그래머스 - 2 x n 타일링
 * https://programmers.co.kr/learn/courses/30/lessons/12900
 *
 * @author Changhee Choi
 * @since 04/04/2020
 */
public class Tiling {

    private final int DIV_NUM = 1000000007;

    /**
     * @param n 직사각형 가로의 길이, 1 <= n <= 60000
     * @return 2x1 타일로 직사각형을 채우는 방법의 수 % 1000000007
     */
    public int solution(int n) {
        int answer = 0;

        int[] numOfCases = calcNumOfCasesPerWidth(n);
        answer = numOfCases[n];

        return answer;
    }

    private int[] calcNumOfCasesPerWidth(int maxWidth) {
        int[] numOfCases = new int[maxWidth + 1];
        numOfCases[0] = 0;
        numOfCases[1] = 1;
        numOfCases[2] = 2;

        /*
        길이 n을 채우는 방법의 수
        = 길이 n-2 를 채운 상태에서 가로로 놓기 + 길이 n-1 을 채운 상태에서 세로로 놓기
        = 길이 n-2 를 채우는 방법의 수 + 길이 n-1 을 채우는 방법의 수
        */
        for (int i = 3; i <= maxWidth; i++) {
            int sumOfCases = (numOfCases[i - 2] + numOfCases[i - 1]) % DIV_NUM;
            numOfCases[i] = sumOfCases;
        }

        return numOfCases;
    }
}
