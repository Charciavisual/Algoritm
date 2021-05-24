package org.example.algorithm.leetcode;

import org.example.algorithm.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Changhee Choi
 * @since 24/05/2021
 */
public class KthSmallestElement {
  private List<Integer> arr = new ArrayList<>();

  public int kthSmallest(TreeNode root, int k) {
    kthSmallest(root);
    return arr.get(k - 1);
  }

  private void kthSmallest(TreeNode root) {
    if (root == null) {
      return;
    }
    kthSmallest(root.left);
    arr.add(root.val);
    kthSmallest(root.right);
  }
}
