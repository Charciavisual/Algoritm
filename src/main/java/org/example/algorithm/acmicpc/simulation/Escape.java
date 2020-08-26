package org.example.algorithm.acmicpc.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 탈출
 * https://www.acmicpc.net/problem/3055
 *
 * @author Changhee Choi
 * @since 26/08/2020
 */
public class Escape {
    private final int STONE = 1, WATER = 2, HOLE = 3;
    private final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int maxRow;
    private int maxCol;
    private int[][] map;
    private Queue<Pos> waters = new LinkedList<>();
    private Pos startPos;

    public void solution() {
        init();
        int answer = moveHedgehog();
        System.out.println(answer == 0 ? "KAKTUS" : answer);
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int[] mapSizeTokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            maxRow = mapSizeTokens[0];
            maxCol = mapSizeTokens[1];
            map = new int[maxRow][maxCol];

            for (int i = 0; i < mapSizeTokens[0]; i++) {
                String[] mapTokens = br.readLine().split("");
                for (int j = 0; j < mapTokens.length; j++) {
                    if (mapTokens[j].equals("S")) {
                        startPos = new Pos(i, j);
                        continue;
                    }
                    if (mapTokens[j].equals("*")) {
                        map[i][j] = WATER;
                        waters.add(new Pos(i, j));
                        continue;
                    }
                    if (mapTokens[j].equals("X")) {
                        map[i][j] = STONE;
                        continue;
                    }
                    if (mapTokens[j].equals("D")) {
                        map[i][j] = HOLE;
                    }
                }
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private int moveHedgehog() {
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maxRow][maxCol];

        queue.add(startPos);
        visited[startPos.row][startPos.col] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            spreadWater(map);

            int hedgehogSize = queue.size();
            //고슴도치를 물처럼 이동 가능한 위치로 확산
            for (int i = 0; i < hedgehogSize; i++) {
                Pos curPos = queue.poll();
                if (map[curPos.row][curPos.col] == HOLE) {
                    return count;
                }

                for (int[] dir : dirs) {
                    int nextRow = curPos.row + dir[0];
                    int nextCol = curPos.col + dir[1];
                    if (isRange(nextRow, nextCol) && !visited[nextRow][nextCol] &&
                            (map[nextRow][nextCol] == 0 || map[nextRow][nextCol] == HOLE)) {
                        queue.add(new Pos(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }

            count++;
        }

        return 0;
    }

    private void spreadWater(int[][] map) {
        int curSpreadCount = waters.size();

        for (int i = 0; i < curSpreadCount; i++) {
            Pos water = waters.poll();
            for (int[] dir : dirs) {
                int nextRow = water.row + dir[0];
                int nextCol = water.col + dir[1];
                if (isRange(nextRow, nextCol) && map[nextRow][nextCol] == 0) {
                    map[nextRow][nextCol] = WATER;
                    waters.add(new Pos(nextRow, nextCol));
                }
            }
        }
    }

    private boolean isRange(int row, int col) {
        if (row >= maxRow || row < 0 || col >= maxCol || col < 0) {
            return false;
        }
        return true;
    }

    private class Pos {
        private int row;
        private int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
