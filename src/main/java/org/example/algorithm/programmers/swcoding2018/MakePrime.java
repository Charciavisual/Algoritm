package org.example.algorithm.programmers.swcoding2018;

/**
 * Summer Winter Coding 2018 - 소수 만들기
 * https://programmers.co.kr/learn/courses/30/lessons/12977
 *
 * @author Changhee Choi
 * @since 11/07/2020
 */

import java.util.ArrayList;

public class MakePrime {
    //1. 3개의 수를 더해서 만들수 있는 수를 모두 구한다 = 50 * 49 * 48
    //2. 그 중 소수인 것을 카운팅한다.
    public int solution(int[] nums) {
        int answer = -1;

        ArrayList<Integer> sums = makeSum(nums);
        answer = countPrime(sums);

        return answer;
    }

    private ArrayList<Integer> makeSum(int[] nums) {
        ArrayList<Integer> ret = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    ret.add(nums[i] + nums[j] + nums[k]);
                }
            }
        }

        return ret;
    }

    private int countPrime(ArrayList<Integer> sums) {
        int count = 0;
        for (Integer sum : sums) {
            if (isPrime(sum)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int n) {
        if (n == 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int sqrtN = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrtN; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}