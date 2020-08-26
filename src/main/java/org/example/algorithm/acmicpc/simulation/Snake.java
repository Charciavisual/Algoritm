package org.example.algorithm.acmicpc.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 뱀
 * https://www.acmicpc.net/problem/3190
 *
 * @author Changhee Choi
 * @since 26/08/2020
 */
public class Snake {
    private final int APPLE = 1;
    private final int WALL = 2;
    private int n;
    private ArrayList<Pos> apples;
    private LinkedList<Instruction> instructions;

    public void solution() {
        init();
        int answer = playDummy();
        System.out.println(answer);
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            apples = new ArrayList<>();
            instructions = new LinkedList<>();

            n = Integer.parseInt(br.readLine());

            int numOfApples = Integer.parseInt(br.readLine());
            for (int i = 0; i < numOfApples; i++) {
                String[] applesPosTokens = br.readLine().split(" ");
                apples.add(new Pos(Integer.parseInt(applesPosTokens[0]), Integer.parseInt(applesPosTokens[1])));
            }

            int directionChangeCount = Integer.parseInt(br.readLine());
            for (int i = 0; i < directionChangeCount; i++) {
                String[] dirChangeTokens = br.readLine().split(" ");
                instructions.add(new Instruction(Integer.parseInt(dirChangeTokens[0]), dirChangeTokens[1]));
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private int playDummy() {
        int[][] map = initMap(n, apples);
        Queue<Pos> queue = new LinkedList<>();
        Direction dir = Direction.RIGHT;

        int time = 0;
        Pos curHead = new Pos(1, 1);
        queue.add(curHead);
        map[curHead.row][curHead.col] = WALL;

        Instruction instruction = instructions.poll();

        while (true) {
            curHead = new Pos(curHead.row + dir.diff[0], curHead.col + dir.diff[1]);
            time++;

            if (map[curHead.row][curHead.col] == WALL) {
                break;
            }

            if(map[curHead.row][curHead.col] != APPLE) {
                Pos removedTail = queue.poll();
                map[removedTail.row][removedTail.col] = 0;
            }

            queue.add(curHead);
            map[curHead.row][curHead.col] = WALL;

            if (instruction != null && time == instruction.seconds) {
                dir = changeDirection(dir, instruction.direction);
                instruction = instructions.poll();
            }
        }

        return time;
    }

    private int[][] initMap(int n, ArrayList<Pos> apples) {
        int[][] map = new int[n + 2][n + 2];

        Arrays.fill(map[0], WALL);
        Arrays.fill(map[n + 1], WALL);

        for (int i = 1; i < n + 2; i++) {
            map[i][0] = WALL;
            map[i][n + 1] = WALL;
        }

        for (Pos apple : apples) {
            map[apple.row][apple.col] = APPLE;
        }

        return map;
    }

    private Direction changeDirection(Direction dir, String nextDir) {
        if (nextDir.equals("D")) {
            return Direction.valueOf((dir.value + 1) % 4);
        }
        return Direction.valueOf((dir.value == 0) ? 3 : dir.value - 1);
    }

    private class Pos {
        private int row;
        private int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private class Instruction {
        private int seconds;
        private String direction;

        public Instruction(int seconds, String direction) {
            this.seconds = seconds;
            this.direction = direction;
        }
    }

    private enum Direction {
        UP(0, new int[]{-1, 0}),
        RIGHT(1, new int[]{0, 1}),
        DOWN(2, new int[]{1, 0}),
        LEFT(3, new int[]{0, -1});

        private int value;
        private int[] diff;

        Direction(int value, int[] diff) {
            this.value = value;
            this.diff = diff;
        }

        public static Direction valueOf(int value) {
            return Direction.values()[value];
        }
    }
}