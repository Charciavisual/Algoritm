package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Changhee Choi
 * @since 14/05/2021
 */
public class GenerateParentheses {
  private List<String> answer;

  public List<String> generateParenthesis(int n) {
    answer = new ArrayList<>();
    backtrack(new StringBuilder(), 0, 0, n);
    return answer;
  }

  public void backtrack(StringBuilder cur, int open, int close, int max) {
    if (cur.length() == max * 2) {
      answer.add(cur.toString());
      return;
    }

    if (open < max) {
      cur.append("(");
      backtrack(cur, open + 1, close, max);
      cur.deleteCharAt(cur.length() - 1);
    }
    if (close < open) { // close는 open보다 적을때에만 추가할 수 있다. (완전한상태: open == close)
      cur.append(")");
      backtrack(cur, open, close + 1, max);
      cur.deleteCharAt(cur.length() - 1);
    }
  }
}
