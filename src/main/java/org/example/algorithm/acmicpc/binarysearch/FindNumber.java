package org.example.algorithm.acmicpc.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 1920 수 찾기
 * https://www.acmicpc.net/problem/1920
 *
 * @author Changhee Choi
 * @since 22/09/2020
 */
public class FindNumber {
    private int n, m;
    private int[] numbers, targetNumbers;

    public void solution() {
        init();

        int[] answer = new int[m];
        for (int i = 0; i < targetNumbers.length; i++) {
            answer[i] = findNumber(targetNumbers[i]) ? 1 : 0;
        }
        for (int i : answer) {
            System.out.println(i);
        }
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());
            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int m = Integer.parseInt(br.readLine());
            int[] targetNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            this.n = n;
            this.m = m;
            Arrays.sort(numbers);
            this.numbers = numbers;
            this.targetNumbers = targetNumbers;
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private boolean findNumber(int targetNumber) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (numbers[mid] == targetNumber) {
                return true;
            }

            if (numbers[mid] < targetNumber) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
