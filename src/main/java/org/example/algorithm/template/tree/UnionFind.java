package org.example.algorithm.template.tree;

import java.util.Arrays;

public class UnionFind {

    private int[] parent, rank;
    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];

        Arrays.fill(rank, 1);

        for(int i=0; i<n; i++)
            parent[i] = i;
    }

    private int find(int node) {
        // 최상위 루트이면 리턴
        if(node == parent[node]) return node;
        // 재귀적으로 최상위 루트를 찾고 복귀할때 부모노드를 최상위 루트 노드로 변경
        return parent[node] = find(parent[node]);
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public void union(int node1, int node2) {
        int[] roots = new int[2];
        roots[0] = find(node1);
        roots[1] = find(node2);

        if (roots[0] == roots[1]) return;

        //높이가 더 높은 쪽으로 합친다.
        if (rank[roots[0]] > rank[roots[1]]) swap(roots, 0, 1);

        parent[roots[0]] = roots[1];

        //높이가 같은 경우 높이를 1 증가시켜준다.
        if (rank[roots[0]] == rank[roots[1]])
            rank[roots[1]]++;
    }

    //두 노드의 루트가 같은지 검사 (연결된 노드인지)
    public boolean isConnected(int node1, int node2) {
        int pnode1 = find(node1);
        int pnode2 = find(node2);

        if(pnode1 == pnode2) return true;
        return false;
    }
}
