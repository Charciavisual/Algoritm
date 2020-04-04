package org.example.algorithm.programmers;

/**
 * 프로그래머스 - 종이접기
 * https://programmers.co.kr/learn/courses/30/lessons/62049
 *
 * @author Changhee Choi
 * @since 04/04/2020
 */
public class FoldingPaper {

    private int[] shapes;

    /**
     * @param n 종이를 접는 횟수
     * @return 종이에 생긴 굴곡의 모양 [0,1] 로 표현
     */
    public int[] solution(int n) {
        int[] answer = {};

        int numOfCurve = (int) Math.pow(2, n) - 1;
        shapes = new int[numOfCurve];

        fold(0, numOfCurve - 1, 0);

        answer = shapes;
        return answer;
    }

    /**
     * 한번씩 접을때 마다 범위의 시작과 끝의 중간점에 굴곡이 생성되고
     * 이전 중간점을 기준으로 왼쪽의 범위는 0의 굴곡, 오른쪽 범위는 1의 굴곡이 생성된다
     *
     * @param start 분할된 범위의 시작
     * @param end   분할된 범위의 끝
     * @param shape 굴곡의 모양
     */
    private void fold(int start, int end, int shape) {

        if (start == end) {
            shapes[start] = shape;
            return;
        }

        int mid = (start + end) / 2;

        shapes[mid] = shape;

        fold(start, mid - 1, 0);
        fold(mid + 1, end, 1);
    }
}

