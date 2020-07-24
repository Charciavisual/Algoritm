package org.example.algorithm.acmicpc.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 주사위 굴리기
 * https://www.acmicpc.net/problem/14499
 *
 * @author Changhee Choi
 * @since 24/07/2020
 */
public class RollDice {

    private int n;
    private int m;
    private Dice dice;
    private int[] operations;
    private int[][] map;
    private final int[][] dirs = {{0,0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // {더미, 동, 서, 북, 남}

    public void solution() {
        init();
        executeOperation();
    }

    private int[] toIntArray(String input) {
        return Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int[] initParams = toIntArray(br.readLine());
            n = initParams[0];
            m = initParams[1];
            dice = new Dice(initParams[2], initParams[3]);
            map = new int[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = toIntArray(br.readLine());
            }
            operations = toIntArray(br.readLine());
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private void executeOperation() {
        for (int operation : operations) {
            int nextRow = dice.row + dirs[operation][0];
            int nextCol = dice.col + dirs[operation][1];
            if (isRange(nextRow, nextCol)) {
                int mapValue = map[nextRow][nextCol];
                int bottomValueAfterRoll = dice.roll(nextRow, nextCol, operation);

                if (mapValue == 0) {
                    map[nextRow][nextCol] = bottomValueAfterRoll;
                } else {
                    dice.setBottomValue(mapValue);
                    map[nextRow][nextCol] = 0;
                }

                System.out.println(dice.getTopValue());
            }
        }
    }

    private boolean isRange(int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= n) return false;
        if (nextCol < 0 || nextCol >= m) return false;
        return true;
    }

    class Dice {
        private int row;
        private int col;
        /**
         * 문제 설명의 주사위 전개도 참고
         * 1 : 바닥면, 6 : 윗면
         * 4, 3 : 바닥면의 좌,우면
         * 2, 5 : 바닥면의 위,아래면
         **/
        private final int BOT = 1, TOP = 6, BOT_LEFT = 4, BOT_RIGHT = 3, BOT_UP = 2, BOT_DOWN = 5;
        private final int TEMP1 = 0, TEMP2 = 7;
        private int[] numbers;


        public Dice(int row, int col) {
            this.row = row;
            this.col = col;
            this.numbers = new int[8];
        }

        private void updateValues(int dir) {
            switch (dir) {
                //동 : 왼쪽 <- 바닥 <- 오른쪽, 왼쪽 -> 윗 -> 오른쪽
                case 1:
                    numbers[TEMP1] = numbers[BOT];
                    numbers[TEMP2] = numbers[BOT_LEFT];

                    numbers[BOT] = numbers[BOT_RIGHT];
                    numbers[BOT_LEFT] = numbers[TEMP1];

                    numbers[TEMP1] = numbers[TOP];
                    numbers[TOP] = numbers[TEMP2];
                    numbers[BOT_RIGHT] = numbers[TEMP1];
                    break;
                //서 : 왼쪽 -> 바닥 -> 오른쪽, 왼쪽 <- 윗 <- 오른쪽
                case 2:
                    numbers[TEMP1] = numbers[BOT];
                    numbers[TEMP2] = numbers[BOT_RIGHT];

                    numbers[BOT] = numbers[BOT_LEFT];
                    numbers[BOT_RIGHT] = numbers[TEMP1];

                    numbers[TEMP1] = numbers[TOP];
                    numbers[TOP] = numbers[TEMP2];
                    numbers[BOT_LEFT] = numbers[TEMP1];
                    break;
                //북 : 바닥위 -> 바닥 -> 바닥아래 -> 윗 -> 바닥위
                case 3:
                    numbers[TEMP1] = numbers[BOT];
                    numbers[TEMP2] = numbers[BOT_DOWN];

                    numbers[BOT] = numbers[BOT_UP];
                    numbers[BOT_DOWN] = numbers[TEMP1];

                    numbers[TEMP1] = numbers[TOP];
                    numbers[TOP] = numbers[TEMP2];
                    numbers[BOT_UP] = numbers[TEMP1];
                    break;
                //남 : 바닥위 <- 바닥 <- 바닥아래 <- 윗 <- 바닥위
                case 4:
                    numbers[TEMP1] = numbers[BOT];
                    numbers[TEMP2] = numbers[BOT_UP];

                    numbers[BOT] = numbers[BOT_DOWN];
                    numbers[BOT_UP] = numbers[TEMP1];

                    numbers[TEMP1] = numbers[TOP];
                    numbers[TOP] = numbers[TEMP2];
                    numbers[BOT_DOWN] = numbers[TEMP1];
            }
        }

        //굴린 후 바닥면의 숫자를 반환
        public int roll(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            updateValues(dir);
            return this.numbers[BOT];
        }

        public void setBottomValue(int number) {
            this.numbers[BOT] = number;
        }

        public int getTopValue() {
            return this.numbers[TOP];
        }
    }
}
