package org.example.algorithm.acmicpc.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 11725 트리의 부모 찾기
 * https://www.acmicpc.net/problem/11725
 *
 * @author Changhee Choi
 * @since 28/09/2020
 */
public class FindParentNode {
    class Node {
        private int number;
        private ArrayList<Node> children;

        public Node(int number) {
            this.number = number;
            this.children = new ArrayList<>();
        }

        public void addChildNode(Node node) {
            this.children.add(node);
        }
    }

    private final int ROOT = 1;
    private int n;
    private Node[] nodes;

    public void solution() throws IOException {
        initTree();
        int[] parent = findParent();
        printAnswer(parent);
    }

    private void initTree() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];
        nodes[ROOT] = new Node(1);
        for (int i = 0; i < n - 1; i++) {
            setChildNode(br.readLine());
        }
        br.close();
    }

    private void setChildNode(String edgeInfo) {
        int[] edge = Arrays.stream(edgeInfo.split(" ")).mapToInt(Integer::parseInt).toArray();
        int nodeNum1 = edge[0];
        int nodeNum2 = edge[1];
        Node node1 = getNode(nodeNum1);
        Node node2 = getNode(nodeNum2);
        node1.addChildNode(node2);
        node2.addChildNode(node1);
    }

    private Node getNode(int number) {
        if (nodes[number] == null) {
            nodes[number] = new Node(number);
        }
        return nodes[number];
    }

    private int[] findParent() {
        Stack<Node> stack = new Stack<>();
        boolean[] visited = new boolean[n + 1];
        int[] parent = new int[n + 1];

        Node root = nodes[ROOT];
        stack.push(root);

        while (!stack.empty()) {
            Node curNode = stack.pop();
            visited[curNode.number] = true;
            for (Node child : curNode.children) {
                if (!visited[child.number]) {
                    parent[child.number] = curNode.number;
                    stack.push(child);
                }
            }
        }

        return parent;
    }

    private void printAnswer(int[] parent) {
        StringBuilder answerBuilder = new StringBuilder();
        for (int i = 2; i < parent.length; i++) {
            answerBuilder.append(parent[i]).append('\n');
        }
        System.out.println(answerBuilder.toString());
    }
}
