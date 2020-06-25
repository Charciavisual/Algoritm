package org.example.algorithm.programmers.heap;

import java.util.PriorityQueue;

/**
 * 프로그래머스 - 더 맵게
 * https://programmers.co.kr/learn/courses/30/lessons/42626
 *
 * @author Changhee Choi
 * @since 25/06/2020
 */
public class ScovilleScale {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int value : scoville) {
            pq.add(value);
        }

        //모든 음식을 섞어 1개만 남았거나, 가장 작은 지수가 K이상이 되면 루프를 종료
        while (pq.size() > 1 && pq.peek() < K) {
            int firstFood = pq.poll();
            int secondFood = pq.poll();
            pq.add(firstFood + (secondFood * 2));
            answer++;
        }

        //모든 음식을 섞을때까지 루프가 돌았고, 그 값이 K보다 작다면 -1 리턴
        if (pq.size() == 1 && pq.peek() < K) return -1;
        return answer;
    }
}
