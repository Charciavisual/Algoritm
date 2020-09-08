package org.example.algorithm.programmers.kakao.blind_2019;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Changhee Choi
 * @since 08/09/2020
 */
public class FindRoadGame {
    private ArrayList<Integer> preOrderResult;
    private ArrayList<Integer> postOrderResult;

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            pq.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        Node root = pq.poll();

        while (!pq.isEmpty()) {
            makeTree(root, pq.poll());
        }

        preOrderResult = new ArrayList<>(nodeinfo.length);
        postOrderResult = new ArrayList<>(nodeinfo.length);

        preOrder(root);
        postOrder(root);


        answer[0] = preOrderResult.stream().mapToInt(Integer::valueOf).toArray();
        answer[1] = postOrderResult.stream().mapToInt(Integer::valueOf).toArray();

        return answer;
    }

    private void makeTree(Node root, Node node) {
        if (root.getX() > node.getX()) {
            if (root.getLeftChild() == null) root.setLeftChild(node);
            else makeTree(root.getLeftChild(), node);
        } else {
            if (root.getRightChild() == null) root.setRightChild(node);
            else makeTree(root.getRightChild(), node);
        }
    }

    private void preOrder(Node root) {
        if (root == null) return;

        preOrderResult.add(root.getNo());
        preOrder(root.getLeftChild());
        preOrder(root.getRightChild());
    }

    private void postOrder(Node root) {
        if (root == null) return;

        postOrder(root.getLeftChild());
        postOrder(root.getRightChild());
        postOrderResult.add(root.getNo());
    }
}

class Node implements Comparable<Node> {
    private int no;
    private int x;
    private int y;
    private Node leftChild;
    private Node rightChild;

    public Node(int no, int x, int y) {
        this.no = no;
        this.x = x;
        this.y = y;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public int getNo() {
        return no;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    @Override
    public int compareTo(Node o) {
        if (this.y == o.y) return this.x - o.x;
        else return o.y - this.y;
    }
}
