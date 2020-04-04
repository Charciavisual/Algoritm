package org.example.algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 프로그래머스 - 배달
 * https://programmers.co.kr/learn/courses/30/lessons/12978
 *
 * @author Changhee Choi
 * @since 04/04/2020
 */
public class Delivery {

    /**
     * 1번 마을에 있는 음식점에서 각 마을로 음식 배달을 하려고 할때 K시간 이하로 배달이 가능한 마을의 개수 구하기
     * <p>
     * 임의의 두 마을간에는 항상 경로가 존재한다.
     * 두 마을 a, b를 연결하는 도로는 여러 개가 있을 수 있다.
     *
     * @param N    마을의 개수 1<= N <= 50
     * @param road 마을을 연결하는 도로의 정보, 1<= 정보의 개수 <= 2000 , 각 배열당 [마을1, 마을2, 1<= 걸리는시간 <= 10000] 의 값을 가진다
     * @param K    배달이 가능한 시간 1<= K <= 500000
     * @return 음식 주문을 받을 수 있는 마을의 개수
     */
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        //1번마을에서 각 마을로의 최단거리를 구한다 (다익스트라)
        Dijkstra dijkstra = new Dijkstra(N + 1);

        for (int i = 0; i < road.length; i++) {
            dijkstra.add(road[i][0], road[i][1], road[i][2]);
        }

        int[] distanceFromStart = dijkstra.calcDistanceFromStart(1);

        for (int j = 1; j < distanceFromStart.length; j++) {
            if (distanceFromStart[j] <= K) answer++;
        }
        return answer;
    }

}

class Dijkstra {

    class Edge implements Comparable<Edge> {
        int vertex;
        int dist;

        public Edge(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        public int getVertex() {
            return vertex;
        }

        public int getDist() {
            return dist;
        }

        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
    }

    private int MAX_V;
    private ArrayList<Edge>[] adj;

    public Dijkstra(int n) {
        MAX_V = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void add(int from, int to, int dist) {
        adj[from].add(new Edge(to, dist));
        adj[to].add(new Edge(from, dist));
    }

    public int[] calcDistanceFromStart(int startPos) {

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        int[] dist = new int[MAX_V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startPos] = 0;

        pq.add(new Edge(startPos, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            //현재 경로가 더 긴 경로라면 무시
            if (cur.getDist() > dist[cur.getVertex()]) continue;

            for (int i = 0; i < adj[cur.getVertex()].size(); i++) {
                Edge next = adj[cur.getVertex()].get(i);
                int nextDist = dist[cur.getVertex()] + next.getDist();
                //더 짧은 경로이면 저장
                if (nextDist < dist[next.getVertex()]) {
                    dist[next.getVertex()] = nextDist;
                    pq.add(next);
                }
            }
        }

        return dist;
    }
}

