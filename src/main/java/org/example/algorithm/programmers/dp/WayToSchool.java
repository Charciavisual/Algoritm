package org.example.algorithm.programmers.dp;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42898
 *
 * @author Changhee Choi
 * @since 20/10/2020
 */
public class WayToSchool {
    private final int DIV = 1000000007;

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        final int maxRow = n + 1, maxCol = m + 1;
        final int[][] map = createMap(maxRow, maxCol, puddles);

        for (int i = 1; i < maxRow; i++) {
            for (int j = 1; j < maxCol; j++) {
                if (map[i][j] == -1) {
                    continue;
                }
                final int fromLeft = map[i][j - 1] == -1 ? 0 : map[i][j - 1] % DIV;
                final int fromAbove = map[i - 1][j] == -1 ? 0 : map[i - 1][j] % DIV;
                map[i][j] += (fromLeft + fromAbove) % DIV;
            }
        }

        answer = map[n][m] % DIV;
        return answer;
    }

    private int[][] createMap(int maxRow, int maxCol, int[][] puddles) {
        final int[][] map = new int[maxRow][maxCol];

        //map의 왼쪽, 위쪽 테투리
        for (int i = 0; i < maxCol; i++) {
            map[0][i] = -1;
        }
        for (int i = 0; i < maxRow; i++) {
            map[i][0] = -1;
        }

        //웅덩이
        for (int[] puddle : puddles) {
            map[puddle[1]][puddle[0]] = -1;
        }

        //집 위치(시작위치)
        map[1][1] = 1;

        return map;
    }
}
