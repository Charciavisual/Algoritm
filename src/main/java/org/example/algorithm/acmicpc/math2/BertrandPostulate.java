package org.example.algorithm.acmicpc.math2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 수학2 - 베르트랑 공준
 * https://www.acmicpc.net/problem/4948
 *
 * @author Changhee Choi
 * @since 01/07/2020
 */
public class BertrandPostulate {

    private ArrayList<Integer> numbers = new ArrayList<>();

    public void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = 0;
        while ((num = Integer.parseInt(br.readLine())) != 0) {
            numbers.add(num);
        }

        br.close();
    }

    public int[] solution() {
        int[] answer = new int[numbers.size()];

        for (int i = 0; i < numbers.size(); i++) {
            int count = 0;
            for (int j = numbers.get(i) + 1; j <= 2 * numbers.get(i); j++) {
                if (isPrime(j)) {
                    count++;
                }
            }
            answer[i] = count;
        }

        return answer;
    }

    private boolean isPrime(int num) {

        if (num <= 1) return false;
        if (num == 2) return true;

        if (num % 2 == 0) return false;

        int sqrt = (int) Math.sqrt(num);

        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
