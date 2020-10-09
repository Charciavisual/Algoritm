package org.example.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Changhee Choi
 * @since 09/10/2020
 */
public class TreeUtil {
    public static TreeNode makeTree(Integer[] treeNodes) {
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
