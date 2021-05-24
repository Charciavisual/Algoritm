package org.example.algorithm.leetcode;

import org.example.algorithm.leetcode.tree.TreeNode;

/**
 * @author Changhee Choi
 * @since 24/05/2021
 */
public class KthSmallestElement {
  private int remain, answer;

  public int kthSmallest(TreeNode root, int k) {
    remain = k;
    kthSmallest(root);
    return answer;
  }

  private void kthSmallest(TreeNode root) {
    if (root == null) {
      return;
    }
    kthSmallest(root.left);
    remain--;
    if (remain == 0) {
      answer = root.val;
      return;
    }
    kthSmallest(root.right);
  }
}
