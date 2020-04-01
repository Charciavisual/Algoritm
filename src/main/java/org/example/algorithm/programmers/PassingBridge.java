package org.example.algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 - 다리를 지나는 트럭
 *
 * @author Changhee Choi
 * @since 01/04/2020
 */
public class PassingBridge {
    private class Truck {
        int weight;
        int expectedArriveAt;

        public Truck(int weight, int expectedArriveAt) {
            this.weight = weight;
            this.expectedArriveAt = expectedArriveAt;
        }
    }

    /**
     * @param bridge_length 다리 길이 (1 <= .. <= 10000)
     * @param weight        다리가 견딜수 있는 무게 (1 <= .. <= 10000)
     * @param truck_weights 트럭의 무게 (1 <= .. <= weight), 총 트럭 수 (1 <= .. <= 10000)
     * @return 모든 트럭이 다리를 건널때 걸리는 최소 시간
     * <p>
     * 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않는다.
     */
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0, totalWeight = 0, timer = 0;
        Queue<Truck> bridge = new LinkedList<>();

        for (int weightOfTruck : truck_weights) {
            //다리에 트럭이 더 올라갈 수 있으면 1초 증가
            if (weightOfTruck + totalWeight <= weight) {
                timer++;

                //새로운 트럭 추가
                bridge.add(new Truck(weightOfTruck, timer + bridge_length));
                totalWeight += weightOfTruck;

                //지나간 트럭을 제거
                while (bridge.peek().expectedArriveAt == timer) {
                    Truck truck = bridge.poll();
                    totalWeight -= truck.weight;
                }

            }
            //다리에 트럭이 더 올라갈 수 없으면 현재 트럭이 올라갈 수 있을때까지 트럭을 지나보낸다
            else {
                while (weightOfTruck + totalWeight > weight) {
                    Truck truck = bridge.poll();
                    int remainTimeOfTruck = truck.expectedArriveAt - timer;
                    timer += remainTimeOfTruck;
                    totalWeight -= truck.weight;
                }
                totalWeight += weightOfTruck;
                bridge.add(new Truck(weightOfTruck, timer + bridge_length));
            }
        }

        //다리에 올라간 모든 트럭이 지나가는 시간을 더한다
        while (!bridge.isEmpty()) {
            Truck truck = bridge.poll();
            int remainTimeOfTruck = truck.expectedArriveAt - timer;
            timer += remainTimeOfTruck;
        }

        answer = timer;
        return answer;
    }
}
