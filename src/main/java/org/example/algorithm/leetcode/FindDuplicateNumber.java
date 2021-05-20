package org.example.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Changhee Choi
 * @since 20/05/2021
 */
public class FindDuplicateNumber {
  public int findDuplicate(int[] nums) {
    Set<Integer> numberSet = new HashSet<>();

    int answer = 0;
    for (int num : nums) {
      if (numberSet.contains(num)) {
        answer = num;
        break;
      }
      numberSet.add(num);
    }

    return answer;
  }
}
