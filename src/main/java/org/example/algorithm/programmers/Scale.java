package org.example.algorithm.programmers;

import java.util.Arrays;

/**
 * 프로그래머스 - 저울
 * https://programmers.co.kr/learn/courses/30/lessons/42886
 *
 * @author Changhee Choi
 * @since 09/04/2020
 */
public class Scale {
    /**
     * 주어진 저울 추들로 측정할 수 없는 무게의 최소값
     *
     * @param weight 저울 추 무게 정보가 담긴 배열
     * @return 측정할 수 없는 무게의 최소값
     */
    public int solution(int[] weight) {
        int answer = 0;

        Arrays.sort(weight);

        //추의 무게들을 합친 무게까지가 그 추들을 이용해서 측정할 수 있는 무게의 한계라는 점을 이용
        //다음 원소는 (현재까지의 합 + 1) 보다 작거나 같은 무게
        for (int i = 0; i < weight.length; i++) {
            if (answer + 1 < weight[i]) break;
            answer += weight[i];
        }

        return answer + 1;
    }
}
