package org.example.algorithm.codility;

import java.util.HashSet;
import java.util.Set;

/**
 * @link https://app.codility.com/programmers/lessons/6-sorting/distinct/
 * @author Changhee Choi
 * @since 01/01/2021
 */
public class Distinct {
  public int solution(int[] A) {
    Set<Integer> numbers = new HashSet<>();
    int count = 0;
    for (int number : A) {
      if (!numbers.contains(number)) {
        numbers.add(number);
        count++;
      }
    }
    return count;
  }
}
