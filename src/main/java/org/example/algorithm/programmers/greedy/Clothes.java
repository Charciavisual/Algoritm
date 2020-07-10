package org.example.algorithm.programmers.greedy;

import java.util.Arrays;

/**
 * 탐욕법 - 체육복
 * https://programmers.co.kr/learn/courses/30/lessons/42862
 *
 * @author Changhee Choi
 * @since 10/07/2020
 */
public class Clothes {
    private int[] clothes;

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        init(n, lost, reserve);
        borrowClothes();
        answer = countAvailableStudents();
        return answer;
    }

    private void init(int n, int[] lost, int[] reserve) {
        clothes = new int[n + 1];
        Arrays.fill(clothes, 1);
        clothes[0] = 0;

        for (int i = 0; i < lost.length; i++) {
            clothes[lost[i]]--;
        }

        for (int i = 0; i < reserve.length; i++) {
            clothes[reserve[i]]++;
        }
    }

    private void borrowClothes() {
        for (int i = 1; i < clothes.length; i++) {
            if (clothes[i] == 0) {
                if (clothes[i - 1] == 2) {
                    clothes[i - 1]--;
                    clothes[i]++;
                } else if (i + 1 < clothes.length && clothes[i + 1] == 2) {
                    clothes[i + 1]--;
                    clothes[i]++;
                }
            }
        }
    }

    private int countAvailableStudents() {
        int count = 0;
        for (int i = 1; i < clothes.length; i++) {
            if (clothes[i] >= 1) {
                count++;
            }
        }
        return count;
    }
}
