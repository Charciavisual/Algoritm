package org.example.algorithm.acmicpc.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 10815 숫자 카드
 * https://www.acmicpc.net/problem/10815
 *
 * @author Changhee Choi
 * @since 25/09/2020
 */
public class NumberCard {
    private int N, M;
    private int[] cards, targetNumbers;

    public void solution() {
        readInput();
        StringBuilder sb = new StringBuilder();
        for (int target : targetNumbers) {
            sb.append(findCard(target)).append(" ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    private int findCard(int number) {
        int left = 0, right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (cards[mid] == number) {
                return 1;
            }

            if (cards[mid] < number) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }

    private void readInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(br.readLine());
            cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
            M = Integer.parseInt(br.readLine());
            targetNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
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

