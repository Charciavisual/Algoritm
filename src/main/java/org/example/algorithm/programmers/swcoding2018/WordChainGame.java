package org.example.algorithm.programmers.swcoding2018;

import java.util.HashSet;

/**
 * Summer Winter Coding 2018 - 영어 끝말잇기
 * https://programmers.co.kr/learn/courses/30/lessons/12981
 *
 * @author Changhee Choi
 * @since 11/07/2020
 */
public class WordChainGame {

    public int[] solution(int n, String[] words) {
        int[] answer = {};
        answer = playGame(n, words);
        return answer;
    }

    private int[] playGame(int n, String[] words) {
        HashSet<String> usedWords = new HashSet<>();

        int[] ret = new int[2];
        char lastChar = words[0].charAt(0);

        for (int i = 0; i < words.length; i++) {
            if (lastChar != words[i].charAt(0) || usedWords.contains(words[i])) {
                int gamer = (i % n) + 1;
                int turn = (i / n) + 1;
                ret[0] = gamer;
                ret[1] = turn;
                break;
            }
            usedWords.add(words[i]);
            lastChar = words[i].charAt(words[i].length() - 1);
        }
        return ret;
    }
}
