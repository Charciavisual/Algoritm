package org.example.algorithm.acmicpc.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 치킨배달
 * https://www.acmicpc.net/problem/15686
 *
 * @author Changhee Choi
 * @since 25/07/2020
 */
public class ChickenDelivery {
    private final int INF = 100 * 98 + 1; //집의 최대 갯수 = 2 * 50, 각 집마다 최대 치킨 거리는 (50-1) + (50-1) = 98

    private int n, m;
    private List<Locate> houses = new ArrayList<>();
    private List<Locate> restaurants = new ArrayList<>();
    private boolean[] selected;

    public void solution() {
        init();
        int answer = calcChickenDistanceOfCity(0, 0);
        System.out.println(answer);
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int[] sizeParams = toIntArray(br.readLine());

            n = sizeParams[0];
            m = sizeParams[1];

            for (int i = 0; i < n; i++) {
                int[] mapParams = toIntArray(br.readLine());
                for (int j = 0; j < n; j++) {
                    if (mapParams[j] == 1) {
                        houses.add(new Locate(i + 1, j + 1));
                    } else if (mapParams[j] == 2) {
                        restaurants.add(new Locate(i + 1, j + 1));
                    }
                }
            }

            selected = new boolean[restaurants.size()];

            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private int[] toIntArray(String input) {
        return Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private int calcChickenDistanceOfCity(int index, int selectedCount) {
        if (selectedCount == m) {
            return calcChickenDistanceSum();
        }

        if (index > restaurants.size() - 1) {
            return INF;
        }

        int ret = INF;

        selected[index] = true;
        ret = Math.min(ret, calcChickenDistanceOfCity(index + 1, selectedCount + 1));

        selected[index] = false;
        ret = Math.min(ret, calcChickenDistanceOfCity(index + 1, selectedCount));

        return ret;
    }

    private int calcChickenDistanceSum() {
        int distanceSum = 0;

        for (Locate house : houses) {
            int distanceOfHouse = INF;
            for (int i = 0; i < restaurants.size(); i++) {
                if (selected[i]) {
                    distanceOfHouse = Math.min(distanceOfHouse, house.getDistance(restaurants.get(i)));
                }
            }
            distanceSum += distanceOfHouse;
        }

        return distanceSum;
    }

    class Locate {
        private int row;
        private int col;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getDistance(Locate o) {
            return Math.abs(this.row - o.row) + Math.abs(this.col - o.col);
        }
    }
}
