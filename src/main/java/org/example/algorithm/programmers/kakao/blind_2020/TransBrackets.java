package org.example.algorithm.programmers.kakao.blind_2020;

import java.util.Stack;

/**
 * 카카오 2020 블라인드 - 괄호변환
 * https://programmers.co.kr/learn/courses/30/lessons/60058
 *
 * @author Changhee Choi
 * @since 10/07/2020
 */
public class TransBrackets {
    public String solution(String p) {
        String answer = "";
        answer = makeBalancedBracket(p);
        return answer;
    }

    private String makeBalancedBracket(String p) {
        char[] bracketChars = p.toCharArray();

        if (bracketChars.length == 0) {
            return "";
        }

        int lCount = 0, rCount = 0;
        int idx = 0;

        while (true) {
            if (bracketChars[idx++] == '(') lCount++;
            else rCount++;

            if (lCount == rCount) break;
        }

        String ret = "";
        String u = p.substring(0, idx);
        String v = p.substring(idx, p.length());

        if (isCompletedBracketString(u)) {
            ret = u + makeBalancedBracket(v);
        } else {
            ret = "(" + makeBalancedBracket(v) + ")" + reverseBrackets(u);
        }

        return ret;
    }

    private boolean isCompletedBracketString(String s) {
        Stack<Character> stack = new Stack<>();
        char[] brackets = s.toCharArray();

        for (int i = 0; i < brackets.length; i++) {
            if (brackets[i] == '(') {
                stack.push(brackets[i]);
            } else {
                if (stack.isEmpty() || stack.peek() == ')') {
                    return false;
                }
                stack.pop();
            }
        }
        return true;
    }

    private String reverseBrackets(String s) {
        StringBuilder sb = new StringBuilder();
        char[] brackets = s.toCharArray();

        for (int i = 1; i < brackets.length - 1; i++) {
            if (brackets[i] == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }

        return sb.toString();
    }

}
