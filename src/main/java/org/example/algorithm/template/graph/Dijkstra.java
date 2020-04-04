package org.example.algorithm.template.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

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
        //양방향인 경우 아래 주석 제거
        //adj[to].add(new Edge(from, dist));
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


