package org.example.algorithm.programmers.kakao_2018;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 2018 KAKAO BLIND RECRUITMENT - 뉴스 클러스터링
 * https://programmers.co.kr/learn/courses/30/lessons/17677
 *
 * @author Changhee Choi
 * @since 22/06/2020
 */
public class NewsClustering {

    private final int MULTI_NUM = 65536;

    public int solution(String str1, String str2) {
        int answer = 0;

        answer = calcJaccardSimilarity(str1, str2);

        return answer;
    }

    private ArrayList<String> tokenize(String str) {
        ArrayList<String> tokens = new ArrayList<>();

        for (int i = 0; i < str.length() - 1; i++) {
            if (Character.isAlphabetic(str.charAt(i)) && Character.isAlphabetic(str.charAt(i + 1))) {
                tokens.add(str.substring(i, i + 2));
            }
        }

        return tokens;
    }

    private int calcJaccardSimilarity(String str1, String str2) {

        int ret = 0;

        ArrayList<String> tokens1 = tokenize(str1.toUpperCase());
        ArrayList<String> tokens2 = tokenize(str2.toUpperCase());

        if (tokens1.size() == 0 && tokens2.size() == 0) {
            return MULTI_NUM;
        }

        Collections.sort(tokens1);
        Collections.sort(tokens2);

        int unionSize = findUnion(tokens1, tokens2);
        if (unionSize == 0) return MULTI_NUM;
        int intersectionSize = findIntersection(tokens1, tokens2);

        ret = (int) ((double) intersectionSize / (double) unionSize * MULTI_NUM);

        return ret;
    }

    private int findIntersection(ArrayList<String> set1, ArrayList<String> set2) {

        int count = 0;
        int idx1 = 0, idx2 = 0;

        while (idx1 < set1.size() && idx2 < set2.size()) {
            if (set1.get(idx1).equals(set2.get(idx2))) {
                count++;
                idx1++;
                idx2++;
            } else if (set1.get(idx1).compareTo(set2.get(idx2)) < 0) idx1++;
            else if (set1.get(idx1).compareTo(set2.get(idx2)) > 0) idx2++;
        }
        return count;
    }

    private int findUnion(ArrayList<String> set1, ArrayList<String> set2) {

        int count = 0;
        int idx1 = 0, idx2 = 0;

        while (idx1 < set1.size() && idx2 < set2.size()) {
            if (set1.get(idx1).equals(set2.get(idx2))) {
                count++;
                idx1++;
                idx2++;
            } else if (set1.get(idx1).compareTo(set2.get(idx2)) < 0) {
                count++;
                idx1++;
            } else if (set1.get(idx1).compareTo(set2.get(idx2)) > 0) {
                count++;
                idx2++;
            }
        }

        if (idx1 == set1.size()) {
            while (idx2 < set2.size()) {
                count++;
                idx2++;
            }
        }
        if (idx2 == set2.size()) {
            while (idx1 < set1.size()) {
                count++;
                idx1++;
            }
        }
        return count;
    }
}
