package org.example.algorithm.acmicpc.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 최단경로 - 최단경로
 * https://www.acmicpc.net/problem/1753
 *
 * @author Changhee Choi
 * @since 20/07/2020
 */
public class ShortestPath {

    private final int INF = Integer.MAX_VALUE;
    private ArrayList<Vertex>[] vertexes;
    private int numOfVertex;
    private int startVertex;

    public void solution() {
        init();
        int[] distances = calcShortestDistanceByDijkstra();
        for (int i = 1; i < distances.length; i++) {
            System.out.println(distances[i] == INF ? "INF" : distances[i]);
        }
    }

    private void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int numOfEdges = 0;
            String[] numParams = br.readLine().split(" ");
            numOfVertex = Integer.parseInt(numParams[0]);
            numOfEdges = Integer.parseInt(numParams[1]);

            startVertex = Integer.parseInt(br.readLine());

            vertexes = new ArrayList[numOfVertex + 1];
            for (int i = 0; i < vertexes.length; i++) {
                vertexes[i] = new ArrayList<>();
            }

            //vertexes 리스트를 LinkedList로 하면 insert 과정에서 실행시간이 오래걸려 시간초과 발생함.
            //리스트의 입력은 추가 과정마다 메모리의 할당이 일어나고 삭제에서는 메모리의 해제가 일어나기 때문인것 같다.
            for (int i = 0; i < numOfEdges; i++) {
                String[] pathParams = br.readLine().split(" ");
                int from = Integer.parseInt(pathParams[0]);
                int to = Integer.parseInt(pathParams[1]);
                int weight = Integer.parseInt(pathParams[2]);
                vertexes[from].add(new Vertex(to, weight));
            }

            br.close();
        }catch(IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }
    }

    private int[] calcShortestDistanceByDijkstra() {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        int[] distances = new int[numOfVertex + 1];

        Arrays.fill(distances, INF);
        distances[startVertex] = 0;

        pq.add(new Vertex(startVertex, 0));

        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();

            if (cur.distance > distances[cur.number]) continue;

            for (Vertex next : vertexes[cur.number]) {
                int distanceToNext = cur.distance + next.distance;
                if (distanceToNext < distances[next.number]) {
                    distances[next.number] = distanceToNext;
                    pq.add(new Vertex(next.number, distanceToNext));
                }
            }
        }

        return distances;
    }

    class Vertex implements Comparable<Vertex> {
        private int number;
        private int distance;

        public Vertex(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.distance - o.distance;
        }
    }
}
