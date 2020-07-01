package org.example.algorithm.acmicpc.math1;

/**
 * 수학1 - 벌집
 * https://www.acmicpc.net/problem/2292
 *
 * @author Changhee Choi
 * @since 01/07/2020
 */
public class Hive {
    public int solution(int n) {

        if (n == 1) return 1;
        int lastRoomNum = 1;
        int increase = 1;

        while (lastRoomNum < n) {
            lastRoomNum += increase * 6;
            increase ++;
        }

        return increase;
    }
}
