package template.graph;

import java.util.ArrayList;
import java.util.Collections;

public class BellmanFord {
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

    public BellmanFord(int n) {
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

    public ArrayList<Integer> bellmanFord(int startPos) {

        ArrayList<Integer> upper = new ArrayList<>(Collections.nCopies(MAX_V, Integer.MAX_VALUE));
        //Collections.fill(upper, Integer.MAX_VALUE); <- 동작하지 않음...
        upper.set(0,0);

        boolean update = false;

        // v번 진행
        for(int iter=0; iter<MAX_V; iter++) {
            update = false;
            for(int cur = 0; cur < MAX_V; cur++) {
                for(int i=0; i<adj[cur].size(); i++){
                    Edge next = adj[cur].get(i);
                    //dist[v] <= dist[u] + w(u,v)
                    if(upper.get(next.getVertex()) > upper.get(cur) + next.getDist()) {
                        upper.set(next.getVertex(), upper.get(cur) + next.getDist());
                        update = true;
                    }
                }
            }
            //완화된 간선이 없으면 종료
            if(!update) break;
        }

        //V번째 순회에서 update가 true면 음수 사이클이 존재
        if(update) upper.clear();
        return upper;
    }

    public void testBellmanFord(int n, int[][] edges){

//       int n = 5;
//       int[][] edges = {{0,2,4}, {2,3,-7}, {3,2,5}, {0,1,4}, {4,1,-4}, {4,4,-1}};

        for(int i=0; i<edges.length; i++) {
            add(edges[i][0],edges[i][1],edges[i][2]);
        }

        ArrayList<Integer> result = bellmanFord(0);
        if(result.size() == 0) System.out.println("음수 사이클 존재!");
        else result.forEach(dist -> System.out.printf("%d ", dist));

    }
}
