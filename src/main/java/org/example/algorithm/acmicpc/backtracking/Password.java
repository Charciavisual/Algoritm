package org.example.algorithm.acmicpc.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 암호만들기
 * https://www.acmicpc.net/problem/1759
 *
 * @author Changhee Choi
 * @since 22/07/2020
 */
public class Password {
    private int n;
    private int m;
    private int numOfVowels;
    private int numOfConsonants;
    private char[] chars;
    private ArrayList<String> passwordCandidates;

    public void solution() {
        init();
        makePasswordCandidate();
        Collections.sort(passwordCandidates);
        for (String passwordCandidate : passwordCandidates) {
            System.out.println(passwordCandidate);
        }
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        chars = sc.nextLine().replace(" ", "").toCharArray();
        Arrays.sort(chars);

        for (int i = 0; i < chars.length; i++) {
            if (isVowel(chars[i])) {
                numOfVowels++;
            } else {
                numOfConsonants++;
            }
        }

        sc.close();
    }

    private void makePasswordCandidate() {
        passwordCandidates = new ArrayList<>();

        //사용할 수 있는 모음의 최대 갯수 = min( 입력된 모음의 전체 갯수, 비밀번호 길이 - 2:최소 자음 갯수 )
        int maxVowelCnt = Math.min(numOfVowels, n - 2);

        //사용할 수 있는 모음의 최소 갯수 = max( 1:최소 모음 갯수, 입력된 모든 자음을 사용한 후 남은 길이 )
        int minVowelCnt = Math.max(1, n - numOfConsonants);

        for (int vowelCnt = minVowelCnt; vowelCnt <= maxVowelCnt; vowelCnt++) {
            makePasswordCandidate("", 0, vowelCnt, -1);
        }
    }

    private void makePasswordCandidate(String pwdStr, int pwdLen, int remainVowel, int lastIdx) {
        if (pwdLen == n) {
            if (remainVowel == 0) {
                passwordCandidates.add(pwdStr);
            }
            return;
        }

        //chars 배열은 정렬된 상태이고, 비밀번호는 오름차순으로 정렬된 문자열만 가능하므로 이전에 사용된 문자는 다시 확인할 필요가 없다.
        //따라서 직전에 사용된 문자의 다음 위치부터 탐색한다.
        for (int i = lastIdx + 1; i < chars.length; i++) {
            if (isVowel(chars[i])) {
                makePasswordCandidate(pwdStr + chars[i], pwdLen + 1, remainVowel - 1, i);
            } else {
                makePasswordCandidate(pwdStr + chars[i], pwdLen + 1, remainVowel, i);
            }
        }
    }

    private boolean isVowel(Character c) {
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }
}
