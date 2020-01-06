package org.example.algorithm.template.graph;

import java.util.Arrays;

public class Floyd {

    private final int INF = 987654321;
    private int MAX_V;
    private int[][] adj;

    public Floyd(int n) {
        MAX_V = n;
        adj = new int[n][n];
        for (int i = 0; i < MAX_V; i++) {
            Arrays.fill(adj[i], INF);
        }
    }

    public boolean add(int from, int to, int dist) {
        adj[from][to] = dist;
        return true;
    }

    public void floyd() {
        for (int i = 0; i < MAX_V; i++) adj[i][i] = 0;

        for (int k = 0; k < MAX_V; k++)
            for (int i = 0; i < MAX_V; i++)
                for (int j = 0; j < MAX_V; j++)
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
    }

    public void testFloyd(int n, int[][] edges){

//       int n = 5;
//       int[][] edges = {{0,2,4}, {2,3,-7}, {3,2,5}, {0,1,4}, {4,1,-4}, {4,4,-1}};

        for(int i=0; i<edges.length; i++) {
            add(edges[i][0],edges[i][1],edges[i][2]);
        }

        floyd();

        for(int i=0; i<MAX_V; i++) {
            for(int j=0; j<MAX_V; j++) {
                System.out.printf("%d -> %d : %d\n", i, j, adj[i][j]);
            }
        }
    }
}
