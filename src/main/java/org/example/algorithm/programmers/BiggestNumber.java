package org.example.algorithm.programmers;

import java.util.Arrays;

/**
 * 프로그래머스 - 가장 큰 수
 * https://programmers.co.kr/learn/courses/30/lessons/42746
 *
 * @author Changhee Choi
 * @since 09/04/2020
 */
public class BiggestNumber {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        //변환된 문자열을 합쳤을때 더 큰 수가 되도록 정렬
        //두 수가 10, 2 일때 102 보다 210 이 크므로 2와 10의 위치를 바꾼다.
        Arrays.sort(arr, (o1, o2) -> Integer.valueOf(o2 + o1).compareTo(Integer.valueOf(o1 + o2)));

        if (arr[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }

        return sb.toString();
    }
}
