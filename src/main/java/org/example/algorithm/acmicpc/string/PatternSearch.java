package org.example.algorithm.acmicpc.string;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 문자열 - 찾기
 * https://www.acmicpc.net/problem/1786
 *
 * @author Changhee Choi
 * @since 14/07/2020
 */
public class PatternSearch {

    private String text;
    private String pattern;

    public int[] solution() {
        int[] answer = {};
        init();
        answer = search(text, pattern);
        return answer;
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        text = sc.nextLine();
        pattern = sc.nextLine();
        sc.close();
    }

    private int[] search(String text, String pattern) {
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        int textLen = textChars.length;
        int patternLen = patternChars.length;

        ArrayList<Integer> ret = new ArrayList<>();
        int[] pi = getPartialMatch(pattern);
        int begin = 0, matched = 0;

        while (begin + patternLen <= textLen) {
            if (matched < patternLen && textChars[begin + matched] == patternChars[matched]) {
                matched++;
                //패턴과 모두 일치하면 답에 추가
                if (matched == patternLen) {
                    ret.add(begin + 1); //문제에서 첫번째 문자의 인덱스를 1로 다루므로 실제 인덱스에 +1을 해준다.
                }
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    //현재 matched는 처음으로 mismatch가 발생한 인덱스를 가리킨다.
                    //따라서 패턴의 접두사와 일치하는 접미사의 최대 길이는 pi[matched-1]로 구할 수 있다.
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1]; //변경된 begin에서 일치하는 부분만큼 matched 값을 변경
                }
            }
        }

        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    private int[] getPartialMatch(final String pattern) {
        char[] patternChars = pattern.toCharArray();
        int len = patternChars.length;
        /* pi[i] : keyword[...i]의 접미사도 되고 접두사도 되는 문자열의 최대길이 */
        int[] pi = new int[len];

        // begin == 0 이면 자기 자신을 찾는다.
        int begin = 1, matched = 0;

        // 비교할 문자가 N의 끝에 도달할 때까지 부분 일치를 모두 기록
        while (begin + matched < len) {
            if (patternChars[begin + matched] == patternChars[matched]) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0) begin++;
                else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return pi;
    }

}
