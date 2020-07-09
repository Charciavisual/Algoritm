package org.example.algorithm.acmicpc.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문자열 - 개미굴
 * https://www.acmicpc.net/problem/14725
 *
 * @author Changhee Choi
 * @since 09/07/2020
 */
public class AntTunnel {

    private int n;
    private Node root;
    private String[] inputs;

    public String solution() {
        String answer = "";
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException("초기화 과정에서 입력오류가 발생했습니다.");
        }

        makeTrie();
        answer = printNodes(root, 0);
        return answer;
    }

    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        inputs = new String[n];

        for (int i = 0; i < n; i++) {
            inputs[i] = br.readLine();
        }
        br.close();
    }

    private void makeTrie() {
        root = new Node("");

        for (String input : inputs) {
            String[] tokens = input.split(" ");

            Node curNode = root;

            for (int i = 1; i <= Integer.parseInt(tokens[0]); i++) {
                curNode = curNode.getOrInsert(tokens[i]);
            }
        }
    }

    private String generateDepthString(int depth) {
        if (depth == 0) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth - 1; i++) {
            sb.append("--");
        }
        return sb.toString();
    }

    private String printNodes(Node node, int depth) {
        StringBuilder sb = new StringBuilder();

        //root가 아니면 노드가 가진 값을 출력문에 추가
        if (!node.getValue().equals("")) {
            sb.append(generateDepthString(depth)).append(node.getValue()).append("\n");
        }

        List<Node> children = node.getChildren();
        for (Node child : children) {
            sb.append(printNodes(child, depth + 1));
        }

        return sb.toString();
    }

    class Node {
        private String value;
        private LinkedList<Node> children;

        public Node(String value) {
            this.value = value;
            this.children = new LinkedList<>();
        }

        private Node insert(String value, int idx) {
            Node newNode = new Node(value);
            children.add(idx, newNode);
            return newNode;
        }

        public Node getOrInsert(String value) {
            if (children.size() == 0) {
                return insert(value, 0);
            }

            Node node = null;
            int start = 0, end = children.size() - 1;

            //같은 값을 가진 노드를 이분 탐색으로 찾는다.
            while (start <= end) {
                int mid = (start + end) / 2;
                String midValue = children.get(mid).getValue();

                if (midValue.equals(value)) {
                    node = children.get(mid);
                    break;
                } else if (midValue.compareTo(value) < 0) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            //같은 값을 가진 노드가 없다면 마지막 탐색 위치에 해당 값의 노드를 추가한다.
            if (node == null) {
                return insert(value, start);
            }
            return node;
        }

        public String getValue() {
            return this.value;
        }

        public List<Node> getChildren() {
            return this.children;
        }
    }
}
