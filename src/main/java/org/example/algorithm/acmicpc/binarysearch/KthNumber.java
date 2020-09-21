package org.example.algorithm.acmicpc.binarysearch;

import java.util.Scanner;

/**
 * 1300번 k번째 수
 * https://www.acmicpc.net/problem/1300
 *
 * @author Changhee Choi
 * @since 22/09/2020
 */
public class KthNumber {

    public void solution() {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int k = sc.nextInt();
        sc.close();

        long kthNumber = findKthNumber(n, k);
        System.out.println(kthNumber);
    }

    private long findKthNumber(long n, int k) {
        long num = 1;
        long left = 1;
        long right = n * n;

        while (left <= right) {
            long mid = (left + right) / 2;
            long midIdx = findMidIdx(mid, n, k);

            if (midIdx < k) {
                left = mid + 1;
            } else {
                num = mid;
                right = mid - 1;
            }
        }
        return num;
    }

    private int findMidIdx(long mid, long n, int k) {
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            idx += Math.min(n, mid / i);
            if (idx >= k) break;
        }
        return idx;
    }
}
