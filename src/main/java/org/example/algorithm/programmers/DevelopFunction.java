package org.example.algorithm.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 - 기능개발
 * https://programmers.co.kr/learn/courses/30/lessons/42586
 *
 * @author Changhee Choi
 * @since 02/04/2020
 */
public class DevelopFunction {

    /**
     * @param progresses 작업의 진도 배열(배포 순서대로), 1 <= 진도 <= 100 , 0 <= 배열의 길이 <= 100
     * @param speeds     각 작업의 개발 속도 배열, 1<= 개발속도 <= 100, 0 <= 배열의 길이 <= 100
     * @return 각 배포마다 배포되는 기능의 개수
     * <p>
     * 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
     * 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다.
     * 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
     */
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int n = progresses.length;
        Queue<Integer> deadlineQueue = new LinkedList<>();
        ArrayList<Integer> countDeployed = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int remainProgress = 100 - progresses[i];
            int expectedDeadline = (int) Math.ceil(remainProgress / speeds[i]);
            deadlineQueue.add(expectedDeadline);
        }

        while (!deadlineQueue.isEmpty()) {
            int curDeadline = deadlineQueue.poll();
            int count = 1;

            while (!deadlineQueue.isEmpty() && deadlineQueue.peek() <= curDeadline) {
                deadlineQueue.poll();
                count++;
            }
            countDeployed.add(count);
        }

        answer = countDeployed.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

}
