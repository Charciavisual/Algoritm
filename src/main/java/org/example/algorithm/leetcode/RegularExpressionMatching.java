package org.example.algorithm.leetcode;

/**
 * Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * @author Changhee Choi
 * @since 05/09/2020
 */
public class RegularExpressionMatching {
    Boolean[][] memo;

    public boolean isMatch(String text, String pattern) {
        memo = new Boolean[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    /**
     * @param i       문자열 인덱스
     * @param j       패턴 인덱스
     * @param text    문자열
     * @param pattern 패턴
     * @return 매칭 여부
     */
    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == true;
        }

        boolean ans;

        //모든 패턴을 확인
        if (j == pattern.length()) {
            //모든 문자가 패턴에 매칭 되었는지 여부
            ans = i == text.length();
            memo[i][j] = ans;
            return ans;
        }

        boolean first_match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

        if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
            ans = dp(i, j + 2, text, pattern) || (first_match && dp(i + 1, j, text, pattern));
        } else {
            ans = first_match && dp(i + 1, j + 1, text, pattern);
        }

        memo[i][j] = ans;
        return ans;
    }
}
