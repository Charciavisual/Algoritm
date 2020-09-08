package org.example.algorithm.programmers.kakao.blind_2019;

import java.util.ArrayList;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42889
 *
 * @author Changhee Choi
 * @since 08/09/2020
 */
public class FailureRate {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];

        ArrayList<Stage> list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            int users = 0;
            int failedUsers = 0;

            for (int stage : stages) {
                if (stage >= i) {
                    if (stage == i) failedUsers++;
                    users++;
                }
            }

            double failureRate = (users == 0) ? 0 : (double) failedUsers / (double) users;
            list.add(new Stage(i, failureRate));
        }

        list.sort((Stage s1, Stage s2) -> {
            if (s2.getFailureRate().compareTo(s1.getFailureRate()) == 0)
                return s1.getNo() - s2.getNo();
            return s2.getFailureRate().compareTo(s1.getFailureRate());
        });

        answer = list.stream().map(Stage::getNo).mapToInt(Integer::valueOf).toArray();

        return answer;
    }
}

class Stage {
    private int no;
    private Double failureRate;

    public Stage(int no, Double failureRate) {
        this.no = no;
        this.failureRate = failureRate;
    }

    public int getNo() {
        return no;
    }

    public Double getFailureRate() {
        return failureRate;
    }
}
