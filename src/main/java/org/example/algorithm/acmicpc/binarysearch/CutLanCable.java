package org.example.algorithm.acmicpc.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 1654 랜선 자르기
 * https://www.acmicpc.net/problem/1654
 *
 * @author Changhee Choi
 * @since 23/09/2020
 */
public class CutLanCable {
    private int k, n;
    private ArrayList<Integer> cableLengthList;

    public void solution() {
        init();
        long answer = calcMaxCableLen();
        System.out.println(answer);
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int k = numbers[0];
            int n = numbers[1];
            ArrayList<Integer> cableLengthList = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                int len = Integer.parseInt(br.readLine());
                cableLengthList.add(len);
            }
            this.k = k;
            this.n = n;
            this.cableLengthList = cableLengthList;
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private long calcMaxCableLen() {
        long ret = 1;
        long left = 1;
        long right = Integer.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;
            long countOfCables = cutCable(mid);

            if (countOfCables >= this.n) {
                ret = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ret;
    }

    private long cutCable(long len) {
        long count = 0;
        for (Integer length : this.cableLengthList) {
            count += (long) length / len;
        }
        return count;
    }
}
