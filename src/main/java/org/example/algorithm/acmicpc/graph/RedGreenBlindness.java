package org.example.algorithm.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 적록색약
 * https://www.acmicpc.net/problem/10026
 *
 * @author Changhee Choi
 * @since 28/07/2020
 */
public class RedGreenBlindness {
    private int n;
    private String[][] normalMap;
    private String[][] rgBlindnessMap;
    private boolean[][] checked;

    public void solution() {
        init();
        int normalCount = countArea(normalMap);
        int rgBlindnessCount = countArea(rgBlindnessMap);

        System.out.println(normalCount + " " + rgBlindnessCount);
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            n = Integer.parseInt(br.readLine());
            this.normalMap = new String[n][n];
            this.rgBlindnessMap = new String[n][n];

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                this.normalMap[i] = line.split("");
                this.rgBlindnessMap[i] = line.replace("G", "R").split("");
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private void initChecked() {
        this.checked = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(this.checked[i], false);
        }
    }

    private int countArea(String[][] map) {
        initChecked();
        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (this.checked[row][col]) {
                    continue;
                }
                checkArea(map, row, col, map[row][col]);
                count++;
            }
        }
        return count;
    }

    private void checkArea(String[][] map, int row, int col, String color) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(row, col));
        this.checked[row][col] = true;
        while (!queue.isEmpty()) {
            Pos curPos = queue.poll();

            for (int[] direction : directions) {
                int nextRow = curPos.getRow() + direction[0];
                int nextCol = curPos.getCol() + direction[1];
                if (isRange(nextRow, nextCol) && !this.checked[nextRow][nextCol] && map[nextRow][nextCol].equals(color)) {
                    queue.add(new Pos(nextRow, nextCol));
                    this.checked[nextRow][nextCol] = true;
                }
            }
        }
    }

    private boolean isRange(int nextRow, int nextCol) {
        if (nextRow >= n || nextRow < 0) return false;
        if (nextCol >= n || nextCol < 0) return false;
        return true;
    }
}

class Pos {
    private int row;
    private int col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}