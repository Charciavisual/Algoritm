package org.example.algorithm.leetcode.dp;

/**
 * https://leetcode.com/problems/matrix-block-sum/
 *
 * @author Changhee Choi
 * @since 24/10/2020
 */
public class MatrixBlockSum {

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] preCalculateRangeSumArray = getPreCalculateRangeSumArray(mat, m, n);
        int[][] answer = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r1 = Math.max(i - K, 0), c1 = Math.max(j - K, 0);
                int r2 = Math.min(i + K, m - 1), c2 = Math.min(j + K, n - 1);

                int rangeSumR2C2 = preCalculateRangeSumArray[r2][c2];
                int partRangeSumR1C2 = r1 == 0 ? 0 :
                                       preCalculateRangeSumArray[r1 - 1][c2];
                int partRangeSumR2C1 = c1 == 0 ? 0 :
                                       preCalculateRangeSumArray[r2][c1 - 1];
                int partRangeSumR1C1 =
                        partRangeSumR1C2 == 0 || partRangeSumR2C1 == 0 ? 0 :
                        preCalculateRangeSumArray[r1 - 1][c1 - 1];

                answer[i][j] = rangeSumR2C2
                               - partRangeSumR1C2
                               - partRangeSumR2C1
                               + partRangeSumR1C1;
            }
        }

        return answer;
    }

    private int[][] getPreCalculateRangeSumArray(int[][] mat, int m, int n) {
        int[][] sum = new int[m][n];
        sum[0][0] = mat[0][0];

        for (int i = 1; i < n; i++) {
            sum[0][i] = sum[0][i - 1] + mat[0][i];
        }
        for (int i = 1; i < m; i++) {
            sum[i][0] = sum[i - 1][0] + mat[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1]
                            + mat[i][j];
            }
        }

        return sum;
    }
}
