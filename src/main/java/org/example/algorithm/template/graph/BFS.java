package org.example.algorithm.template.graph;

import java.util.*;

public class BFS {
    private ArrayList<Integer>[] adj;
    private boolean[] visited;
    private int[] parent;

    public void init(int n, int[][] data) {
        adj = new ArrayList[n];
        visited = new boolean[n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<data.length; i++){
            add(data[i][0], data[i][1]);
        }
    }

    public boolean add(int vFrom, int vTo) {
        adj[vFrom].add(vTo);
        return true;
    }

    private void search(int curPos) {
        Queue<Integer> queue = new LinkedList<>();

        visited[curPos] = true;
        queue.add(curPos);
        parent[curPos] = curPos;

        while (!queue.isEmpty()) {

            curPos = queue.poll();

            for (int i = 0; i < adj[curPos].size(); i++) {
                int nextPos = adj[curPos].get(i);
                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.add(nextPos);
                    parent[nextPos] = curPos;
                }
            }
        }
    }

    public void search() {
        Arrays.fill(visited, false);
        Arrays.fill(parent, 0);
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) search(i);
        }
    }

    public LinkedList<Integer> shortestPath(int pos) {
        LinkedList<Integer> path = new LinkedList<>();
        path.add(pos);

        while (parent[pos] != pos) {
            pos = parent[pos];
            path.add(pos);
        }
        Collections.reverse(path);
        return path;
    }

    public void testBFS(){
        int n = 6;
        int[][] data = {{0, 1}, {0, 2}, {1, 3}, {2, 4}, {3, 5}};

        init(n, data);
        search();
        shortestPath(5).forEach(v -> System.out.printf("%s ", v));
        System.out.println();
    }
}
