package org.example.algorithm.leetcode.tree;

/**
 * https://leetcode.com/problems/increasing-order-search-tree/
 *
 * @author Changhee Choi
 * @since 19/10/2020
 */
public class IncreasingOrderSearchTree {
    private TreeNode cur;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode bst = new TreeNode(0);
        cur = bst;
        inorder(root);
        return bst.right;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        cur.right = new TreeNode(node.val);
        cur = cur.right;
        inorder(node.right);
    }
}
