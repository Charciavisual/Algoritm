package org.example.algorithm.programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 프로그래머스 - 단속카메라
 * https://programmers.co.kr/learn/courses/30/lessons/42884
 *
 * @author Changhee Choi
 * @since 06/04/2020
 */
public class SpeedCamera {

    /**
     * @param routes 차량의 이동경로 [진입지점, 나간지점], -30000 <= 진입지점, 나간지점 <= 30000
     * @return 모든 차량이 한 번은 단속 카메라를 만나도록 할때 설치해야 할 최소 카메라의 개수
     */
    public int solution(int[][] routes) {

        int answer = 0;

        PriorityQueue<Car> outPointOrderQueue = new PriorityQueue<>(Car.OUT_POINT_ORDER);
        int lastCameraPoint = -30001;

        //나가는 지점을 기준으로 하는 우선순위 큐를 생성
        for (int i = 0; i < routes.length; i++) {
            Car car = new Car(routes[i][0], routes[i][1]);
            outPointOrderQueue.add(car);
        }

        //차가 나가는 지점이 마지막 카메라 위치보다 이후라면 카메라를 설치해야한다
        while (!outPointOrderQueue.isEmpty()) {
            Car curOutCar = outPointOrderQueue.poll();

            if (curOutCar.getInPoint() > lastCameraPoint) {
                answer++;
                //마지막 카메라 위치 갱신
                lastCameraPoint = curOutCar.getOutPoint();
            }
        }

        return answer;
    }
}

class Car {
    private int inPoint;
    private int outPoint;

    public Car(int inPoint, int outPoint) {
        this.inPoint = inPoint;
        this.outPoint = outPoint;
    }

    public int getInPoint() {
        return inPoint;
    }

    public int getOutPoint() {
        return outPoint;
    }

    public static Comparator<Car> OUT_POINT_ORDER = Comparator.comparingInt(o -> o.outPoint);
}
