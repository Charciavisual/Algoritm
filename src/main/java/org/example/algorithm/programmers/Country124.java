package org.example.algorithm.programmers;

/**
 * 프로그래머스 - 124 나라의 숫자
 *
 * @author Changhee Choi
 * @since 01/04/2020
 */
public class Country124 {
    //n <= 500000000
    public String solution(int n) {
        String answer = "";

        while (n > 0) {
            int rest = n % 3;
            n = n / 3;

            //나머지가 0인 경우 4를 더해주고 몫을 1 감소시킨다
           if (rest == 0) {
                answer = "4" + answer;
                n = n - 1;
            }
            else answer = rest + answer;
        }

        return answer;
    }
}
