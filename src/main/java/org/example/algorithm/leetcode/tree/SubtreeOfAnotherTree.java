package org.example.algorithm.leetcode.tree;

import static org.example.algorithm.leetcode.tree.TreeUtil.makeTree;

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
        return s != null && (checkSubTree(s, t)
                || traverse(s.left, t)
                || traverse(s.right, t));
    }

    public boolean checkSubTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        return (s.val == t.val)
                && checkSubTree(s.left, t.left)
                && checkSubTree(s.right, t.right);
    }

    public boolean solution(Integer[] treeNodes1, Integer[] treeNodes2) {
        TreeNode s = makeTree(treeNodes1);
        TreeNode t = makeTree(treeNodes2);
        return isSubtree(s, t);
    }
}