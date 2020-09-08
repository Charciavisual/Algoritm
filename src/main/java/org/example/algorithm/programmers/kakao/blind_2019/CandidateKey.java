package org.example.algorithm.programmers.kakao.blind_2019;

import java.util.HashSet;
import java.util.Iterator;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42890
 *
 * @author Changhee Choi
 * @since 08/09/2020
 */
public class CandidateKey {
    public int solution(String[][] relation) {
        int answer = 0;

        HashSet<Integer> candidateKeySet = new HashSet<>();

        int setBase = (1 << relation[0].length) - 1;
        for (int set = 1; set > 0; set = (set + 1) & setBase) {
            if (checkCandidateKey(relation, set)) {
                makeMinimality(candidateKeySet, set);
            }
        }
        answer = candidateKeySet.size();
        return answer;
    }

    private void makeMinimality(HashSet<Integer> candidateKeySet, int set) {
        Iterator<Integer> itr = candidateKeySet.iterator();
        boolean flag = true;
        while (itr.hasNext()) {
            Integer prevSet = itr.next();
            if ((prevSet & set) == prevSet) flag = false;
        }

        if (flag) candidateKeySet.add(set);
    }

    private boolean checkCandidateKey(String[][] relation, int columnSet) {
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < relation.length; i++) {
            int tempSet = columnSet;
            String s = "";
            while (tempSet > 0) {
                s += relation[i][Integer.numberOfTrailingZeros(tempSet)];
                tempSet &= (tempSet - 1);
            }
            if (set.contains(s)) return false;
            else set.add(s);
        }

        return true;
    }
}