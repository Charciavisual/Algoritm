package org.example.algorithm.acmicpc.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 2805 나무자르기
 * https://www.acmicpc.net/problem/2805
 *
 * @author Changhee Choi
 * @since 25/09/2020
 */
public class CutTree {
    private int N, M;
    private int[] trees;

    public void solution() {
        readInput();
        long answer = 0;
        long left = 1, right = 1000000000;
        while (left <= right) {
            long height = (left + right) / 2;
            long sumCutTrees = calcSumCutTrees(height);

            if (sumCutTrees >= M) {
                answer = height;
                left = height + 1;
            } else {
                right = height - 1;
            }
        }
        System.out.println(answer);
    }

    private long calcSumCutTrees(long height) {
        long sum = 0;

        for (int tree : trees) {
            int diff = tree - (int) height;
            sum += Math.max(diff, 0);
        }

        return sum;
    }

    private void readInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            this.N = numbers[0];
            this.M = numbers[1];
            this.trees = trees;
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
                }
            }
        }
    }
}
