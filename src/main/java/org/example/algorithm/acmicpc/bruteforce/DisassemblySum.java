package org.example.algorithm.acmicpc.bruteforce;

import java.util.Scanner;

/**
 * @author Changhee Choi
 * @since 18/07/2020
 */
public class DisassemblySum {
    private int n;

    public int solution() {
        int answer = 0;
        init();
        answer = findConstructor();
        return answer;
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.close();
    }

    private int findConstructor() {
        int digit = calcDigit(n);

        // n이 x자리수의 숫자인 경우
        // 생성자의 범위는 n - (9 * x) <= m <= n - (0 * x) 이다.
        // (각 자리에서 최소값 = 0, 최대값 = 9이다.)
        int minConstructor = n - 9 * digit;
        int maxConstructor = n;

        for (int num = minConstructor; num <= maxConstructor; num++) {
            if (calcDisassemblySum(num, digit) == n) {
                return num;
            }
        }
        return 0;
    }

    private int calcDigit(int num) {
        int digit = 0;
        while (num > 0) {
            num /= 10;
            digit++;
        }
        return digit;
    }

    private int calcDisassemblySum(int num, int digit) {
        int disassemblySum = num;
        int divNum = 1;

        for (int i = 0; i < digit - 1; i++) {
            divNum *= 10;
        }

        while (num > 0) {
            int disassembledNum = num / divNum;
            disassemblySum += disassembledNum;
            num -= disassembledNum * divNum;
            divNum /= 10;
        }

        return disassemblySum;
    }
}
