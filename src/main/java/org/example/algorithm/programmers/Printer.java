package org.example.algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 - 프린터
 * https://programmers.co.kr/learn/courses/30/lessons/42587
 *
 * @author Changhee Choi
 * @since 17/04/2020
 */
public class Printer {

    /**
     * @param priorities 문서의 중요도
     * @param location   인쇄를 요청한 문서의 위치
     * @return 인쇄를 요청한 문서가 인쇄되는 순서
     */
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Document> readyQueue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            boolean target = i == location;
            readyQueue.add(new Document(priorities[i], target));
        }

        while (!readyQueue.isEmpty()) {

            Document document = readyQueue.poll();

            boolean isBest = readyQueue.stream().filter(d -> d.getPriority() > document.getPriority()).count() == 0;

            if (!isBest) {
                readyQueue.add(document);
                continue;
            }

            answer++;
            if (document.isTarget()) {
                break;
            }
        }

        return answer;
    }
}

class Document {
    private int priority;
    private boolean target;

    public Document(int priority, boolean target) {
        this.priority = priority;
        this.target = target;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isTarget() {
        return target;
    }
}
