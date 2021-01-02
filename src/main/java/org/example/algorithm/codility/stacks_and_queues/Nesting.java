package org.example.algorithm.codility.stacks_and_queues;

import java.util.Stack;

/**
 * @link https://app.codility.com/programmers/lessons/7-stacks_and_queues/nesting/
 * @author Changhee Choi
 * @since 02/01/2021
 */
public class Nesting {
  public int solution(String S) {
    return isNested(S) ? 1 : 0;
  }

  private boolean isNested(String S) {
    if (S.isEmpty()) {
      return true;
    }

    Stack<Character> stack = new Stack<>();
    char[] chars = S.toCharArray();

    for (char aChar : chars) {
      if (aChar == '(') {
        stack.push(aChar);
      } else if (stack.isEmpty()) {
        return false;
      } else {
        stack.pop();
      }
    }

    return stack.isEmpty() ? true : false;
  }
}
