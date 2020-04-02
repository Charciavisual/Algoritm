package org.example.algorithm.programmers;

/**
 * 프로그래머스 - 탑
 * https://programmers.co.kr/learn/courses/30/lessons/42588
 *
 * @author Changhee Choi
 * @since 02/04/2020
 */
public class Tower {
    /**
     * @param heights 탑의 높이를 담은 배열, 1<= 높이 <= 100, 2<= 배열의 길이 <= 100
     * @return 각 탑에서 쏜 신호를 받은 탑의 정보
     * 발사한 신호는 신호를 보낸 탑보다 높은 탑에서만 수신합니다.
     * 신호의 방향 : 배열의 인덱스가 큰쪽 -> 작은쪽
     */
    public int[] solution(int[] heights) {
        int n = heights.length;
        int[] answer = new int[n];

        for (int i = n - 1; i > 0; i--) {

            int curTowerHeight = heights[i];

            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] > curTowerHeight) {
                    answer[i] = j + 1;
                    break;
                }
            }
        }

        return answer;
    }
}
