package org.example.algorithm.acmicpc.unpackaged;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 로봇청소기
 * https://www.acmicpc.net/problem/14503
 *
 * @author Changhee Choi
 * @since 21/07/2020
 */
public class RoboticVacuum {
    private int maxRow, maxCol;
    private int[][] map;
    private State startState;

    public void solution() {
        int answer = 0;
        init();
        answer = cleaning();
        System.out.println(answer);
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] sizeParams = br.readLine().split(" ");
            maxRow = Integer.parseInt(sizeParams[0]);
            maxCol = Integer.parseInt(sizeParams[1]);

            String[] vacuumParams = br.readLine().split(" ");
            int vacuumRow = Integer.parseInt(vacuumParams[0]);
            int vacuumCol = Integer.parseInt(vacuumParams[1]);
            int vacuumDir = Integer.parseInt(vacuumParams[2]);
            startState = new State(vacuumRow, vacuumCol, Direction.of(vacuumDir));

            map = new int[maxRow][maxCol];
            for (int i = 0; i < maxRow; i++) {
                String[] mapParams = br.readLine().split(" ");
                for (int j = 0; j < maxCol; j++) {
                    //해당 위치가 벽인 경우 2로 변경하여 저장
                    map[i][j] = Integer.parseInt(mapParams[j].equals("1") ? "2" : mapParams[j]);
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private boolean isWall(int row, int col) {
        if (map[row][col] == 2) return true;
        return false;
    }

    private int cleaning() {
        int cleanCount = 0;
        Stack<State> stack = new Stack<>();
        stack.push(startState);

        while (!stack.empty()) {
            State cur = stack.pop();
            Direction curDir = cur.direction;

            //현재 위치를 청소
            if (map[cur.row][cur.col] == 0) {
                map[cur.row][cur.col] = 1;
                cleanCount++;
            }

            boolean findNext = false;
            for (int i = 0; i < 4; i++) {
                curDir = curDir.leftDirection();
                int leftRow = cur.row + curDir.diff[0];
                int leftCol = cur.col + curDir.diff[1];
                if (map[leftRow][leftCol] == 0) {
                    stack.push(new State(leftRow, leftCol, curDir));
                    findNext = true;
                    break;
                }
            }

            if (!findNext) {
                //4방향 모두 벽이거나 청소를 한경우 후진
                Direction reverseDir = cur.direction.reverseDirection();
                int reverseRow = cur.row + reverseDir.diff[0];
                int reverseCol = cur.col + reverseDir.diff[1];

                if (isWall(reverseRow, reverseCol)) {
                    break;
                }
                stack.push(new State(reverseRow, reverseCol, cur.direction)); //방향은 유지
            }
        }

        return cleanCount;
    }

    private enum Direction {
        NORTH(0, new int[]{-1, 0}),
        EAST(1, new int[]{0, 1}),
        SOUTH(2, new int[]{1, 0}),
        WEST(3, new int[]{0, -1});

        private int value;
        private int[] diff;

        Direction(int value, int[] diff) {
            this.value = value;
            this.diff = diff;
        }

        public static Direction of(int value) {
            if (value > Direction.values().length - 1) {
                return null;
            }
            return Direction.values()[value];
        }

        public Direction leftDirection() {
            Direction nextDir = null;
            switch (this) {
                case NORTH:
                    nextDir = Direction.WEST;
                    break;
                case WEST:
                    nextDir = Direction.SOUTH;
                    break;
                case SOUTH:
                    nextDir = Direction.EAST;
                    break;
                case EAST:
                    nextDir = Direction.NORTH;
            }
            return nextDir;
        }

        public Direction reverseDirection() {
            Direction nextDir = null;
            switch (this) {
                case NORTH:
                    nextDir = Direction.SOUTH;
                    break;
                case WEST:
                    nextDir = Direction.EAST;
                    break;
                case SOUTH:
                    nextDir = Direction.NORTH;
                    break;
                case EAST:
                    nextDir = Direction.WEST;
            }
            return nextDir;
        }
    }

    class State {
        private int row;
        private int col;
        private Direction direction;

        public State(int row, int col, Direction direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }
    }
}
