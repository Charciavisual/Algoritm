package org.example.algorithm.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 연구소
 * https://www.acmicpc.net/problem/14502
 *
 * @author Changhee Choi
 * @since 21/07/2020
 */
public class Lab {
    private int maxRow, maxCol;
    private int[][] map;
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solution() {
        int answer = 0;
        init();
        answer = setWalls(0);
        System.out.println(answer);
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] sizeParams = br.readLine().split(" ");
            maxRow = Integer.parseInt(sizeParams[0]);
            maxCol = Integer.parseInt(sizeParams[1]);

            map = new int[maxRow][maxCol];

            for (int i = 0; i < maxRow; i++) {
                String[] mapParams = br.readLine().split(" ");
                for (int j = 0; j < maxCol; j++) {
                    map[i][j] = Integer.parseInt(mapParams[j]);
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private int[][] copyMap() {
        int[][] copiedMap = new int[maxRow][maxCol];
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        return copiedMap;
    }

    private boolean isRange(int row, int col) {
        if (row >= maxRow || row < 0) return false;
        if (col >= maxCol || col < 0) return false;
        return true;
    }

    private int countZeroValue(int[][] afterSpreadMap) {
        int zeroCount = 0;
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (afterSpreadMap[i][j] == 0) {
                    zeroCount++;
                }
            }
        }
        return zeroCount;
    }

    private int spreadVirus() {
        Queue<Pos> q = new LinkedList<>();
        //현재 연구소 상태를 복사
        int[][] spreadMap = copyMap();

        //바이러스가 퍼진 지역을 시작점으로 큐에 넣는다
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (spreadMap[i][j] == 2) {
                    q.add(new Pos(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            //상,하,좌,우로 바이러스를 퍼뜨리고 바이러스가 퍼진 위치를 큐에 넣는다.
            for (int[] direction : directions) {
                int nextRow = cur.row + direction[0];
                int nextCol = cur.col + direction[1];

                if (isRange(nextRow, nextCol) && spreadMap[nextRow][nextCol] == 0) {
                    spreadMap[nextRow][nextCol] = 2;
                    q.add(new Pos(nextRow, nextCol));
                }
            }
        }

        int zeroCount = countZeroValue(spreadMap);
        return zeroCount;
    }

    private int setWalls(int count) {
        if (count == 3) {
            return spreadVirus();
        }

        int ret = 0;

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    ret = Math.max(ret, setWalls(count + 1));
                    map[i][j] = 0;
                }
            }
        }
        return ret;
    }

    class Pos {
        private int row;
        private int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
