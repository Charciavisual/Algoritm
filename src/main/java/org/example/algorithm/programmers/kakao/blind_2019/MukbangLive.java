package org.example.algorithm.programmers.kakao.blind_2019;

import java.util.Comparator;
import java.util.ArrayList;

/**
 * 무지의 먹방 라이브
 * https://programmers.co.kr/learn/courses/30/lessons/42891#
 *
 * @author Changhee Choi
 * @since 08/09/2020
 */
public class MukbangLive {
    class Food {
        int number;
        int time;

        public Food(int number, int time) {
            this.number = number;
            this.time = time;
        }
    }

    Comparator<Food> compareTime = Comparator.comparingInt(o -> o.time);
    Comparator<Food> compareNumber = Comparator.comparingInt(o -> o.number);

    public int solution(int[] food_times, long k) {
        int n = food_times.length;

        ArrayList<Food> foods = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            foods.add(new Food(i + 1, food_times[i]));
        }

        foods.sort(compareTime);

        int prevFoodTime = 0;

        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            long diff = food.time - prevFoodTime;

            if (diff != 0) {
                long spend = diff * n;
                if (spend <= k) {
                    k -= spend;
                    prevFoodTime = food.time;
                } else {
                    k %= n;
                    foods.subList(i, food_times.length).sort(compareNumber);
                    return foods.get(i + (int) k).number;
                }
            }
            n--;
        }
        return -1;
    }
}
