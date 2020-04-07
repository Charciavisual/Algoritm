package org.example.algorithm.programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 프로그래머스 - 이중우선순위 큐
 * https://programmers.co.kr/learn/courses/30/lessons/42628
 *
 * @author Changhee Choi
 * @since 07/04/2020
 */
public class DoublePriorityQueue {

    private PriorityQueue<Integer> pqMax = new PriorityQueue<>(Comparator.reverseOrder());
    private PriorityQueue<Integer> pqMin = new PriorityQueue<>();

    /**
     * @param operations 이중 우선순위 큐가 할 연산
     * @return 큐가 비어있으면 [0,0], 비어있지 않으면 [최댓값, 최솟값]
     */
    public int[] solution(String[] operations) {
        int[] answer = {};

        // I 숫자 : 큐에 숫자를 삽입
        // D 1 : 큐에서 최댓값 삭제
        // D -1 : 큐에서 최솟값 삭제
        for (int i = 0; i < operations.length; i++) {
            executeOperation(operations[i]);
        }

        answer = new int[2];

        answer[0] = getMaxValue();
        answer[1] = getMinValue();

        return answer;
    }

    private void executeOperation(String operation) {
        String[] operationToken = operation.split(" ");
        String type = operationToken[0];
        String data = operationToken[1];

        if (type.equals("I")) {
            insertData(Integer.valueOf(data));
        } else if (type.equals("D")) {
            if (data.equals("1")) {
                deleteMaxValue();
            } else if (data.equals("-1")) {
                deleteMinValue();
            }
        }
    }

    private void insertData(int data) {
        pqMax.add(data);
        pqMin.add(data);
    }

    private void deleteMaxValue() {
        if (pqMax.isEmpty()) return;
        int data = pqMax.poll();
        pqMin.remove(data);
    }

    private void deleteMinValue() {
        if (pqMin.isEmpty()) return;
        int data = pqMin.poll();
        pqMax.remove(data);
    }

    private int getMaxValue() {
        if (pqMax.isEmpty())
            return 0;
        else
            return pqMax.poll();
    }

    private int getMinValue() {
        if (pqMin.isEmpty())
            return 0;
        else
            return pqMin.poll();
    }
}
