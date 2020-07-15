package org.example.algorithm.acmicpc.string;

import java.util.Scanner;

/**
 * 문자열 - 광고
 * https://www.acmicpc.net/problem/1305
 *
 * @author Changhee Choi
 * @since 15/07/2020
 */
public class Advertisement {

    private int L;
    private String adsString;

    public int solution() {
        int answer = 0;
        init();
        answer = getMinAdvertisement(adsString);
        return answer;
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        L = Integer.parseInt(sc.nextLine());
        adsString = sc.nextLine();
        sc.close();
    }

    //가능한 광고 중 가장 짧은 광고의 길이는 현재 보이는 광고 문자열에서 접두사가 될 수 있는 접미사 부분을 제외한 길이와 같다.
    private int getMinAdvertisement(String adsString) {
        int[] pi = getPartialMatch(adsString);
        return L - pi[L - 1];
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
