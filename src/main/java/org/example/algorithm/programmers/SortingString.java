package org.example.algorithm.programmers;

import java.util.Arrays;

/**
 * 연습문제 - 문자열 내 마음대로 정렬하기
 * https://programmers.co.kr/learn/courses/30/lessons/12915
 *
 * @author Changhee Choi
 * @since 10/07/2020
 */
public class SortingString {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};

        Arrays.sort(strings, (s1, s2) -> {
            if (s1.charAt(n) == s2.charAt(n)) {
                return s1.compareTo(s2);
            } else {
                return s1.charAt(n) - s2.charAt(n);
            }
        });
        answer = strings;
        return answer;
    }
}
