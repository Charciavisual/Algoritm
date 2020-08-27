package org.example.algorithm.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 보물섬
 * https://www.acmicpc.net/problem/2589
 *
 * @author Changhee Choi
 * @since 27/08/2020
 */
public class TreasureIsland {
    private int maxRow, maxCol;
    private String[][] map;
    private boolean[][] visited;
    private final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void solution() {
        init();
        int answer = calcDistanceTreasures();
        System.out.println(answer);
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] mapSizeTokens = br.readLine().split(" ");
            maxRow = Integer.parseInt(mapSizeTokens[0]);
            maxCol = Integer.parseInt(mapSizeTokens[1]);

            map = new String[maxRow][maxCol];

            for (int i = 0; i < maxRow; i++) {
                map[i] = br.readLine().split("");
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private void initVisited() {
        visited = new boolean[maxRow][maxCol];
        for (int row = 0; row < maxRow; row++) {
            Arrays.fill(visited[row], false);
        }
    }

    private int calcDistanceTreasures() {
        int ret = 0;
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                if (map[row][col].equals("L")) {
                    initVisited();
                    ret = Math.max(ret, bfs(row, col));
                }
            }
        }
        return ret;
    }

    private int bfs(int row, int col) {
        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(row, col));
        visited[row][col] = true;

        int distance = -1;

        while (!queue.isEmpty()) {
            int qSize = queue.size();
            distance++;

            for (int i = 0; i < qSize; i++) {
                Pos curPos = queue.poll();
                for (int[] dir : dirs) {
                    int nextRow = curPos.row + dir[0];
                    int nextCol = curPos.col + dir[1];
                    if (isRange(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol].equals("L")) {
                        queue.add(new Pos(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        return distance;
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