package org.example.algorithm.leetcode;

/**
 * @author Changhee Choi
 * @since 15/05/2021
 */
public class RotateImage {

  public void rotate(int[][] matrix) {
    int n = matrix.length;
    int[][] rotated = new int[n][n];

    for (int depth = 0; depth < n / 2; depth++) {
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

    int minIdx = depth;
    int maxIdx = (n - 1) - depth;

    // rotate top side
    for (int i = minIdx; i <= maxIdx; i++) {
      rotated[i][maxIdx] = origin[minIdx][i];
    }

    // rotate right side
    for (int i = minIdx; i <= maxIdx; i++) {
      rotated[maxIdx][n - i] = origin[i][maxIdx];
    }

    // rotate bottom side
    for (int i = maxIdx; i >= minIdx; i--) {
      rotated[i][minIdx] = origin[maxIdx][i];
    }

    // rotate left side
    for (int i = maxIdx; i >= minIdx; i--) {
      rotated[minIdx][n - i] = origin[i][minIdx];
    }
  }
}
