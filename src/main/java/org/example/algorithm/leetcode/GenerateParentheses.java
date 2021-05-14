package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Changhee Choi
 * @since 14/05/2021
 */
public class GenerateParentheses {
  private List<String> answer;
  private int n;

  public List<String> generateParenthesis(int n) {
    answer = new ArrayList<>();
    this.n = n;

    List<Character> initList = new ArrayList<>();
    initList.add('(');
    generateParenthesis(initList);
    return answer;
  }

  private void generateParenthesis(List<Character> parenthesis) {
    if (parenthesis.size() == 2 * n) {
      if (isWellFormed(parenthesis)) {
        StringBuilder sb = new StringBuilder();

        for (Character ch : parenthesis) {
          sb.append(ch);
        }
        answer.add(sb.toString());
      }
      return;
    }

    parenthesis.add('(');
    generateParenthesis(parenthesis);
    parenthesis.remove(parenthesis.size() - 1);

    parenthesis.add(')');
    generateParenthesis(parenthesis);
    parenthesis.remove(parenthesis.size() - 1);
  }

  private boolean isWellFormed(List<Character> parenthesis) {
    if (parenthesis.get(0) != '(') {
      return false;
    }

    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < parenthesis.size(); i++) {
      Character c = parenthesis.get(i);
      if (c == '(') {
        stack.push(c);
      } else {
        if (stack.isEmpty()) {
          return false;
        }
        stack.pop();
      }
    }
    if (!stack.isEmpty()) {
      return false;
    }
    return true;
  }
}
