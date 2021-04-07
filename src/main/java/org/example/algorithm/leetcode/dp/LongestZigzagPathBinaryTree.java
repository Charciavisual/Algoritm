package org.example.algorithm.leetcode.dp;

import org.example.algorithm.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author Changhee Choi
 * @since 07/04/2021
 */
public class LongestZigzagPathBinaryTree {
  private final int LEFT = 0, RIGHT = 1;
  private Map<TreeNode, Integer[]> cacheMap = new HashMap<>();

  public int longestZigZag(TreeNode root) {
    int answer = 0;
    Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    treeNodeQueue.add(root);

    while (!treeNodeQueue.isEmpty()) {
      TreeNode curNode = treeNodeQueue.poll();
      answer =
          Math.max(
              answer,
              Math.max(findLongestZigZag(curNode, LEFT), findLongestZigZag(curNode, RIGHT)));

      if (hasLeft(curNode)) {
        treeNodeQueue.add(curNode.left);
      }
      if (hasRight(curNode)) {
        treeNodeQueue.add(curNode.right);
      }
    }

    return answer;
  }

  private int findLongestZigZag(TreeNode node, int dir) {
    if (dir == LEFT && !hasLeft(node)) {
      return 0;
    }
    if (dir == RIGHT && !hasRight(node)) {
      return 0;
    }

    if (cacheMap.get(node) != null && cacheMap.get(node)[dir] != 0) {
      return cacheMap.get(node)[dir];
    }

    int zigzagLen = 0;
    int nextDir = dir ^ 1;

    if (dir == LEFT) {
      zigzagLen = findLongestZigZag(node.left, nextDir) + 1;
    } else {
      zigzagLen = findLongestZigZag(node.right, nextDir) + 1;
    }

    Integer[] curNodeCache = cacheMap.getOrDefault(node, new Integer[] {0, 0});
    curNodeCache[dir] = zigzagLen;
    cacheMap.put(node, curNodeCache);
    return zigzagLen;
  }

  private boolean hasLeft(TreeNode node) {
    return node.left != null;
  }

  private boolean hasRight(TreeNode node) {
    return node.right != null;
  }
}
