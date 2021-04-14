package org.example.algorithm.leetcode.dp;

import org.example.algorithm.leetcode.tree.TreeNode;

/**
 * @author Changhee Choi
 * @since 07/04/2021
 */
public class LongestZigzagPathBinaryTree {
  private final int LEFT = 0, RIGHT = 1;
  private int result = 0;

  public int longestZigZag(TreeNode root) {
    findLongestZigZag(root.left, LEFT, 1);
    findLongestZigZag(root.right, RIGHT, 1);
    return result;
  }

  private void findLongestZigZag(TreeNode node, int dir, int len) {
    if (node == null) {
      return;
    }

    result = Math.max(result, len);

    if (dir == LEFT) {
      findLongestZigZag(node.right, RIGHT, len + 1); // 지그재그로 이동하여 길이 늘림
      findLongestZigZag(node.left, LEFT, 1); // 같은 방향의 자식 노드에서 새로 시작
    } else {
      findLongestZigZag(node.left, LEFT, len + 1); // 지그재그로 이동하여 길이 늘림
      findLongestZigZag(node.right, RIGHT, 1); // 같은 방향의 자식 노드에서 새로 시작
    }
  }
}
