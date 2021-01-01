package org.example.algorithm.codility.sorting;

import java.util.Arrays;

/**
 * @link https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
 * @author Changhee Choi
 * @since 01/01/2021
 */
public class NumberOfDiscIntersections {
  public int solution(int[] A) {
    final int INF = 10000000;
    final int n = A.length;
    int ans = countIntersect(A, n);
    return ans > INF ? -1 : ans;
  }

  private int countIntersect(int[] A, int n) {
    final long[] startPosList = new long[n];
    final long[] endPosList = new long[n];

    for (int i = 0; i < A.length; i++) {
      startPosList[i] = (long) i - (long) A[i];
      endPosList[i] = (long) i + (long) A[i];
    }

    Arrays.sort(startPosList);
    Arrays.sort(endPosList);

    int sIdx = 0;
    int eIdx = 0;
    int containsCircle = 0;
    int count = 0;

    while (sIdx < n) {
      while (startPosList[sIdx] > endPosList[eIdx]) {
        eIdx++;
        containsCircle--;
      }
      containsCircle++;
      count += containsCircle - 1;
      sIdx++;
    }

    return count;
  }
}
