package org.example.algorithm.leetcode.dp;

import org.example.algorithm.leetcode.tree.TreeNode;

/**
 * @author Changhee Choi
 * @since 17/04/2021
 */
public class HouseRobberIII {
  private final int ROBBED = 0, NOT_ROBBED = 1;

  public int rob(TreeNode root) {
    int[] result = breakInto(root);
    return Math.max(result[ROBBED], result[NOT_ROBBED]);
  }

  private int[] breakInto(TreeNode house) {
    if (house == null) {
      return new int[] {0, 0};
    }

    int[] leftResult = breakInto(house.left);
    int[] rightResult = breakInto(house.right);
    // 현재 집을 훔치기 위해서는 왼쪽 자식 노드와 오른쪽 자식 노드 모두 훔치지 않아야 한다.
    int robbedResult = house.val + leftResult[NOT_ROBBED] + rightResult[NOT_ROBBED];
    // 현재 집을 훔치지 않는 경우 왼쪽 자식 노드와 오른쪽 자식 노드는 훔치거나 훔치지 않은 경우 모두 가능하며
    // 왼쪽 서브트리와 오른쪽 서브트리에서 합이 가장 큰 경우를 선택하면 된다.
    int notRobbedResult =
        Math.max(leftResult[ROBBED], leftResult[NOT_ROBBED])
            + Math.max(rightResult[ROBBED], rightResult[NOT_ROBBED]);
    return new int[] {robbedResult, notRobbedResult};
  }
}
