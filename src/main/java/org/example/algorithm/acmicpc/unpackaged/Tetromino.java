package org.example.algorithm.acmicpc.unpackaged;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 테트로미노
 * https://www.acmicpc.net/problem/14500
 *
 * @author Changhee Choi
 * @since 23/07/2020
 */
public class Tetromino {

    private int n, m;
    private int[][] numbers;
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private final int[][][] TShapes = {
            {{0, 0}, {1, 0}, {2, 0}, {1, 1}},  //ㅏ
            {{0, 0}, {1, 0}, {2, 0}, {1, -1}}, //ㅓ
            {{0, 0}, {1, -1}, {1, 0}, {1, 1}}, //ㅗ
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}}   //ㅜ
    };
    private boolean[][] visited;

    public void solution() {
        init();
        System.out.println(getMaxSum());
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int[] sizeParams = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = sizeParams[0];
            m = sizeParams[1];

            numbers = new int[n][];

            for (int i = 0; i < n; i++) {
                numbers[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private int getMaxSum() {
        visited = new boolean[n][m];
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret = Math.max(ret, getMaxSumOf_T_Shape(i, j));

                visited[i][j] = true;
                ret = Math.max(ret, getMaxSumOf_OtherShapes(i, j, numbers[i][j], 1));
                visited[i][j] = false;
            }
        }
        return ret;
    }

    //T-shape
    private int getMaxSumOf_T_Shape(int row, int col) {
        int ret = 0;
        for (int[][] shape : TShapes) {
            int sum = 0;
            int sumCnt = 0;

            for (int j = 0; j < 4; j++) {
                int nextRow = row + shape[j][0];
                int nextCol = col + shape[j][1];

                if (isRange(nextRow, nextCol)) {
                    sum += numbers[nextRow][nextCol];
                    sumCnt++;
                }
            }

            if (sumCnt == 4) {
                ret = Math.max(ret, sum);
            }
        }
        return ret;
    }

    //Z-shape, L-shape, Line-shape, Square-shape
    private int getMaxSumOf_OtherShapes(int row, int col, int sum, int blockCnt) {
        if (blockCnt == 4) {
            return sum;
        }

        int ret = 0;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + directions[i][0];
            int nextCol = col + directions[i][1];

            if (isRange(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                ret = Math.max(ret, getMaxSumOf_OtherShapes(nextRow, nextCol, sum + numbers[nextRow][nextCol]
                        , blockCnt + 1));
                visited[nextRow][nextCol] = false;
            }
        }
        return ret;
    }

    private boolean isRange(int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= n) return false;
        if (nextCol < 0 || nextCol >= m) return false;
        return true;
    }
}