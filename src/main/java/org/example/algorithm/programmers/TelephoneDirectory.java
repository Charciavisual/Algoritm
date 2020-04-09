package org.example.algorithm.programmers;

import java.util.Arrays;

/**
 * 프로그래머스 - 전화번호 목록
 * https://programmers.co.kr/learn/courses/30/lessons/42577
 *
 * @author Changhee Choi
 * @since 09/04/2020
 */
public class TelephoneDirectory {

    /**
     * 전화번호부에서 어떤 번호가 다른 번호의 접두어인 경우가 있는지 확인
     *
     * @param phone_book 전화번호 목록
     * @return 접두어인 경우가 있으면 false, 없으면 true
     */
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i].length() < phone_book[i + 1].length()) {
                if (phone_book[i + 1].startsWith(phone_book[i])) {
                    answer = false;
                    break;
                }
            }
        }

        return answer;
    }
}
