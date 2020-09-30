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

    public boolean solution(Integer[] treeNodes) {
        TreeNode root = makeTree(treeNodes);
        return isSymmetric(root);
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
