package org.example.algorithm.codility.sorting;

import java.util.Arrays;

/**
 * @link https://app.codility.com/programmers/lessons/6-sorting/max_product_of_three/
 * @author Changhee Choi
 * @since 01/01/2021
 */
public class MaxProductOfThree {
  public int solution(int[] A) {
    int n = A.length;
    Arrays.sort(A);

    /**
     * 곱의 결과가 양수가 되는 경우 1. 음수 * 음수 * 양수 2. 양수 * 양수 * 양수
     *
     * <p>1의 경우에서 가장 큰 수를 얻으려면 가장 작은 음수 2개와 가장 큰 양수 1개를 곱한다. = 정렬 후 0,1,n-1 위치
     *
     * <p>2의 경우에서 가장 큰 수를 얻으려면 가장 큰 양수부터 3개를 곱한다. = 정렬 후 n-3,n-2,n-1 위치
     */
    return Math.max(A[0] * A[1] * A[n - 1], A[n - 1] * A[n - 2] * A[n - 3]);
  }
}
