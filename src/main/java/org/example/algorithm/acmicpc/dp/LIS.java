package org.example.algorithm.acmicpc.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 동적계획법 - 가장 긴 증가하는 부분 수열
 * https://www.acmicpc.net/problem/11053
 *
 * @author Changhee Choi
 * @since 08/07/2020
 */
public class LIS {
    private int n;
    private int[] sequence;
    private int[] lis;

    public int solution() {
        int answer = 0;
        init();
        answer = makeLis();
        return answer;
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.nextLine());
        sequence = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        lis = new int[n];
        sc.close();
    }

    private int makeLis() {
        lis[0] = sequence[0];

        int idx = 0; //LIS의 마지막 원소를 가리키는 인덱스
        for (int i = 1; i < n; i++) {
            //LIS의 마지막 원소와 수열의 i번째 숫자를 비교하여 큰 경우 LIS의 마지막 원소로 추가
            if (lis[idx] < sequence[i]) {
                lis[++idx] = sequence[i];
            }
            /**
             * 작거나 같다면 LIS에서 수열의 i번째 숫자보다 처음으로 같거나 큰 원소의 위치를 찾아 값을 대치시킨다.
             *
             * LIS = 10 20 25 30, i번째 숫자 = 26이라면
             * lowerBoundIdx = 3이 되고, 30은 26으로 대치된다.
             * 30이 26으로 대치됨으로써 30보다 뒤에 나온 26이 30보다 앞에 놓이는 경우를 방지할 수 있고,
             * 이후에 26보다 큰 숫자가 나올 경우 LIS에 추가할 수 있게 된다.
             *
             * 만약, i번째 숫자가 20이라면, 먼저 나온 20을 다시 20으로 대치하게 되므로
             * 결론적으로는 뒤에나온 20을 선택하지 않는 것과 같다.
             */
            else {
                int lowerBoundIdx = lowerBound(idx, sequence[i]);
                lis[lowerBoundIdx] = sequence[i];
            }
        }

        return idx + 1;
    }

    //이분 탐색으로 target 보다 같거나 큰 데이터 중 가장 작은 값의 위치를 찾는다.
    private int lowerBound(int end, int target) {
        int start = 0;

        while (start < end) {
            int mid = (start + end) / 2;
            if (lis[mid] >= target)
                end = mid;
            else start = mid + 1;
        }
        return end;
    }
}
