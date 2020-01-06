package org.example.algorithm.template.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class TopologicalSort {

    private int[][] adj;
    private ArrayList<Integer> order;
    private boolean[] visited;
    private int numOfData;

    public TopologicalSort(int n) {
        adj = new int[n][n];
        order = new ArrayList<>();
        visited = new boolean[n];
        numOfData = n;
    }

    public boolean add(int vFrom, int vTo) {
        adj[vFrom][vTo] = 1;
        return true;
    }

    private void iterativeDfs(int curPos) {
        Stack<Integer> stack = new Stack<>();
        boolean flag = false;

        visited[curPos] = true;
        stack.push(curPos);

        while (!stack.isEmpty()) {
            curPos = stack.peek();
            flag = false;
            for (int next = 0; next < numOfData; next++) {
                if (adj[curPos][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                order.add(stack.pop());
            }
        }
    }

    public ArrayList<Integer> topologicalSort() {
        Arrays.fill(visited, false);
        order.clear();
        for (int i = 0; i < numOfData ; i++) {
            if (!visited[i])
                iterativeDfs(i);
        }

        Collections.reverse(order);

        for (int i = 0; i < order.size(); i++)
            for (int j = i + 1; j < order.size(); j++) {
                if (adj[order.get(j)][order.get(i)] == 1) return new ArrayList<>();
            }
        return order;
    }
}
