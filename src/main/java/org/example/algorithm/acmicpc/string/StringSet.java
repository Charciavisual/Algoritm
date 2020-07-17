package org.example.algorithm.acmicpc.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문자열 - 문자열 집합
 *
 * @author Changhee Choi
 * @since 17/07/2020
 */
public class StringSet {

    private Set<String> sets; //List를 사용할 경우 실행시간 4816ms, Set 사용시 실행시간 464ms
    private List<String> words;

    public int solution() {
        int answer = 0;
        init();
        answer = findWords();
        return answer;
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sets = new HashSet<>();
        words = new ArrayList<>();

        try {
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < nums[0]; i++) {
                sets.add(br.readLine());
            }

            for (int i = 0; i < nums[1]; i++) {
                words.add(br.readLine());
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

    private int findWords() {
        int count = 0;
        for (String word : words) {
            if (sets.contains(word)) count++;
        }
        return count;
    }
}
