package org.example.algorithm.programmers;

import java.util.Arrays;

/**
 * 프로그래머스 - 구명보트
 * https://programmers.co.kr/learn/courses/30/lessons/42885
 *
 * @author Changhee Choi
 * @since 24/06/2020
 */
public class LifeBoat {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int weightSum = 0;

        Arrays.sort(people);

        int left = 0, right = people.length - 1;

        //남은 사람 중 몸무게가 가장 큰 사람을 포함하여 최대한 태운다.
        while (left <= right) {
            weightSum = people[right--];
            answer++;

            while (weightSum + people[left] <= limit) {
                weightSum += people[left++];
            }
        }

        return answer;
    }
}
