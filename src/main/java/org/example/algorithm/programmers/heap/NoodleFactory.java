package org.example.algorithm.programmers.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 프로그래머스 - 라면공장
 * https://programmers.co.kr/learn/courses/30/lessons/42629
 *
 * @author Changhee Choi
 * @since 25/06/2020
 */
public class NoodleFactory {
    public int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        int dateIndex = 0;

        // 공급을 받을 수 있는 날이 되었을 때 공급을 받는가 받지 않는가를 결정하는 문제로 접근하는 것이 아니라
        // 재고가 다 떨어졌을때 공급을 받을 수 있는 날 중에서 가장 많이 공급을 받을 수 있는 날을 선택하는 문제로 접근한다.
        for (int i = 0; i < k; i++) {

            //i일째에 공급을 받을 수 있으면 공급받을 수 있는 양을 힙에 넣는다
            if (dateIndex < dates.length && i == dates[dateIndex]) {
                priorityQueue.add(supplies[dateIndex++]);
            }

            //재고가 다 떨어지면 힙에서 가장 큰 값을 꺼낸다 (공급받는다)
            if (stock == 0) {
                stock += priorityQueue.poll();
                answer ++;
            }

            // 밀가루를 사용하는 위치 주의
            // i일째 밀가루를 사용한 후 공급 받는 것이 아닌 i일째 남은 재고에 따라 공급을 받고 다음 일로 넘어가면서 밀가루를 사용한다.
            stock--;
        }

        return answer;
    }
}
