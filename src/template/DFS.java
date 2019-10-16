package template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
    private ArrayList<Integer>[] adj;
    private boolean[] visited;

    public DFS(int n) {
        adj = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public boolean add(int vFrom, int vTo) {
        adj[vFrom].add(vTo);
        return true;
    }

    private void recursiveDfs(int curPos) {
        System.out.println(curPos);
        visited[curPos] = true;

        for (int i = 0; i < adj[curPos].size(); i++) {
            int next = adj[curPos].get(i);
            if (!visited[next])
                recursiveDfs(next);
        }
    }

    private void iterativeDfs(int curPos) {
        Stack<Integer> stack = new Stack<>();
        boolean flag = false;

        System.out.println(curPos);
        visited[curPos] = true;
        stack.push(curPos);

        while (!stack.isEmpty()){
            curPos = stack.peek();
            flag = false;
            for (int i = 0; i < adj[curPos].size(); i++) {
                int next = adj[curPos].get(i);
                if (!visited[next]) {
                    System.out.println(next);
                    visited[next] = true;
                    stack.push(next);
                    flag = true;
                    break;
                }
            }

            if(!flag)
                stack.pop();
        }
    }

    public void recursiveDfs() {
        Arrays.fill(visited, false);
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i])
                recursiveDfs(i);
        }
    }

    public void iterDfs() {
        Arrays.fill(visited, false);
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i])
                iterativeDfs(i);
        }
    }
}
