package org.example.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 *
 * @author Changhee Choi
 * @since 01/10/2020
 */
public class SubtreeOfAnotherTree {
    //제출하는 부분
    //Given two non-empty binary trees s and t
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    public boolean traverse(TreeNode s, TreeNode t) {
        return s != null && (checkSubTree(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }

    public boolean checkSubTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        return (s.val == t.val)
                && checkSubTree(s.left, t.left)
                && checkSubTree(s.right, t.right);
    }

    // 구현 과정에서 필요한 코드
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean solution(Integer[] treeNodes1, Integer[] treeNodes2) {
        TreeNode s = makeTree(treeNodes1);
        TreeNode t = makeTree(treeNodes2);
        return isSubtree(s, t);
    }

    private TreeNode makeTree(Integer[] treeNodes) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(treeNodes[0]);
        queue.add(root);

        int idx = 1;
        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            Integer leftNodeNumber = idx < treeNodes.length ? treeNodes[idx++] : null;
            Integer rightNodeNumber = idx < treeNodes.length ? treeNodes[idx++] : null;

            if (leftNodeNumber != null) {
                TreeNode leftNode = new TreeNode(leftNodeNumber);
                parent.left = leftNode;
                queue.add(leftNode);
            }

            if (rightNodeNumber != null) {
                TreeNode rightNode = new TreeNode(rightNodeNumber);
                parent.right = rightNode;
                queue.add(rightNode);
            }
        }

        return root;
    }
}
