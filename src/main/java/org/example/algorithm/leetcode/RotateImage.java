package org.example.algorithm.leetcode;

/**
 * @author Changhee Choi
 * @since 15/05/2021
 */
public class RotateImage {
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    int[][] rotated = new int[n][n];

    for (int depth = 0; depth < n / 2 + n % 2; depth++) {
      rotateOutside(matrix, rotated, depth, n);
    }

    // copy
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = rotated[i][j];
      }
    }
  }

  private void rotateOutside(int[][] origin, int[][] rotated, int depth, int n) {

    int originMaxIdx = n - 1;
    int minIdx = depth;
    int maxIdx = originMaxIdx - depth;

    for (int i = minIdx; i <= maxIdx; i++) {
      // rotate top side
      rotated[i][maxIdx] = origin[minIdx][i];
      // rotate right side
      rotated[maxIdx][originMaxIdx - i] = origin[i][maxIdx];
    }

    for (int i = maxIdx; i >= minIdx; i--) {
      // rotate bottom side
      rotated[i][minIdx] = origin[maxIdx][i];
      // rotate left side
      rotated[minIdx][originMaxIdx - i] = origin[i][minIdx];
    }
  }
}
