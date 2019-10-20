package template.graph;

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

    public boolean add(int from, int to, int dist) {
        adj[from].add(new Edge(to, dist));
        return true;
    }

    public int[] dijkstra(int startPos) {

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

    public void testDijkstra(int n, int[][] edges){

//        int n = 7;
//        int[][] edges = {{0,1,1},{0,2,2},{0,6,2},{2,3,1},{3,6,1},{1,4,4},{4,5,1},{3,4,1}};

        for(int i=0; i<edges.length; i++) {
            add(edges[i][0],edges[i][1],edges[i][2]);
        }

        int[] result = dijkstra(0);

        for(int i=0; i<n; i++)
            System.out.printf("%d ", result[i]);
    }
}


