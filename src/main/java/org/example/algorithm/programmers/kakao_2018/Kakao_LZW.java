package org.example.algorithm.programmers.kakao_2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 2018 KAKAO BLIND RECRUITMENT - 압축
 * https://programmers.co.kr/learn/courses/30/lessons/17684
 * @author Changhee Choi
 * @since 19/06/2020
 */
public class Kakao_LZW {

    public int[] solution(String msg) {
        int[] answer = {};
        Map<String, Integer> indexMap = new HashMap<>();
        init(indexMap);

        answer = compression(msg, indexMap);
        return answer;
    }

    private void init(Map<String, Integer> indexMap) {
        for (int i = 0; i < 26; i++) {
            indexMap.put(String.valueOf((char) ('A' + i)), i + 1);
        }
    }

    private int[] compression(String msg, Map<String, Integer> indexMap) {

        ArrayList<Integer> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int idx = 0;

        while (idx < msg.length()) {

            sb.append(msg.charAt(idx));

            //마지막 단어까지 추가했거나, 다음 단어를 추가했을 때 인덱스가 존재하지 않으면 루프 종료
            while (idx < msg.length() - 1 && indexMap.get(sb.toString() + msg.charAt(idx + 1)) != null) {
                sb.append(msg.charAt(++idx));
            }

            Integer curWordIndex = indexMap.get(sb.toString());
            result.add(curWordIndex);

            if (idx < msg.length() - 1) {
                indexMap.put(sb.toString() + msg.charAt(idx + 1), indexMap.size() + 1);
            }

            //reset
            sb.delete(0, sb.length());
            //다음 알파벳으로 이동
            idx++;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
