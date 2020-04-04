package org.example.algorithm.programmers;

/**
 * 프로그래머스 - 기지국 설치
 * https://programmers.co.kr/learn/courses/30/lessons/12979
 *
 * @author Changhee Choi
 * @since 04/04/2020
 */
public class BaseStationInstallation {

    /**
     * 모든 아파트에 전파를 전달하기 위해 증설해야할 기지국 개수의 최솟값 구하기
     *
     * @param n        1<= 아파트개수 <= 200000000
     * @param stations 현재 기지국이 설치된 아파트 번호가 담긴 배열, 1<= 배열의 길이 <= 10000 (오름차순 정렬)
     * @param w        1<= 전파 도달거리 <= 10000
     * @return 추가로 증설해야할 기지국 개수의 최솟값
     */
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        //최적으로 설치했을때 전파의 길이
        int OptimumWaveLength = 2 * w + 1;
        int current = 1;
        int idx = 0;

        while (current <= n) {
            //현재 위치가 기지국이 설치되어 전파가 닿는 곳이라면 해당 기지국에서 마지막으로 전파가 닿는 아파트 다음으로 현재 위치를 옮긴다.
            if (idx < stations.length && current >= stations[idx] - w) {
                current = stations[idx] + w + 1;
                idx++;
                continue;
            }

            //현재 위치에 전파가 닿지 않는다면 최적의 위치에 기지국을 설치했을때 마지막으로 전파가 닿는 아파트 다음으로 현재 위치를 옮긴다.
            current = current + OptimumWaveLength;
            answer++;
        }

        return answer;
    }
}
