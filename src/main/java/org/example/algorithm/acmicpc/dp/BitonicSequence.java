package org.example.algorithm.acmicpc.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 동적계획법 - 가장 긴 바이토닉 부분 수열
 * https://www.acmicpc.net/problem/11054
 *
 * @author Changhee Choi
 * @since 09/07/2020
 */
public class BitonicSequence {

    private int n;
    private int[] sequence;
    private int[] lis;
    private int[] reverseLis;

    public int solution() {
        int answer = 0;
        init();
        makeLis();
        makeReverseLis();
        answer = getMaxBitonicSequenceLength();
        return answer;
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.nextLine());
        lis = new int[n];
        reverseLis = new int[n];
        sequence = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sc.close();
    }

    //앞에서 시작하여 LIS를 생성
    private void makeLis() {
        for (int i = 0; i < n; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                /**
                 * lis의 마지막 원소를 sequence[j]로 했을때
                 * sequence[i]가 마지막 원소보다 크고 (sequence[i] > sequence[j])
                 * sequence[i]를 추가했을때 가장 긴 길이로 lis[i]를 갱신한다. (lis[i] < lis[j] + 1)
                 */
                if (sequence[j] < sequence[i] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
    }

    //뒤에서 시작하여 LIS를 생성
    private void makeReverseLis() {
        for (int i = n - 1; i >= 0; i--) {
            reverseLis[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (sequence[j] < sequence[i] && reverseLis[i] < reverseLis[j] + 1) {
                    reverseLis[i] = reverseLis[j] + 1;
                }
            }
        }
    }

    private int getMaxBitonicSequenceLength() {
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, lis[i] + reverseLis[i] - 1); //lis와 reverseLis가 만나는 지점의 원소가 중복되므로 1을 빼준다
        }
        return ret;
    }
}
