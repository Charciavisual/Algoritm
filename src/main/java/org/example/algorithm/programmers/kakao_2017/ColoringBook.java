package org.example.algorithm.programmers.kakao_2017;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 - 2017 카카오코드 예선 : 카카오프렌즈 컬러링북
 * https://programmers.co.kr/learn/courses/30/lessons/1829
 *
 * @author Changhee Choi
 * @since 09/04/2020
 */
public class ColoringBook {
    private int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int N, M;

    public int[] solution(int m, int n, int[][] picture) {
        N = n;
        M = m;

        int[] answer = solve(picture);
        return answer;
    }

    private int[] solve(int[][] picture) {

        int[] result = new int[2];
        boolean[][] visited = new boolean[M][N];
        int maxSizeArea = 0;
        int numberOfArea = 0;

        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                if (picture[row][col] == 0) continue;
                if (!visited[row][col]) {
                    maxSizeArea = Math.max(maxSizeArea, bfs(picture, visited, new Area(row, col, picture[row][col])));
                    numberOfArea++;
                }
            }
        }

        result[0] = numberOfArea;
        result[1] = maxSizeArea;
        return result;
    }

    private int bfs(int[][] picture, boolean[][] visited, Area area) {
        Queue<Area> queue = new LinkedList<>();
        int count = 0;

        visited[area.getRow()][area.getCol()] = true;
        count++;
        queue.add(area);


        while (!queue.isEmpty()) {
            Area cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nextRow = cur.getRow() + dir[d][0];
                int nextCol = cur.getCol() + dir[d][1];

                if (checkRange(nextRow, nextCol) && picture[nextRow][nextCol] == cur.getColor() && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    count++;
                    queue.add(new Area(nextRow, nextCol, cur.getColor()));
                }
            }
        }

        return count;
    }

    private boolean checkRange(int nextRow, int nextCol) {
        if ((nextRow >= 0 && nextRow < M) && (nextCol >= 0 && nextCol < N)) return true;
        return false;
    }

}

class Area {
    private int row;
    private int col;
    private int color;

    public Area(int row, int col, int color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getColor() {
        return color;
    }
}