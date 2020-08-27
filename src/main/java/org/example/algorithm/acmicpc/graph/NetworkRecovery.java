package org.example.algorithm.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 네트워크 복구
 * https://www.acmicpc.net/problem/2211
 *
 * @author Changhee Choi
 * @since 27/08/2020
 */
public class NetworkRecovery {

    private final int INF = 10001;
    private int n, m;
    private ArrayList<Edge>[] edges;
    private int[] dist;
    private int[] path;

    public void solution() {
        init();
        recovery(1);
        printResult();
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] sizeTokens = br.readLine().split(" ");
            n = Integer.parseInt(sizeTokens[0]);
            m = Integer.parseInt(sizeTokens[1]);

            edges = new ArrayList[n + 1];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<>(n + 1);
            }

            for (int i = 0; i < m; i++) {
                int[] edgeTokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                edges[edgeTokens[0]].add(new Edge(edgeTokens[1], edgeTokens[2]));
                edges[edgeTokens[1]].add(new Edge(edgeTokens[0], edgeTokens[2]));
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private void initDijkstra() {
        path = new int[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
    }

    private void recovery(int start) {
        initDijkstra();

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int vertex = edge.vertex;

            for (int i = 0; i < edges[vertex].size(); i++) {
                Edge dest = edges[vertex].get(i);
                if (dist[vertex] + dest.weight < dist[dest.vertex]) {
                    dist[dest.vertex] = dist[vertex] + dest.weight;
                    path[dest.vertex] = vertex;
                    pq.add(dest);
                }
            }
        }
    }

    private void printResult() {
        int count = 0;
        ArrayList<String> pathStrings = new ArrayList<>();

        for (int vertex = 1; vertex < path.length; vertex++) {
            if (path[vertex] > 0) {
                count++;
                pathStrings.add(vertex + " " + path[vertex]);
            }
        }

        System.out.println(count);
        pathStrings.forEach(path -> System.out.println(path));
    }

    private class Edge {
        private int vertex;
        private int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
