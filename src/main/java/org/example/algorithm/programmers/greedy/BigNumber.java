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

        //k개 만큼 지우지 못한 경우 (number == 1111000 처럼 같은 숫자가 반복되는 구조일때 발생)
        //뒤에서부터 남은 k만큼 지워준다. (가장 뒤에 나오는 숫자보다 작은 숫자들은 루프를 돌면서 지워진다)
        if (k > 0) {
            for (int i = 0; i < k; i++) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        answer = sb.toString();

        return answer;
    }
}
