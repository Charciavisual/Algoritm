package org.example.algorithm.leetcode.dp;

/**
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 *
 * @author Changhee Choi
 * @since 24/10/2020
 */
public class CountSquares {
    public int countSquares(int[][] A) {
        int answer = 0;
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (A[i][j] > 0 && i > 0 && j > 0) {
                    A[i][j] = Math.min(A[i - 1][j - 1], Math.min(A[i - 1][j], A[i][j - 1])) + 1;
                }
                answer += A[i][j];
            }
        }
        return answer;
    }
}
