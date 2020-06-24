package org.example.algorithm.programmers.greedy;

/**
 * Greedy - 큰 수 만들기
 * https://programmers.co.kr/learn/courses/30/lessons/42883
 *
 * @author Changhee Choi
 * @since 24/06/2020
 */
public class BigNumber {
    public String solution(String number, int k) {
        String answer = "";
        int resultLen = number.length() - k;

        StringBuilder sb = new StringBuilder();
        sb.append(number.charAt(0)); //첫 숫자 추가

        for (int i = 1; i < number.length(); i++) {
            //추가된 숫자들 중 현재 숫자보다 작은 숫자들을 뒤에서부터 지운다.
            while (k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) < number.charAt(i)) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(number.charAt(i));
        }

        answer = sb.substring(0, resultLen);

        return answer;
    }
}
