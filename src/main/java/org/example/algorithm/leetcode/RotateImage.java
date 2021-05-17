package org.example.algorithm.leetcode;

/**
 * @author Changhee Choi
 * @since 15/05/2021
 */
public class RotateImage {
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    solution1(n, matrix);
  }

  private void solution1(int n, int[][] matrix) {
    final int lastIdx = n - 1;
    for (int i = 0; i < n / 2; i++) { // 몇번째 outline 인지

      // 각 outline 당 swap 해야하는 횟수
      // if, n == 4 일때,
      // 0번째 outline 이면 3번 swap 필요 (4 - 2*0 - 1)
      //    1,4,13,16 의 위치를 swap
      //    2,8,5,9 의 위치를 swap
      //    3,12,14,5 의 위치를 swap
      // 1번째 outline 이면 1번 swap 필요 (4 - 2*1 - 1)
      //    6,7,11,10 의 위치를 swap
      for (int j = 0; j < n - (2 * i) - 1; j++) {
        int temp = matrix[i][i + j];
        matrix[i][i + j] = matrix[lastIdx - i - j][i];
        matrix[lastIdx - i - j][i] = matrix[lastIdx - i][lastIdx - i - j];
        matrix[lastIdx - i][lastIdx - i - j] = matrix[i + j][lastIdx - i];
        matrix[i + j][lastIdx - i] = temp;
      }
    }
  }
}
