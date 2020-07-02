package org.example.algorithm.acmicpc.math2;

import java.util.Scanner;

/**
 * 수학2 - 골드바흐의 추측
 * https://www.acmicpc.net/problem/9020
 *
 * @author Changhee Choi
 * @since 02/07/2020
 */
public class GoldbachConjecture {

    private int count;
    private int[] numbers;

    public String[] solution() {
        init();
        String[] answer = new String[count];

        for (int i = 0; i < count; i++) {
            String partition = selectPartition(numbers[i]);
            answer[i] = partition;
        }

        return answer;
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        count = sc.nextInt();

        numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = sc.nextInt();
        }
        sc.close();
    }

    private String selectPartition(int number) {
        String partition = "";

        int left = number / 2, right = number / 2;
        while (left >= 2 && right <= number) {
            //left는 1씩 줄이고 right는 1씩 늘리므로 항상 합은 number이기 때문에 조건으로 검사하지 않는다.
            if (isPrime(left) && isPrime(right)) {
                partition += left + " " + right;
                break;
            }
            left --;
            right ++;
        }

        return partition;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;

        //2를 제외한 모든 짝수는 소수가 아님
        if (num % 2 == 0) return false;

        int sqrtn = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrtn; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
