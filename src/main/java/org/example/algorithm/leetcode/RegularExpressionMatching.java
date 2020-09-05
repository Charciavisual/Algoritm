package org.example.algorithm.leetcode;

/**
 * Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * @author Changhee Choi
 * @since 05/09/2020
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            // * : 0 또는 그 이상 매칭됨
            //return (0번 매칭 || 1번이상 매칭)
            return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }
}
