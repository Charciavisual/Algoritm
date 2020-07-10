package org.example.algorithm.programmers.kakao.blind_2020;

import java.util.HashSet;

public class SecretDoor {

    private int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private HashSet<String> visited = new HashSet<>();

    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;

        //키에 돌기가 없거나 자물쇠에 구멍이 없으면 false 리턴
        if (!(validateKey(key) || validateLock(lock))) return false;

        answer = openDoor(key, lock);
        return answer;
    }

    private boolean validateKey(int[][] key) {
        int count = 0;
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                if (key[i][j] == 1) count++;
            }
        }

        if (count == 0) return false;
        return true;
    }

    private boolean validateLock(int[][] lock) {
        int count = 0;
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (lock[i][j] == 0) count++;
            }
        }

        if (count == 0) return false;
        return true;
    }

    private int[][] rotateKey(int[][] key) {

        int[][] rotatedKey = new int[key.length][key.length];

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                rotatedKey[j][key.length - i - 1] = key[i][j];
            }
        }

        return rotatedKey;
    }

    private boolean openDoor(int[][] key, int[][] lock) {
        boolean ret = false;

        for (int i = 0; i < 4; i++) {
            ret = ret || openDoor(key, lock, 0, 0);
            if (ret) break;
            key = rotateKey(key);
            visited.clear();
        }

        return ret;
    }

    private boolean openDoor(int[][] key, int[][] lock, int dx, int dy) {

        //키 전체 배열이 자물쇠의 범위를 벗어나는지 검사
        if (!isRangeOfLock(lock.length, dx, dy)) return false;

        boolean ret = true;

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                int mappedKey = mappedAsKey(key, i, j, dx, dy);

                //자물쇠의 구멍에 매핑되는 키가 없으면 문열기 실패
                if(lock[i][j] == 0 && (mappedKey == -1 || mappedKey == 0)) {
                    ret = false;
                    break;
                }
                //키의 돌기와 자물쇠의 돌기가 만나면 문열기 실패
                if (lock[i][j] == 1 && mappedKey == 1) {
                    ret = false;
                    break;
                }
            }

            if(!ret) break;
        }

        //현재 위치에서 키가 맞지 않는 경우 키를 이동시켜 다시 탐색한다
        if (!ret) {
            for (int i = 0; i < 4; i++) {
                int nextDx = dx + d[i][0];
                int nextDy = dy + d[i][1];
                String visitedKey = nextDx + " " + nextDy;
                if (!visited.contains(visitedKey)) {
                    visited.add(visitedKey);
                    ret = ret || openDoor(key, lock, nextDx, nextDy);                }
                if (ret) break;
            }
        }

        return ret;
    }

    private boolean isRangeOfLock(int lockSize, int dx, int dy) {
        if (Math.abs(dx) > lockSize - 1 || Math.abs(dy) > lockSize - 1) return false;
        return true;
    }

    private int mappedAsKey(int[][] key, int x, int y, int dx, int dy) {
        if (x - dx < 0 || x - dx > key.length - 1 || y - dy < 0 || y - dy > key.length - 1)
            return -1;
        return key[x - dx][y - dy];
    }
}
