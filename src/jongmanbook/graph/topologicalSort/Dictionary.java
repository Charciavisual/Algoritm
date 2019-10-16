package jongmanbook.graph.topologicalSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Dictionary {

    private int[][] adj;
    private ArrayList<Integer> order;
    private boolean[] visited;
    private int numOfData;

    public void init() {
        adj = new int[26][26];
        order = new ArrayList<>();
        visited = new boolean[26];
        numOfData = 26;
    }

    public boolean add(String s1, String s2) {

        int len = Math.min(s1.length(), s2.length());

        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                int a = s1.charAt(i) - 'a';
                int b = s2.charAt(i) - 'a';
                adj[a][b] = 1;
                break;
            }
        }

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
        for (int i = 0; i < numOfData; i++) {
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
