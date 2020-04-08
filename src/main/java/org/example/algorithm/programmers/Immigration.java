package org.example.algorithm.programmers;

import java.util.Arrays;

/**
 * 프로그래머스 - 입국심사
 * https://programmers.co.kr/learn/courses/30/lessons/43238
 *
 * @author Changhee Choi
 * @since 08/04/2020
 */
public class Immigration {
    /**
     * 모든사람이 심사를 받는데 걸리는 시간의 최솟값 구하기
     *
     * @param n     입국심사를 기다리는 사람의 수
     * @param times 각 심사관이 한명을 심사하는데 걸리는 시간
     * @return 모든사람이 심사를 받는데 걸리는 시간의 최솟값
     */
    public long solution(int n, int[] times) {
        long answer = 1000000000L * 1000000000L + 1L;

        Arrays.sort(times);

        int numOfExaminer = times.length;
        long maxExaminationTime = (long) times[numOfExaminer - 1] * n;

        long start = 0;
        long last = maxExaminationTime;

        while (start <= last) {
            long mid = (start + last) / 2;
            long count = 0;

            for (int i = 0; i < times.length; i++) {
                count += mid / times[i];
            }

            //검사가능한 수가 n보다 같거나 큰 경우 mid 이하의 범위로 좁혀서 다시 탐색한다.
            //n과 같을때도 범위를 좁혀서 다시 탐색하는 이유는 최소값을 찾기 위함
            if (count >= n) {
                answer = Math.min(answer, mid);
                last = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }
}
