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
        int num = 1;
        while (num < n) {
            int disassemblySum = calcDisassemblySum(num);
            if (disassemblySum == n) {
                return num;
            }
            num++;
        }
        return 0;
    }

    private int calcDisassemblySum(int num) {
        int disassemblySum = num;
        int divNum = 1000000;

        while (num > 0) {
            if (divNum == 0) {
                disassemblySum += num;
                num = 0;
                continue;
            }
            int disassembledNum = num / divNum;
            if (disassembledNum > 0) {
                disassemblySum += disassembledNum;
                num -= disassembledNum * divNum;
            }
            divNum /= 10;
        }

        return disassemblySum;
    }
}
