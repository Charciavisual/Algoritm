package org.example.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/symmetric-tree/
 *
 * @author Changhee Choi
 * @since 30/09/2020
 */
public class SymmetricTree {

    // 제출하는 부분
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
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

    public boolean solution(int[] treeNodes) {
        TreeNode root = makeTree(treeNodes);
        return isSymmetric(root);
    }

    private TreeNode makeTree(int[] treeNodes) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(treeNodes[0]);
        queue.add(root);

        for (int i = 1; i < treeNodes.length - 1; i += 2) {
            TreeNode parent = queue.poll();
            TreeNode leftNode = new TreeNode(treeNodes[i]);
            TreeNode rightNode = new TreeNode(treeNodes[i + 1]);
            parent.left = leftNode;
            parent.right = rightNode;
            queue.add(leftNode);
            queue.add(rightNode);
        }

        return root;
    }
}
