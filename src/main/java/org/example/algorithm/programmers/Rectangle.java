package org.example.algorithm.programmers;

/**
 * 프로그래머스 - 멀쩡한 사각형
 * https://programmers.co.kr/learn/courses/30/lessons/62048
 *
 * @author Changhee Choi
 * @since 17/04/2020
 */
public class Rectangle {
    /**
     * @param w 직사각형 가로길이
     * @param h 직사각형 세로길이
     * @return 사용할 수 있는 정사각형의 개수
     */
    public long solution(int w, int h) {
        long answer = 1;
        int gcd = 1;
        long sum = (long)w * (long)h;

        //gcd를 w와 h의 최대 공약수라 할 때,
        //선이 지나면서 생기는 블록의 크기는  (w / gcd) x  (h / gcd)  이다.
        //한 블록 안에서는 (블록의 가로 크기 + 블록의 세로 크기 - 1) 수만큼의 사각형 위로 선이 지나간다.
        for (int i = (w < h) ? w : h; i > 0; i--) {
            if ((w % i == 0) && (h % i == 0)) {
                gcd = i;
                break;
            }
        }
        answer =  sum - gcd * ((w / gcd) + (h / gcd) - 1);
        return answer;
    }
}
