package org.example.algorithm.template.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
    private ArrayList<Integer>[] adj;
    private boolean[] visited;

    public void init(int n, int[][] data) {
        adj = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < data.length; i++) {
            add(data[i][0], data[i][1]);
        }
    }

    public boolean add(int vFrom, int vTo) {
        adj[vFrom].add(vTo);
        return true;
    }

    private void recursiveSearch(int curPos) {
        System.out.printf("%s ", curPos);
        visited[curPos] = true;

        for (int i = 0; i < adj[curPos].size(); i++) {
            int next = adj[curPos].get(i);
            if (!visited[next])
                recursiveSearch(next);
        }
    }

    public void recursiveSearch() {
        Arrays.fill(visited, false);
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i])
                recursiveSearch(i);
        }
    }

    private void iterativeSearch(int curPos) {
        Stack<Integer> stack = new Stack<>();
        boolean flag = false;

        System.out.printf("%s ", curPos);
        visited[curPos] = true;
        stack.push(curPos);

        while (!stack.isEmpty()) {
            curPos = stack.peek();
            flag = false;
            for (int i = 0; i < adj[curPos].size(); i++) {
                int next = adj[curPos].get(i);
                if (!visited[next]) {
                    System.out.printf("%s ", next);
                    visited[next] = true;
                    stack.push(next);
                    flag = true;
                    break;
                }
            }

            if (!flag)
                stack.pop();
        }
    }

    public void iterativeSearch() {
        Arrays.fill(visited, false);
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i])
                iterativeSearch(i);
        }
    }

    public void testDFS(){
        int n = 6;
        int[][] data = {{0, 1}, {0, 2}, {1, 3}, {2, 4}, {3, 5}};

        init(n, data);

        System.out.println("recursive search: ");
        recursiveSearch();
        System.out.println();

        System.out.println("iterative search: ");
        iterativeSearch();
        System.out.println();
    }
}
