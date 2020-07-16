package org.example.algorithm.acmicpc.string;

import java.io.*;
import java.util.ArrayList;

/**
 * 문자열 - 문자열 제곱
 * https://www.acmicpc.net/problem/4354
 *
 * @author Changhee Choi
 * @since 16/07/2020
 */
public class PowerStrings {

    private ArrayList<String> strings;

    public void solution() {
        init();
        for (int i = 0; i < strings.size(); i++) {
            System.out.println(calcPowIndex(strings.get(i)));
        }
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        strings = new ArrayList<>();

        try {
            String line = "";
            while (!(line = br.readLine()).equals(".")) {
                strings.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }

        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("BufferedReader close failed.");
        }
    }

    //로컬 테스트용 메소드
    public void solution2() {
        BufferedReader br;
        FileReader fr;
        int[] answer = {};

        initFromFile();

        answer = new int[this.strings.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = calcPowIndex(strings.get(i));
        }

        try {
            File file = new File("./src/main/java/org/example/algorithm/acmicpc/string/PowerStringsAnswer.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            boolean success = true;
            for (int i = 0; i < answer.length; i++) {
                int expected = Integer.parseInt(br.readLine());
                if (answer[i] != expected) {
                    success = false;
                    System.out.println(strings.get(i) + "의 기댓값 : " + expected + ", 결과값 : " + answer[i]);
                }
            }
            System.out.println(success);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }

        try {
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException("Reader close failed.");
        }
    }

    //로컬 테스트용 메소드
    private void initFromFile() {
        strings = new ArrayList<>();
        BufferedReader br;
        FileReader fr;

        try {
            File file = new File("./src/main/java/org/example/algorithm/acmicpc/string/PowerStringsInput.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = "";
            while (!(line = br.readLine()).equals(".")) {
                strings.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }

        try {
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException("Reader close failed.");
        }
    }

    /*
        1) s = ababab 일때
        s' = (abab)ab / ab(abab) = abab
        s는 같은 문자열(ab)이 3번 반복되는 형태이므로, s - s'를 하면 반복되는 문자열 ab를 찾을 수 있다.
        s % s' == 0

        2) s = abcabc 일때
        s' = (abc)abc / abc(abc) = abc
        s는 같은 문자열(abc)가 2번 반복되는 형태이므로, s - s'를 하면 반복되는 문자열 abc를 찾을 수 있다.
        s % s' == 0

        3) s = abcdabc 일때
        s' = (abc)dabc / abcd(abc) = abc
        s는 가운데 d 때문에 같은 문자열이 반복되는 형태가 아니므로, 제곱의 표현으로 나타낼 수 없다.
        s % s' != 0

        4) s = aaab 일때
        s'은 존재하지 않는다.
        제곱의 표현으로 나타낼 수 없다.
    */
    private int calcPowIndex(String s) {
        int[] pi = getPartialMatch(s);
        if (pi[s.length() - 1] == 0 || (s.length() % (s.length() - pi[s.length() - 1])) != 0) {
            return 1;
        }
        return s.length() / (s.length() - pi[s.length() - 1]);
    }

    private int[] getPartialMatch(final String pattern) {
        char[] patternChars = pattern.toCharArray();
        int len = patternChars.length;
        int[] pi = new int[len];

        int begin = 1, matched = 0;

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
