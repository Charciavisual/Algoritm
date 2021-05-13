package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Changhee Choi
 * @since 13/05/2021
 */
public class Permutations {
  private boolean[] visited;
  private List<List<Integer>> answer;
  private int[] nums;

  public List<List<Integer>> permute(int[] nums) {
    visited = new boolean[nums.length];
    answer = new ArrayList<>();
    this.nums = nums;
    makePermutation(new ArrayList<>());
    return answer;
  }

  private void makePermutation(List<Integer> permutation) {
    if (permutation.size() == nums.length) {
      answer.add(new ArrayList<>(permutation));
    }

    for (int i = 0; i < nums.length; i++) {
      if (visited[i]) {
        continue;
      }
      permutation.add(nums[i]);
      visited[i] = true;
      makePermutation(permutation);
      permutation.remove(permutation.size() - 1);
      visited[i] = false;
    }
  }
}
